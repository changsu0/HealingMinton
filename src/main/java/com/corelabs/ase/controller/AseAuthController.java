package com.corelabs.ase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.corelabs.ase.service.AseAuthService;
import com.corelabs.ase.vo.AseAuthVO;
import com.corelabs.ase.vo.AseMenuVO;
import com.corelabs.ase.vo.AseUserVO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ase/auth")
public class AseAuthController {

    private final AseAuthService aseAuthService;
    private final Gson gson;

    public AseAuthController(AseAuthService aseAuthService) {
        this.aseAuthService = aseAuthService;
        this.gson = new Gson();
    }

    @GetMapping("/authList")
    public String authList() {
        return "ase/authList";
    }

    @GetMapping("/createAuth")
    public String createAuth(@RequestParam(required = false) String authId, Model model) {
        if (authId != null && !authId.isEmpty()) {
            try {
                AseAuthVO auth = aseAuthService.selectAuthByAuthId(authId);
                model.addAttribute("auth", auth);
            } catch (Exception e) {
                log.error("Error selecting auth by ID", e);
            }
        }
        return "ase/createAuth";
    }

    @PostMapping("/selectAuthList")
    @ResponseBody
    public ResponseEntity<String> selectAuthList(@RequestBody AseAuthVO aseAuthVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<AseAuthVO> authList = aseAuthService.selectAuthList(aseAuthVO);
            log.info("Found {} auth records", authList.size());
            result.put("authList", authList);
            result.put("success", true);
            result.put("message", "조회되었습니다.");
        } catch (Exception e) {
            log.error("Error selecting auth list", e);
            result.put("success", false);
            result.put("message", "권한 목록 조회 중 오류가 발생했습니다.");
        }
        String jsonResponse = gson.toJson(result);
        log.info("Response: {}", jsonResponse);
        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping("/saveAuth")
    @ResponseBody
    public ResponseEntity<String> saveAuth(@RequestBody AseAuthVO aseAuthVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            int saveResult = aseAuthService.saveAuth(aseAuthVO);
            result.put("success", saveResult > 0);
            result.put("message", saveResult > 0 ? "저장되었습니다." : "저장에 실패했습니다.");
        } catch (Exception e) {
            log.error("Error saving auth", e);
            result.put("success", false);
            result.put("message", "저장 중 오류가 발생했습니다.");
        }
        return ResponseEntity.ok(gson.toJson(result));
    }

    @PostMapping("/deleteAuth")
    @ResponseBody
    public ResponseEntity<String> deleteAuth(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        String authId = params.get("authId");
        try {
            aseAuthService.deleteAuth(authId);
            result.put("success", true);
            result.put("message", "삭제되었습니다.");
        } catch (IllegalStateException e) {
            log.warn("Cannot delete auth: {}", e.getMessage());
            result.put("success", false);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting auth", e);
            result.put("success", false);
            result.put("message", "삭제 중 오류가 발생했습니다.");
        }
        return ResponseEntity.ok(gson.toJson(result));
    }

    @GetMapping("/authMenuList")
    public String authMenuList() {
        return "ase/authMenuList";
    }

    @GetMapping("/authUserList")
    public String authUserList() {
        return "ase/authUserList";
    }

    @PostMapping("/selectAuthMenuList")
    @ResponseBody
    public String selectAuthMenuList(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String menuNm = params.get("menuNm");
            String authId = params.get("authId");
            String useYn = params.get("useYn");
            
            List<AseMenuVO> menuList = aseAuthService.selectAuthMenuList(menuNm, authId, useYn);
            result.put("success", true);
            result.put("menuList", menuList);
        } catch (Exception e) {
            log.error("Error selecting auth menu list", e);
            result.put("success", false);
            result.put("message", "메뉴 목록 조회 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/selectAuthUserList")
    @ResponseBody
    public String selectAuthUserList(@RequestBody AseAuthVO aseAuthVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<AseUserVO> userList = aseAuthService.selectAuthUserList(aseAuthVO.getAuthId());
            result.put("success", true);
            result.put("userList", userList);
        } catch (Exception e) {
            log.error("Error selecting auth user list", e);
            result.put("success", false);
            result.put("message", "사용자 목록 조회 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/saveAuthMenu")
    @ResponseBody
    public String saveAuthMenu(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String authId = (String) params.get("authId");
            @SuppressWarnings("unchecked")
            List<Map<String, String>> menuList = (List<Map<String, String>>) params.get("menuList");
            
            if (menuList == null || menuList.isEmpty()) {
                result.put("success", false);
                result.put("message", "메뉴를 하나 이상 선택해주세요.");
            } else {
                List<String> menuIds = menuList.stream()
                    .map(menu -> menu.get("menuId"))
                    .collect(Collectors.toList());

                aseAuthService.saveAuthMenu(authId, menuIds);
                result.put("success", true);
                result.put("message", "저장되었습니다.");
            }
        } catch (Exception e) {
            log.error("Error saving auth menu", e);
            result.put("success", false);
            result.put("message", "저장 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/saveAuthUser")
    @ResponseBody
    public String saveAuthUser(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String authId = (String) params.get("authId");
            @SuppressWarnings("unchecked")
            List<String> userIds = (List<String>) params.get("userIds");
            @SuppressWarnings("unchecked")
            List<String> deletedUserIds = (List<String>) params.get("deletedUserIds");
            
            if (authId == null || authId.isEmpty()) {
                result.put("success", false);
                result.put("message", "권한을 선택해주세요.");
            } else {
                aseAuthService.saveAuthUser(authId, userIds, deletedUserIds);
                result.put("success", true);
                result.put("message", "저장되었습니다.");
            }
        } catch (Exception e) {
            log.error("Error saving auth user", e);
            result.put("success", false);
            result.put("message", "저장 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/selectAuthUser")
    @ResponseBody
    public String selectAuthUser(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String userId = params.get("userId");
            AseUserVO user = aseAuthService.selectAuthUser(userId);
            result.put("success", true);
            result.put("user", user);
        } catch (Exception e) {
            log.error("Error selecting auth user", e);
            result.put("success", false);
            result.put("message", "사용자 정보 조회 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/deleteAuthUser")
    @ResponseBody
    public String deleteAuthUser(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String userId = params.get("userId");
            aseAuthService.deleteAuthUser(userId);
            result.put("success", true);
            result.put("message", "삭제되었습니다.");
        } catch (Exception e) {
            log.error("Error deleting auth user", e);
            result.put("success", false);
            result.put("message", "삭제 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @GetMapping("/createMenuAuth")
    public String createMenuAuth() {
        return "ase/createMenuAuth";
    }

    @GetMapping("/createUserAuth")
    public String createUserAuth() {
        return "ase/createUserAuth";
    }

    @PostMapping("/selectMenuList")
    @ResponseBody
    public String selectMenuList(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String authId = params.get("authId");
            if (authId == null || authId.isEmpty()) {
                result.put("success", false);
                result.put("message", "권한을 선택해주세요.");
            } else {
                List<AseMenuVO> menuList = aseAuthService.selectMenuList(authId);
                result.put("success", true);
                result.put("menuList", menuList);
            }
        } catch (Exception e) {
            log.error("메뉴 목록 조회 중 오류 발생", e);
            result.put("success", false);
            result.put("message", "메뉴 목록 조회 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }

    @PostMapping("/selectUser")
    @ResponseBody
    public String selectUser(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            String userId = params.get("userId");
            AseUserVO user = aseAuthService.selectUser(userId);
            result.put("success", true);
            result.put("user", user);
        } catch (Exception e) {
            log.error("사용자 조회 중 오류 발생", e);
            result.put("success", false);
            result.put("message", "사용자 조회 중 오류가 발생했습니다.");
        }
        return gson.toJson(result);
    }
} 