package com.corelabs.dragLayout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drag")
public class DragController {

    @GetMapping("/dragLayout")
    public String dragLayout(Model model) {
        return "drag/dragLayout";
    }

    @GetMapping("/courtCount")
    public String courtCount(Model model) {
        return "drag/courtCount";
    }

}