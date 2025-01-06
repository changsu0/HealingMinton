package com.corelabs.tournament.controller;

import com.corelabs.tournament.service.TournamentService;
import com.corelabs.tournament.vo.TournamentVO;
import com.google.gson.Gson;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/tournament")
public class TournamentController {
    
    final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournamentList")
    public String tournamentList(Model model) {
        return "tournament/tournamentList";
    }

    @GetMapping("/tournamentMgmt")
    public String tournamentMgmt(Model model) {
        return "tournament/tournamentMgmt";
    }

    @PostMapping("/selectTournamentList")
    @ResponseBody
    public String selectTournamentList(@RequestBody TournamentVO tournamentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", tournamentService.selectTournamentList(tournamentVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/insertTournament")
    @ResponseBody
    public String insertTournament(@RequestBody TournamentVO tournamentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            tournamentService.insertTournament(tournamentVO);
            rstMap.put("data", tournamentVO);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/deleteTournament")
    @ResponseBody
    public String deleteTournament(@RequestBody TournamentVO tournamentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            tournamentService.deleteTournament(tournamentVO);
            rstMap.put("data", tournamentVO);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping(value = "/insertTournament1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap> insertTournament1(MultipartHttpServletRequest multiRequest) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();

        String uploadDir = "D:/files"; // 파일이 저장될 경로
        makeFolder(uploadDir); // 폴더 생성

        String tournamentTitle = multiRequest.getParameter("tournamentTitle");
        String tournamentStDt = multiRequest.getParameter("tournamentStDt");
        String tournamentEnDt = multiRequest.getParameter("tournamentEnDt");
        String fileName = "";
        String newFileName = "";

        try {

            Iterator<Map.Entry<String, MultipartFile>> itr = multiRequest.getFileMap().entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, MultipartFile> entry = itr.next();
                MultipartFile file = entry.getValue();

                if (file.getSize() > 10485760) { // 1MB = 1048576 bytes
                    rstMap.put("code", -1);
                    rstMap.put("failMsg", "10MB 이하 파일만 업로드 가능합니다.");
                    rstMap.put("failCause", "10MB 이하 파일만 업로드 가능합니다.");
                    return new ResponseEntity<HashMap>(rstMap, HttpStatus.OK);
                }

                fileName = file.getOriginalFilename();
                String extension = getExtension(fileName);
                UUID uuid = UUID.randomUUID();

                newFileName = uuid.toString() + "." + extension;

                File dest = new File(uploadDir, newFileName);

                try {
                    file.transferTo(dest); // 파일 저장
                } catch (IOException e) {
                    rstMap.put("code", -1);
                    rstMap.put("failMsg", "처리중 에러발생");
                    rstMap.put("failCause", e.getCause());
                }
            }

            TournamentVO tournamentVO = new TournamentVO();
            tournamentVO.setTournamentTitle(tournamentTitle);
            tournamentVO.setTournamentStDt(tournamentStDt);
            tournamentVO.setTournamentEnDt(tournamentEnDt);
            tournamentVO.setOriFileNm(fileName);
            tournamentVO.setNewFileNm(newFileName);
            tournamentVO.setFullFileNm(uploadDir + newFileName);
            tournamentService.insertTournament(tournamentVO);
            rstMap.put("data", tournamentVO);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
        }finally {
            return new ResponseEntity<HashMap>(rstMap, HttpStatus.OK);
        }
    }

    public void makeFolder( String directoryPath ){
        // File 객체 생성
        File directory = new File(directoryPath);

        // 폴더가 존재하지 않으면 생성
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("폴더 생성 성공: " + directoryPath);
            } else {
                System.out.println("폴더 생성 실패: " + directoryPath);
            }
        }
    }

    public String getExtension(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');
        if (dotPosition != -1 && fileName.length() - 1 > dotPosition) {
            return fileName.substring(dotPosition + 1);
        } else {
            return "";
        }
    }


    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPdf(@RequestParam String filename) {
        try {
            Path file = Paths.get("D:/files/" + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}