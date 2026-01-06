package com.corelabs.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @GetMapping("/resumeList")
    public String resume() {
        return "resume/resumeList";
    }

    @GetMapping("/createResume")
    public String createResume() {
        return "resume/createResume";
    }
}
