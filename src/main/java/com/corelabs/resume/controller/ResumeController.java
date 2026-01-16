package com.corelabs.resume.controller;

import com.corelabs.resume.service.ResumeService;
import com.corelabs.resume.vo.ResumeSearchVO;
import com.corelabs.resume.vo.ResumeVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) { this.resumeService = resumeService; }

    @GetMapping("/resumeList_sync")
    public String resumeSync(@ModelAttribute("SearchVO") ResumeSearchVO resumeSearchVO, Model model) {

        List<ResumeVO> resume = resumeService.selectResumeList(resumeSearchVO);

        model.addAttribute("resumeList", resume);

        return "resume/resumeList_sync";
    }

//    @GetMapping("/resumeList_sync")
//    public String resumeSync(
//            @RequestParam(value = "searchType", required = false) String searchType,
//            @RequestParam(value = "keyword", required = false) String keyword,
//            Model model) {
//
//        List<ResumeVO> resume = resumeService.selectResumeList(searchType, keyword);
//
//        System.out.println(resume);
//        System.out.println(searchType);
//        System.out.println(keyword);
//
//        model.addAttribute("resumeList", resume);
//        model.addAttribute("searchType",searchType);
//        model.addAttribute("keyword",keyword);
//
//        return "resume/resumeList_sync";
//    }

    @GetMapping("/resumeList")
    public String resume() {
        return "resume/resumeList";
    }

    @GetMapping("/resumeDetail")
    public String detailResume() { return "resume/resumeDetail"; }

    @GetMapping("/modifyResume")
    public String modifyResume() { return "resume/modifyResume"; }

    @GetMapping("/createResume")
    public String createResume() {
        return "resume/createResume";
    }

    @GetMapping("/selectResumeList")
    @ResponseBody
    public String selectResumeList(@ModelAttribute ResumeSearchVO resumeSearchVO) {

        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<ResumeVO> resumeList = resumeService.selectResumeList(resumeSearchVO);
            System.out.println("Resume List Size: " + resumeList.size());
            for (ResumeVO resume : resumeList) {
                System.out.println("Resume: " + resume.toString());
            }
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", resumeList);
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }
    }

//    @GetMapping("/selectResumeList")
//    @ResponseBody
//    public String selectResumeList(
//            @RequestParam(value="searchType", required=false) String searchType,
//            @RequestParam(value="keyword", required=false) String keyword) {
//
//        String rstJson = null;
//        HashMap<String, Object> rstMap = new HashMap<>();
//        Gson gson = new Gson();
//        try {
//            List<ResumeVO> resumeList = resumeService.selectResumeList(searchType, keyword);
//            System.out.println("Resume List Size: " + resumeList.size());
//            for (ResumeVO resume : resumeList) {
//                System.out.println("Resume: " + resume.toString());
//            }
//            rstMap.put("code", 200);
//            rstMap.put("successMsg", "조회 성공");
//            rstMap.put("data", resumeList);
//        } catch (Exception e) {
//            rstMap.put("code", -1);
//            rstMap.put("failMsg", "처리중 에러발생");
//            rstMap.put("failCause", e.getCause());
//            System.out.println(e.getMessage());
//        } finally {
//            rstJson = gson.toJson(rstMap);
//            System.out.println("Response JSON: " + rstJson);
//            return rstJson;
//        }
//    }

//    @GetMapping("/selectResumeList")
//    @ResponseBody
//    public String selectResumeList(@ModelAttribute ResumeVO resumeVO) {
//        String rstJson = null;
//        HashMap<String, Object> rstMap = new HashMap<>();
//        Gson gson = new Gson();
//        try {
//            List<ResumeVO> resumeList = resumeService.selectResumeList(resumeVO);
//            System.out.println("Resume List Size: " + resumeList.size());
//            for (ResumeVO resume : resumeList) {
//                System.out.println("Resume: " + resume.toString());
//            }
//            rstMap.put("code", 200);
//            rstMap.put("successMsg", "조회 성공");
//            rstMap.put("data", resumeList);
//        } catch (Exception e) {
//            rstMap.put("code", -1);
//            rstMap.put("failMsg", "처리중 에러발생");
//            rstMap.put("failCause", e.getCause());
//            System.out.println(e.getMessage());
//        } finally {
//            rstJson = gson.toJson(rstMap);
//            System.out.println("Response JSON: " + rstJson);
//            return rstJson;
//        }
//    }

    @GetMapping("/resumeDetail/{resumeId}")
    @ResponseBody
    public ResumeVO selectResumeById(@PathVariable String resumeId) {
        ResumeVO resumeVO = resumeService.selectResumeById(resumeId);
        return resumeVO;
    }

    @GetMapping("/modifyResume/{resumeId}")
    @ResponseBody
    public ResumeVO detailResume(@PathVariable String resumeId) {
        ResumeVO resumeVO = resumeService.selectResumeById(resumeId);
        return resumeVO;
    }

    @PostMapping("/saveResume")
    @ResponseBody
    public String saveResume(@RequestBody ResumeVO resumeVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", resumeService.saveResume(resumeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }

    }

    @PostMapping("/updateResume")
    @ResponseBody
    public String updateResume(@RequestBody ResumeVO resumeVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", resumeService.updateResume(resumeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }

    }

    @PostMapping("/deleteResume")
    @ResponseBody
    public String deleteResume(@RequestParam String resumeId) {
        HashMap<String, Object> rstMap = new HashMap<>();
        try {
            resumeService.deleteResume(resumeId);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "메뉴가 삭제되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", 500);
            rstMap.put("failMsg", "메뉴 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return new Gson().toJson(rstMap);
    }

}
