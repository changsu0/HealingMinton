package com.corelabs.ase.controller;

import com.corelabs.ase.service.AseService;
import com.corelabs.ase.vo.AseVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/ase")
public class AseController {

    final AseService aseService;

    public AseController(AseService aseService) {
        this.aseService = aseService;
    }

    @GetMapping("/userList")
    public String userList() {
        return "ase/userLIst";
    }
    @GetMapping("/createUser")
    public ModelAndView createUser(@RequestParam(value = "userUid", required = false) String userUid) {
        ModelAndView modelAndView = new ModelAndView("ase/createUser");
        if (userUid != null) {
            AseVO aseVO = aseService.getUserByUid(userUid);
            modelAndView.addObject("user", aseVO);
        }
        return modelAndView;
    }
    @PostMapping("/selectUserList")
    @ResponseBody
    public String selectUserList(@ModelAttribute AseVO aseVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseService.selectUserList(aseVO));
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
    public String saveUser(@ModelAttribute AseVO aseVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseService.saveUser(aseVO));
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
            aseService.deleteUser(userUid);
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

    @GetMapping("/menuList")
    public String menuList() {
        return "ase/menuList";
    }

    @PostMapping("/selectMenuList")
    @ResponseBody
    public String selectMenuList(@ModelAttribute AseVO aseVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseService.selectMenuList(aseVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/saveMenu")
    @ResponseBody
    public String saveMenu(@ModelAttribute AseVO aseVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "저장 성공");
            rstMap.put("data", aseService.saveMenu(aseVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @GetMapping("/authList")
    public String authList() {
        return "ase/authList";
    }

    @PostMapping("/selectAuthList")
    @ResponseBody
    public String selectAuthList(@ModelAttribute AseVO aseVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseService.selectAuthList(aseVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/saveAuth")
    @ResponseBody
    public String saveAuth(@ModelAttribute AseVO aseVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "저장 성공");
            rstMap.put("data", aseService.saveAuth(aseVO));
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
            rstMap.put("data", aseService.assignSingleAuthToUser(userUid, authId));
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
            rstMap.put("data", aseService.selectUserAuthList(userUid));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }


    @PostMapping("/assignAuthToMenu")
    @ResponseBody
    public String assignAuthToMenu(@RequestParam String menuId, @RequestParam List<String> authIds) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "권한 부여 성공");
            rstMap.put("data", aseService.assignAuthToMenu(menuId, authIds));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/selectMenuAuthList")
    @ResponseBody
    public String selectMenuAuthList(@RequestParam String menuId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", aseService.selectMenuAuthList(menuId));
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
