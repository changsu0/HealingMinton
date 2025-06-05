package com.corelabs.ase.controller;

import com.corelabs.ase.service.AseUserService;
import com.corelabs.ase.vo.AseUserVO;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping("/ase/user")
public class AseUserController {

    final AseUserService aseUserService;

    public AseUserController(AseUserService aseUserService) {
        this.aseUserService = aseUserService;
    }

    @GetMapping("/userList")
    public String userList() {
        return "ase/userLIst";
    }
    @GetMapping("/createUser")
    public ModelAndView createUser(@RequestParam(value = "userUid", required = false) String userUid) {
        ModelAndView modelAndView = new ModelAndView("ase/createUser");
        if (userUid != null) {
            AseUserVO aseUserVO = aseUserService.getUserByUid(userUid);
            modelAndView.addObject("user", aseUserVO);
        }
        return modelAndView;
    }
    @PostMapping("/selectUserList")
    @ResponseBody
    public String selectUserList(@ModelAttribute AseUserVO aseUserVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseUserService.selectUserList(aseUserVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(@ModelAttribute AseUserVO aseUserVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseUserService.saveUser(aseUserVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam String userUid) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            aseUserService.deleteUser(userUid);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "삭제 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/assignSingleAuthToUser")
    @ResponseBody
    public String assignSingleAuthToUser(@RequestParam String userUid, @RequestParam String authId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "권한 부여 성공");
            rstMap.put("data", aseUserService.assignSingleAuthToUser(userUid, authId));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/selectUserAuthList")
    @ResponseBody
    public String selectUserAuthList(@RequestParam String userUid) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseUserService.selectUserAuthList(userUid));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }


}
