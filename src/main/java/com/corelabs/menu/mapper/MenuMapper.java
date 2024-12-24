package com.corelabs.menu.mapper;

import com.corelabs.menu.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuVO> selectParentMenuList(MenuVO menuVO);
    List<MenuVO> selectChildMenuList(MenuVO menuVO);
}
