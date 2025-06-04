package com.corelabs.ase.service;

import com.corelabs.ase.mapper.AseMapper;
import com.corelabs.ase.vo.AseVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AseService {

    final AseMapper aseMapper;

    public AseService(AseMapper aseMapper) {
        this.aseMapper = aseMapper;
    }

    public List<AseVO> selectUserList(AseVO aseVO) {
        return aseMapper.selectUserList(aseVO);
    }

    public int saveUser(AseVO aseVO) {
        int resultCnt = 0;

        // Check if the user exists in the tb_user table
        boolean userExists = aseMapper.checkUserExists(aseVO.getUserUid());

        if (userExists) {
            // Update the existing user
            resultCnt = aseMapper.updateUser(aseVO);
        } else {
            // Insert a new user
            resultCnt = aseMapper.insertUser(aseVO);
        }

        return resultCnt;
    }

    public AseVO getUserByUid(String userUid) {
        return aseMapper.selectUserByUid(userUid);
    }

    public void deleteUser(String userUid) {
        aseMapper.deleteUserAuth(userUid); // Delete associated auth records
        aseMapper.deleteUser(userUid);    // Delete the user
    }

    public List<AseVO> selectMenuList(AseVO aseVO) {
        return aseMapper.selectMenuList(aseVO);
    }

    public int saveMenu(AseVO aseVO) {
        int resultCnt = 0;

        // Check if the menu exists in the tb_menu table
        boolean menuExists = aseMapper.checkMenuExists(aseVO.getMenuId());

        if (menuExists) {
            // Update the existing menu
            resultCnt = aseMapper.updateMenu(aseVO);
        } else {
            // Insert a new menu
            resultCnt = aseMapper.insertMenu(aseVO);
        }

        return resultCnt;
    }

    public List<AseVO> selectAuthList(AseVO aseVO) {
        return aseMapper.selectAuthList(aseVO);
    }

    public int saveAuth(AseVO aseVO) {
        int resultCnt = 0;

        // Check if the auth record exists in the tb_auth table
        boolean authExists = aseMapper.checkAuthExists(aseVO.getAuthId());

        if (authExists) {
            // Update the existing auth record
            resultCnt = aseMapper.updateAuth(aseVO);
        } else {
            // Insert a new auth record
            resultCnt = aseMapper.insertAuth(aseVO);
        }

        return resultCnt;
    }


    public int assignSingleAuthToUser(String userUid, String authId) {
        AseVO aseVO = new AseVO();
        aseVO.setUserUid(userUid);
        aseVO.setAuthId(authId);

        // Check if the user already has the permission
        boolean authExists = aseMapper.checkUserAuthExists(userUid, authId);

        if (authExists) {
            // Update the existing permission
            return aseMapper.updateUserAuth(aseVO);
        } else {
            // Assign a new permission
            return aseMapper.insertUserAuth(aseVO);
        }
    }

    public List<AseVO> selectUserAuthList(String userUid) {
        return aseMapper.selectUserAuthList(userUid);
    }


    public int assignAuthToMenu(String menuId, List<String> authIds) {
        int resultCnt = 0;
        for (String authId : authIds) {
            AseVO aseVO = new AseVO();
            aseVO.setMenuId(menuId);
            aseVO.setAuthId(authId);

            // Check if the menu already has the permission
            boolean authExists = aseMapper.checkMenuAuthExists(menuId, authId);

            if (authExists) {
                // Update the existing permission (optional)
                resultCnt += aseMapper.updateMenuAuth(aseVO);
            } else {
                // Assign a new permission
                resultCnt += aseMapper.insertMenuAuth(aseVO);
            }
        }
        return resultCnt;
    }

    public List<AseVO> selectMenuAuthList(String menuId) {
        return aseMapper.selectMenuAuthList(menuId);
    }
}