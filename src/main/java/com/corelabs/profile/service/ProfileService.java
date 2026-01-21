package com.corelabs.profile.service;

import com.corelabs.profile.mapper.ProfileMapper;
import com.corelabs.profile.vo.AttachmentVO;
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

    public List<ProfileVO> selectProfileList() {
        return profileMapper.selectProfileList();
    }

    @Transactional
    public void saveResume(ProfileVO profileVO, MultipartFile photo, List<MultipartFile> attaches) {
        String uuid = UUID.randomUUID().toString();
        if (photo != null && !photo.isEmpty()) {
            String baseDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "profile" + File.separator;
            // 1. 고유한 파일명 생성 (UUID 사용)
            String originalFilename = photo.getOriginalFilename();
            String saveFilename = uuid + "_" + originalFilename;

            // 2. 물리적 폴더에 파일 저장
            try {
                // 폴더가 없으면 생성
                File directory = new File(baseDir);
                if(!directory.exists()) directory.mkdirs();

                File target = new File(baseDir + saveFilename);
                photo.transferTo(target);

                // 3. DB에 저장할 상대 경로 설정
                //profileVO.setPhotoPath("/upload/profile/" + saveFilename);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
        }

        profileVO.setProfileId(uuid);
        profileMapper.insertProfile(profileVO);

        if (attaches != null && !attaches.isEmpty()) {
            String baseDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "files" + File.separator;

            for (MultipartFile file : attaches) {
                if (file.isEmpty()) continue;

                String saveFilename = uuid + "_" + file.getOriginalFilename();

                try {
                    File target = new File(baseDir, saveFilename);
                    if(!target.getParentFile().exists()) target.getParentFile().mkdirs();
                    file.transferTo(target);

                    AttachmentVO attachmentVO = new AttachmentVO();
                    attachmentVO.setProfileId(uuid);
                    attachmentVO.setOriginalName(file.getOriginalFilename());
                    attachmentVO.setSaveName(saveFilename);
                    attachmentVO.setFilePath("/upload/files/" + saveFilename);

                    profileMapper.insertAttachment(attachmentVO);
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패", e);
                }
            }
        }
    }



}