package com.corelabs.ase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.corelabs.ase.vo.AseAuthVO;
import com.corelabs.ase.vo.AseMenuVO;
import com.corelabs.ase.vo.AseUserVO;

@Mapper
public interface AseAuthMapper {
    List<AseAuthVO> selectAuthList(AseAuthVO aseAuthVO);
    boolean checkAuthInUse(String authId);
    int insertAuth(AseAuthVO aseAuthVO);
    int updateAuth(AseAuthVO aseAuthVO);
    void deleteAuth(@Param("authId") String authId);
    List<AseMenuVO> selectAuthMenuList(@Param("menuNm") String menuNm, 
                                      @Param("authId") String authId, 
                                      @Param("useYn") String useYn);
    List<AseUserVO> selectAuthUserList(@Param("authId") String authId);
    void deleteAuthMenu(@Param("authId") String authId);
    void insertAuthMenu(@Param("authId") String authId, @Param("menuId") String menuId);
    void updateAuthMenu(@Param("authId") String authId, @Param("menuId") String menuId);
    void deleteAuthUser(@Param("userUid") String userUid);
    void insertAuthUser(@Param("authId") String authId, @Param("userIds") List<String> userIds);
    AseUserVO selectAuthUser(@Param("userUid") String userUid);
    List<AseMenuVO> selectMenuList(@Param("authId") String authId);
    AseUserVO selectUser(@Param("userUid") String userUid);
} 