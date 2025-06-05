package com.corelabs.ase.mapper;

import com.corelabs.ase.vo.AseUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AseUserMapper {

	List<AseUserVO> selectUserList(AseUserVO aseUserVO);
	boolean checkUserExists(String userUid);
	int insertUser(AseUserVO aseUserVO);
	int updateUser(AseUserVO aseUserVO);

	boolean checkUserAuthExists(@Param("userUid") String userUid, @Param("authId") String authId);
	int insertUserAuth(AseUserVO aseUserVO);
	int updateUserAuth(AseUserVO aseUserVO);
	List<AseUserVO> selectUserAuthList(@Param("userUid") String userUid);

	AseUserVO selectUserByUid(String userUid);

	void deleteUser(String userUid);

	void deleteUserAuth(String userUid);
}
