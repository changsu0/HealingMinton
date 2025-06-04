package com.corelabs.ase.mapper;

import com.corelabs.ase.vo.AseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AseMapper {
	List<AseVO> selectUserList(AseVO aseVO);
	boolean checkUserExists(String userUid);
	int insertUser(AseVO aseVO);
	int updateUser(AseVO aseVO);


	List<AseVO> selectMenuList(AseVO aseVO);
	boolean checkMenuExists(String menuId);
	int insertMenu(AseVO aseVO);
	int updateMenu(AseVO aseVO);


	List<AseVO> selectAuthList(AseVO aseVO);
	boolean checkAuthExists(String authId);
	int insertAuth(AseVO aseVO);
	int updateAuth(AseVO aseVO);


	boolean checkUserAuthExists(@Param("userUid") String userUid, @Param("authId") String authId);
	int insertUserAuth(AseVO aseVO);
	int updateUserAuth(AseVO aseVO);
	List<AseVO> selectUserAuthList(@Param("userUid") String userUid);



	boolean checkMenuAuthExists(@Param("menuId") String menuId, @Param("authId") String authId);
	int insertMenuAuth(AseVO aseVO);
	int updateMenuAuth(AseVO aseVO);
	List<AseVO> selectMenuAuthList(@Param("menuId") String menuId);

	AseVO selectUserByUid(String userUid);

	void deleteUser(String userUid);

	void deleteUserAuth(String userUid);
}
