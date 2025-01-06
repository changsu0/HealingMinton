package com.corelabs.rent.controller;

import com.corelabs.rent.service.RentService;
import com.corelabs.rent.vo.RentVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/rent")
public class RentController {
    
    final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/rentList")
    public String rentList(Model model) {
        return "rent/rentList";
    }

    @PostMapping("/selectRentList")
    @ResponseBody
    public String selectRentList(@RequestBody RentVO rentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", rentService.selectRentList(rentVO));
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

    @PostMapping("/insertRent")
    @ResponseBody
    public String insertRent(@RequestBody RentVO rentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rentService.insertRent(rentVO);
            rstMap.put("data", rentVO);
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

    @PostMapping("/deleteRent")
    @ResponseBody
    public String deleteRent(@RequestBody RentVO rentVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rentService.deleteRent(rentVO);
            rstMap.put("data", rentVO);
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