package com.corelabs.profile.service;

import com.corelabs.profile.mapper.ProfileMapper;
import com.corelabs.profile.vo.ProfileVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {

    final ProfileMapper profileMapper;

    public ProfileService(ProfileMapper profileMapper) { this.profileMapper = profileMapper; }

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "resume" + File.separator;

    public List<ProfileVO> selectProfileList() {
        return profileMapper.selectProfileList();
    }

    @Transactional
    public void saveResume(ProfileVO profileVO, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        if (file != null && !file.isEmpty()) {
            // 1. 고유한 파일명 생성 (UUID 사용)
            String originalFilename = file.getOriginalFilename();
            String saveFilename = uuid + "_" + originalFilename;

            // 2. 물리적 폴더에 파일 저장
            try {
                // 폴더가 없으면 생성
                File directory = new File(uploadDir);
                if(!directory.exists()) directory.mkdirs();

                File target = new File(uploadDir + saveFilename);
                file.transferTo(target);

                // 3. DB에 저장할 상대 경로 설정
                profileVO.setPhotoPath("/upload/resume/" + saveFilename);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
        }

        profileVO.setProfileId(uuid);
        // 4. DB 저장 (기존 Mapper 호출)
        profileMapper.insertProfile(profileVO);
    }

}