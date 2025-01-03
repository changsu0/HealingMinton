package com.corelabs.sample.controller;

import com.corelabs.sample.service.ActorService;
import com.corelabs.sample.service.FilesService;
import com.corelabs.sample.vo.ActorVO;
import com.corelabs.sample.vo.FilesVO;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/sample")
public class SampleController {
    
    final
    ActorService actorService;
    final
    FilesService filesService;

    public SampleController(ActorService actorService, FilesService filesService) {
        this.actorService = actorService;
        this.filesService = filesService;
    }

    @GetMapping("/sample")
    public String sample(Model model) {
        return "sample/sample2";
    }

    @GetMapping("/cal")
    public String cal(Model model) {
        return "cal/cal";
    }

    @GetMapping("/sample3")
    public String sample3(Model model) {
        return "sample/sample3";
    }

    @GetMapping("/sampleList")
    public String sampleList(Model model) {
        return "sample/sampleList";
    }

    @GetMapping("/imageList")
    public String imageList(Model model) {
        return "image/imageList";
    }

    @GetMapping("/selectActorList")
    @ResponseBody
    public String selectActorList(@ModelAttribute ActorVO actorVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", actorService.selectActorList(actorVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/insertActor")
    @ResponseBody
    public String insertActor(@ModelAttribute ActorVO actorVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "등록 성공");
            rstMap.put("data", actorService.insertActor(actorVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/updateActor")
    @ResponseBody
    public String updateActor(@ModelAttribute ActorVO actorVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "수정 성공");
            rstMap.put("data", actorService.updateActor(actorVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/deleteActor")
    @ResponseBody
    public String deleteActor(@ModelAttribute ActorVO actorVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "삭제 성공");
            rstMap.put("data", actorService.deleteActor(actorVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    /*파일 업로드, 업로드 결과 반환*/
    @PostMapping("/save/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("files") MultipartFile[] uploadFiles, @RequestParam("info") String info) throws IOException {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();

        String docUID = UUID.randomUUID().toString().replaceAll("-", "");

//        List<HashMap> resultDTOList = new ArrayList<>();
        List<FilesVO> filesVOList = new ArrayList<>();
        for (MultipartFile uploadFile: uploadFiles) {
            String oriFileFullName = uploadFile.getOriginalFilename();
            String oriFileName = FilenameUtils.getBaseName( oriFileFullName );
            String extension = FilenameUtils.getExtension( oriFileFullName );
            String oriFileSize = uploadFile.getBytes().toString();

            String saveFilePath = makeFolder();
            String saveFileName = UUID.randomUUID().toString().replaceAll("-", "");
            String saveFileFullName = saveFileName + "." + extension;


            // 저장할 파일 이름 중간에 "_"를 이용해서 구현
            String saveFileFullPath = saveFilePath + File.separator + saveFileFullName;
            Path savePath = Paths.get( saveFileFullPath );

            FilesVO filesVO = new FilesVO();
            filesVO.setDocUid(docUID);
            filesVO.setDocType("A");

            filesVO.setOriFileNm( oriFileName );
            filesVO.setOriFileFullNm( oriFileFullName );
            filesVO.setOriFileType( extension );
            filesVO.setOriFileSize( oriFileSize );
            filesVO.setSaveFileNm( saveFileName );
            filesVO.setSaveFileFullNm( saveFileFullName );
            filesVO.setSaveFileType( extension );
            filesVO.setSaveFileSize( oriFileSize );
            filesVO.setSaveFilePath( saveFilePath );
            filesVO.setSaveFileFullPath( saveFileFullPath );
            filesVO.setCreateUserId( "SYSTEM" );

            try {
                uploadFile.transferTo(savePath); // 실제 이미지 저장
                filesVOList.add(filesVO);
                rstMap.put("code", 200);
                rstMap.put("successMsg", "삭제 성공");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        filesService.insertFiles( filesVOList );

        return gson.toJson( rstMap );
    }


    /*날짜 폴더 생성*/
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder --------
        File uploadPathFolder = new File(folderPath);

        if(!uploadPathFolder.exists()) {
            boolean mkdirs = uploadPathFolder.mkdirs();
            System.out.println("-------------------makeFolder------------------");
            System.out.println("uploadPathFolder.exists(): "+uploadPathFolder.exists());
            System.out.println("mkdirs: "+mkdirs);
        }

        return folderPath;

    }

}