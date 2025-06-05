package com.corelabs.ase.service;

import com.corelabs.ase.mapper.AseAuthMapper;
import com.corelabs.ase.vo.AseAuthVO;
import com.corelabs.ase.vo.AseMenuVO;
import com.corelabs.ase.vo.AseUserVO;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("aseAuthService")
public class AseAuthService {

    private final AseAuthMapper aseAuthMapper;

    public AseAuthService(AseAuthMapper aseAuthMapper) {
        this.aseAuthMapper = aseAuthMapper;
    }

    public List<AseAuthVO> selectAuthList(AseAuthVO aseAuthVO) {
        return aseAuthMapper.selectAuthList(aseAuthVO);
    }

    public AseAuthVO selectAuthByAuthId(String authId) {
        AseAuthVO searchVO = new AseAuthVO();
        searchVO.setAuthId(authId);
        List<AseAuthVO> authList = aseAuthMapper.selectAuthList(searchVO);
        return authList.isEmpty() ? null : authList.get(0);
    }

    @Transactional
    public int saveAuth(AseAuthVO aseAuthVO) {
        // 기존 권한 조회
        List<AseAuthVO> existingAuths = aseAuthMapper.selectAuthList(new AseAuthVO());
        boolean isUpdate = existingAuths.stream()
                .anyMatch(auth -> auth.getAuthId().equals(aseAuthVO.getAuthId()));

        int resultCnt;
        if (isUpdate) {
            // 기존 권한이 있으면 update
            resultCnt = aseAuthMapper.updateAuth(aseAuthVO);
        } else {
            // 기존 권한이 없으면 insert
            resultCnt = aseAuthMapper.insertAuth(aseAuthVO);
        }

        return resultCnt;
    }

    public boolean checkAuthInUse(String authId) {
        return aseAuthMapper.checkAuthInUse(authId);
    }

    @Transactional
    public void deleteAuth(String authId) {
        // 권한이 사용 중인지 확인
        boolean isInUse = aseAuthMapper.checkAuthInUse(authId);
        if (isInUse) {
            throw new IllegalStateException("해당 권한은 현재 사용자에게 할당되어 있어 삭제할 수 없습니다.");
        }

        // 권한이 메뉴에 할당되어 있는지 확인
        List<AseMenuVO> menuList = aseAuthMapper.selectAuthMenuList(null, authId, null);
        if (menuList != null && !menuList.isEmpty()) {
            throw new IllegalStateException("해당 권한은 현재 메뉴에 할당되어 있어 삭제할 수 없습니다.");
        }

        // 사용 중이지 않은 경우에만 삭제
        aseAuthMapper.deleteAuth(authId);
    }

    public List<AseMenuVO> selectAuthMenuList(String authId) {
        return aseAuthMapper.selectAuthMenuList(null, authId, null);
    }

    public List<AseUserVO> selectAuthUserList(String authId) {
        return aseAuthMapper.selectAuthUserList(authId);
    }

    @Transactional
    public void saveAuthMenu(String authId, List<String> menuIds) {
        // 기존 권한-메뉴 매핑 삭제 처리 (del_yn = 'Y'로 업데이트)
        aseAuthMapper.deleteAuthMenu(authId);
        
        // 새로운 권한-메뉴 매핑 저장
        if (menuIds != null && !menuIds.isEmpty()) {
            for (String menuId : menuIds) {
                // 이미 존재하는 매핑인 경우 del_yn을 'N'으로 업데이트
                aseAuthMapper.updateAuthMenu(authId, menuId);
                // 존재하지 않는 매핑인 경우 새로 삽입
                aseAuthMapper.insertAuthMenu(authId, menuId);
            }
        }
    }

    @Transactional
    public void saveAuthUser(String authId, List<String> userIds, List<String> deletedUserIds) {
        // 삭제된 사용자 처리
        if (deletedUserIds != null && !deletedUserIds.isEmpty()) {
            for (String userId : deletedUserIds) {
                aseAuthMapper.deleteAuthUser(userId);
            }
        }
        
        // 새로운 사용자 권한 저장
        if (userIds != null && !userIds.isEmpty()) {
            aseAuthMapper.insertAuthUser(authId, userIds);
        }
    }

    public List<AseMenuVO> selectAuthMenuList(String menuNm, String authId, String useYn) {
        return aseAuthMapper.selectAuthMenuList(menuNm, authId, useYn);
    }

    public AseUserVO selectAuthUser(String userUid) {
        return aseAuthMapper.selectAuthUser(userUid);
    }

    public void deleteAuthUser(String userUid) {
        aseAuthMapper.deleteAuthUser(userUid);
    }

    public List<AseMenuVO> selectMenuList(String authId) {
        return aseAuthMapper.selectMenuList(authId);
    }

    public AseUserVO selectUser(String userUid) {
        return aseAuthMapper.selectUser(userUid);
    }
} 