package com.corelabs.profile.controller;

import com.corelabs.profile.service.NewResumeService;
import com.corelabs.profile.vo.NewResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/resume")
public class NewResumeController {

    final NewResumeService newResumeService;

    public NewResumeController(NewResumeService newResumeService) { this.newResumeService = newResumeService; }

    @GetMapping("/newResumeList")
    public String selectResumeList(Model model) {
        List<NewResumeVO> resume = newResumeService.selectResumeList();

        model.addAttribute("resumeList", resume);

        return "resume/newResumeList";
    }

    @GetMapping("/resume-new")
    public String createResume() {

        return "resume/resume-new";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveWithPhoto(
            @RequestPart(value = "photoFile", required = false) MultipartFile photoFile,
            @RequestPart("resumeData") NewResumeVO newResumeVO) {

        // 서비스 호출하여 파일 저장 및 DB 인서트 처리
        newResumeService.saveResume(newResumeVO, photoFile);

        return "success";
    }

}
