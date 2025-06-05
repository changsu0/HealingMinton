package com.corelabs.ase.service;

import com.corelabs.ase.mapper.AseUserMapper;
import com.corelabs.ase.vo.AseUserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AseUserService {

    final AseUserMapper aseUserMapper;

    public AseUserService(AseUserMapper aseUserMapper) {
        this.aseUserMapper = aseUserMapper;
    }

    public List<AseUserVO> selectUserList(AseUserVO aseUserVO) {
        return aseUserMapper.selectUserList(aseUserVO);
    }

    public int saveUser(AseUserVO aseUserVO) {
        int resultCnt = 0;

        // Check if the user exists in the tb_user table
        boolean userExists = aseUserMapper.checkUserExists(aseUserVO.getUserUid());

        if (userExists) {
            // Update the existing user
            resultCnt = aseUserMapper.updateUser(aseUserVO);
        } else {
            // Insert a new user
            resultCnt = aseUserMapper.insertUser(aseUserVO);
        }

        return resultCnt;
    }

    public AseUserVO getUserByUid(String userUid) {
        return aseUserMapper.selectUserByUid(userUid);
    }

    public void deleteUser(String userUid) {
        aseUserMapper.deleteUserAuth(userUid); // Delete associated auth records
        aseUserMapper.deleteUser(userUid);    // Delete the user
    }


    public int assignSingleAuthToUser(String userUid, String authId) {
        AseUserVO aseUserVO = new AseUserVO();
        aseUserVO.setUserUid(userUid);
        //aseUserVO.setAuthId(authId);

        // Check if the user already has the permission
        boolean authExists = aseUserMapper.checkUserAuthExists(userUid, authId);

        if (authExists) {
            // Update the existing permission
            return aseUserMapper.updateUserAuth(aseUserVO);
        } else {
            // Assign a new permission
            return aseUserMapper.insertUserAuth(aseUserVO);
        }
    }

    public List<AseUserVO> selectUserAuthList(String userUid) {
        return aseUserMapper.selectUserAuthList(userUid);
    }

}