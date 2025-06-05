package com.corelabs.ase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.corelabs.ase.service.AseMenuService;
import com.corelabs.ase.vo.AseMenuVO;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/ase/menu")
public class AseMenuController {
    
    final AseMenuService aseMenuService;

    public AseMenuController(AseMenuService aseMenuService) {
        this.aseMenuService = aseMenuService;
    }

    @GetMapping("/menuList")
    public String menuList() {
        return "ase/menuList";
    }

    @GetMapping("/createMenu")
    public String createMenu() {
        return "ase/createMenu";
    }

    @PostMapping("/selectMenuList")
    @ResponseBody
    public String selectMenuList(@ModelAttribute AseMenuVO aseMenuVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<AseMenuVO> menuList = aseMenuService.selectMenuList(aseMenuVO);
            System.out.println("Menu List Size: " + menuList.size());
            for (AseMenuVO menu : menuList) {
                System.out.println("Menu: " + menu.toString());
            }
            
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", menuList);
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }
    }

    @PostMapping("/saveMenu")
    @ResponseBody
    public String saveMenu(@ModelAttribute AseMenuVO aseMenuVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "저장 성공");
            rstMap.put("data", aseMenuService.saveMenu(aseMenuVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/updateMenuId")
    @ResponseBody
    public String updateMenuId(@RequestParam String oldMenuId, @RequestParam String newMenuId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            aseMenuService.updateMenuId(oldMenuId, newMenuId);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "메뉴 ID가 변경되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "메뉴 ID 변경 중 오류가 발생했습니다: " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            return rstJson;
        }
    }

    @PostMapping("/deleteMenu")
    @ResponseBody
    public String deleteMenu(@RequestParam String menuId) {
        HashMap<String, Object> rstMap = new HashMap<>();
        try {
            // 하위 메뉴 존재 여부 확인
            AseMenuVO paramVO = new AseMenuVO();
            paramVO.setParentMenuId(menuId);  // parentMenuId로 검색하여 하위 메뉴 확인
            List<AseMenuVO> childMenus = aseMenuService.selectMenuList(paramVO);
            if (childMenus != null && !childMenus.isEmpty()) {
                rstMap.put("code", 400);
                rstMap.put("failMsg", "하위 메뉴가 존재하여 삭제할 수 없습니다.");
                return new Gson().toJson(rstMap);
            }

            // 메뉴 삭제
            aseMenuService.deleteMenu(menuId);
            
            rstMap.put("code", 200);
            rstMap.put("successMsg", "메뉴가 삭제되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", 500);
            rstMap.put("failMsg", "메뉴 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return new Gson().toJson(rstMap);
    }

    @PostMapping("/selectMenuByMenuId")
    @ResponseBody
    public String selectMenuByMenuId(@RequestParam String menuId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            System.out.println("Selecting menu by ID: " + menuId);
            AseMenuVO menu = aseMenuService.selectMenuByMenuId(menuId);
            System.out.println("Found menu: " + (menu != null ? menu.toString() : "null"));
            
            if (menu == null) {
                rstMap.put("code", 404);
                rstMap.put("failMsg", "메뉴를 찾을 수 없습니다: " + menuId);
            } else {
                rstMap.put("code", 200);
                rstMap.put("successMsg", "조회 성공");
                rstMap.put("data", menu);
            }
        } catch (Exception e) {
            System.err.println("Error in selectMenuByMenuId: " + e.getMessage());
            e.printStackTrace();
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생: " + e.getMessage());
            rstMap.put("failCause", e.getCause() != null ? e.getCause().getMessage() : "Unknown cause");
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }
    }

}
