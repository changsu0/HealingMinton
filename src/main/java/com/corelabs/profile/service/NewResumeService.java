package com.corelabs.profile.service;

import com.corelabs.profile.mapper.NewResumeMapper;
import com.corelabs.profile.vo.NewResumeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class NewResumeService {

    final NewResumeMapper newResumeMapper;

    public NewResumeService(NewResumeMapper newResumeMapper) { this.newResumeMapper = newResumeMapper; }

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "resume" + File.separator;

    public List<NewResumeVO> selectResumeList() {
        return newResumeMapper.selectResumeList();
    }

    @Transactional
    public void saveResume(NewResumeVO newResumeVO, MultipartFile file) {
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
                newResumeVO.setPhotoPath("/upload/resume/" + saveFilename);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }
        }

        newResumeVO.setResumeId(uuid);
        // 4. DB 저장 (기존 Mapper 호출)
        newResumeMapper.insertResume(newResumeVO);
    }

}