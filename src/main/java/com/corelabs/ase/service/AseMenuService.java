package com.corelabs.ase.service;

import com.corelabs.ase.mapper.AseMenuMapper;
import com.corelabs.ase.vo.AseMenuVO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AseMenuService {

    final AseMenuMapper aseMenuMapper;

    public AseMenuService(AseMenuMapper aseMenuMapper) {
        this.aseMenuMapper = aseMenuMapper;
    }

    
    public List<AseMenuVO> selectMenuList(AseMenuVO aseMenuVO) {
        return aseMenuMapper.selectMenuList(aseMenuVO);
    }

    public int saveMenu(AseMenuVO aseMenuVO) {
        int resultCnt = 0;

        // Check if the menu exists in the tb_menu table
        boolean menuExists = aseMenuMapper.checkMenuExists(aseMenuVO.getMenuId());

        if (menuExists) {
            // Update the existing menu
            resultCnt = aseMenuMapper.updateMenu(aseMenuVO);
        } else {
            // Insert a new menu
            resultCnt = aseMenuMapper.insertMenu(aseMenuVO);
        }

        return resultCnt;
    }

    public void updateMenuId(String oldMenuId, String newMenuId) {
        // 중복 체크
        AseMenuVO checkMenu = new AseMenuVO();
        checkMenu.setMenuId(newMenuId);
        List<AseMenuVO> existingMenus = aseMenuMapper.selectMenuList(checkMenu);
        if (!existingMenus.isEmpty()) {
            throw new RuntimeException("이미 존재하는 메뉴 ID입니다.");
        }

        // 상위 메뉴 참조 업데이트
        aseMenuMapper.updateParentMenuId(oldMenuId, newMenuId);
        
        // 메뉴 ID 업데이트
        aseMenuMapper.updateMenuId(oldMenuId, newMenuId);
    }

    public void deleteMenu(String menuId) {
        // 메뉴 삭제
        aseMenuMapper.deleteMenu(menuId);
    }

    public AseMenuVO selectMenuByMenuId(String menuId) {
        System.out.println("Service: Selecting menu by ID: " + menuId);
        try {
            AseMenuVO menu = aseMenuMapper.selectMenuByMenuId(menuId);
            System.out.println("Service: Found menu: " + (menu != null ? menu.toString() : "null"));
            return menu;
        } catch (Exception e) {
            System.err.println("Service Error in selectMenuByMenuId: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
