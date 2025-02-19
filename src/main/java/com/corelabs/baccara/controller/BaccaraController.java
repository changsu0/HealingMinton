package com.corelabs.baccara.controller;

import com.corelabs.baccara.service.BaccaraService;
import com.corelabs.baccara.vo.BaccaraVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/baccara")
public class BaccaraController {
    
    final BaccaraService baccaraService;

    public BaccaraController(BaccaraService baccaraService) {
        this.baccaraService = baccaraService;
    }

    @GetMapping("/baccara")
    public String baccara(Model model) {
        // 상단결과
        List<List<Integer>> rstTR = new ArrayList<>();
        List<Integer> rstTD = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        for (int i = 0; i < 8; i++) {
            rstTR.add(rstTD);
        }
        model.addAttribute("rstTR", rstTR);

        // 예측
        List<List<Integer>> prdcTR = new ArrayList<>();
        List<Integer> prdcTD = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        for (int i = 0; i < 5; i++) {
            prdcTR.add(prdcTD);
        }

        model.addAttribute("prdcTR", prdcTR);
        return "baccara/baccara";
    }

    @PostMapping("/insertBaccara")
    @ResponseBody
    public String insertBaccara(@RequestBody BaccaraVO baccaraVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", baccaraService.insertBaccara(baccaraVO));
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


}