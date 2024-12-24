package com.corelabs.user.controller;

import com.corelabs.ticket.vo.TicketVO;
import com.corelabs.user.service.UserService;
import com.corelabs.user.vo.UserVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* WEB Start*/
    @GetMapping("/userList")
    public String userList(Model model) {
        return "user/userList";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        return "user/createUser";
    }
    /* WEB End*/


    @PostMapping("/selectUserList")
    @ResponseBody
    public String selectUserList(@ModelAttribute UserVO userVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", userService.selectUserList(userVO));
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
    public String saveUser(@ModelAttribute TicketVO ticketVO){
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", userService.insertUser(ticketVO));
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