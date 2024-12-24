package com.corelabs.image.controller;

import com.corelabs.image.service.ImageService;
import com.corelabs.image.vo.ImageVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/image")
public class ImageController {

    final
    ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/imageSingle")
    public String imageSingle(Model model) {
        return "image/imageSingle";
    }

    @GetMapping("/select/imageList")
    @ResponseBody
    public String selectImageList(@ModelAttribute ImageVO imageVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", imageService.selectImageList(imageVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    
}