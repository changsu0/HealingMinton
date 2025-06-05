package com.corelabs.ase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.corelabs.ase.vo.AseMenuVO;

@Mapper
public interface AseMenuMapper {
	List<AseMenuVO> selectMenuList(AseMenuVO aseMenuVO);
	boolean checkMenuExists(String menuId);
	int insertMenu(AseMenuVO aseMenuVO);
	int updateMenu(AseMenuVO aseMenuVO);
	void updateMenuId(@Param("oldMenuId") String oldMenuId, @Param("newMenuId") String newMenuId);
	void updateParentMenuId(@Param("oldMenuId") String oldMenuId, @Param("newMenuId") String newMenuId);
	void deleteMenu(@Param("menuId") String menuId);
	AseMenuVO selectMenuByMenuId(String menuId);
	void insertSystemMenu();
}
