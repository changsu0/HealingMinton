package com.corelabs.home.controller;

import com.corelabs.menu.vo.MenuVO;
import com.corelabs.menu.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MenuService menuService;

    public HomeController() {

    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request, @ModelAttribute MenuVO menuVO) {
        HttpSession session = request.getSession(true);

//        /* 1depth의 메뉴리스트 */
//        List<MenuVO> depth1List = menuService.selectParentMenuList(menuVO);
//        session.setAttribute("depth1List", depth1List);
//
//        /* 2depth의 메뉴리스트 */
//        List<MenuVO> depth2List = menuService.selectChildMenuList(menuVO);
//        session.setAttribute("depth2List", depth2List);

        return "index";
    }

    @GetMapping("/error403")
    public String error403(Model model) {
        return "error/error";
    }

}