package com.corelabs.profile.controller;

import com.corelabs.profile.service.ProfileService;
import com.corelabs.profile.vo.ProfileVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    final ProfileService profileService;

    public ProfileController(ProfileService profileService) { this.profileService = profileService; }

    @GetMapping("/profileList")
    public String selectResumeList(Model model) {
        List<ProfileVO> profile = profileService.selectProfileList();

        return "resume/newResumeList";
    }

    @GetMapping("/resume-new")
    public String createResume() {

        return "resume/resume-new";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveProfile(
            @RequestPart(value = "photoFile", required = false) MultipartFile photoFile,
            @RequestPart(value = "attachFiles", required = false) MultipartFile attachFiles,
            @RequestPart("resumeData") ProfileVO newResumeVO) {

        // 서비스 호출하여 파일 저장 및 DB 인서트 처리
//        newResumeService.saveResume(newResumeVO, photoFile);

        return "success";
    }

}
