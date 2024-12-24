package com.corelabs.menu.service;

import com.corelabs.menu.mapper.MenuMapper;
import com.corelabs.menu.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    final
    MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuVO> selectParentMenuList(MenuVO menuVO) {
        return menuMapper.selectParentMenuList(menuVO);
    }

    public List<MenuVO> selectChildMenuList(MenuVO menuVO) {
        return menuMapper.selectChildMenuList(menuVO);
    }

}