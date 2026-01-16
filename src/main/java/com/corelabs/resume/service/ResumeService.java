package com.corelabs.resume.service;

import com.corelabs.resume.mapper.ResumeMapper;
import com.corelabs.resume.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ResumeService {

    final ResumeMapper resumeMapper;

    public ResumeService(ResumeMapper resumeMapper) { this.resumeMapper = resumeMapper; }

    public List<ResumeVO> selectResumeList(ResumeVO resumeVO) {
        return resumeMapper.selectResumeList();
    }

    public List<ResumeVO> selectResumeList() {
        return resumeMapper.selectResumeList();
    }

//    public List<ResumeVO> selectResumeList(String searchType, String keyword) {
//        return resumeMapper.selectResumeList(searchType, keyword);
//    }

    public List<ResumeVO> selectResumeList(ResumeSearchVO resumeSearchVO) {
        return resumeMapper.selectResumeList(resumeSearchVO);
    }

    public ResumeVO selectResumeById(String resumeId) {
        return resumeMapper.selectResumeById(resumeId);
    }

    @Transactional
    public int saveResume(ResumeVO resumeVO) {
        int resultCnt = 0;

        String uuid = UUID.randomUUID().toString();
        resumeVO.setResumeId(uuid);

        resultCnt = resumeMapper.insertResume(resumeVO);

        insertEducation(resumeVO, uuid);
        insertCareer(resumeVO, uuid);
        insertLicense(resumeVO, uuid);
        insertSkill(resumeVO, uuid);

        return resultCnt;
    }

    @Transactional
    public int updateResume(ResumeVO resumeVO) {
        int resultCnt = 0;

        resultCnt = resumeMapper.updateResume(resumeVO);

        String resumeId = resumeVO.getResumeId();
        deleteRelatedData(resumeId);

        insertEducation(resumeVO, resumeId);
        insertCareer(resumeVO, resumeId);
        insertLicense(resumeVO, resumeId);
        insertSkill(resumeVO, resumeId);

        return resultCnt;
    }

    public void deleteResume(String resumeId) {
        resumeMapper.deleteSkill(resumeId);
        resumeMapper.deleteLicense(resumeId);
        resumeMapper.deleteCareer(resumeId);
        resumeMapper.deleteEducation(resumeId);
        resumeMapper.deleteResume(resumeId);
    }

    private void deleteRelatedData(String resumeId) {
        resumeMapper.deleteSkill(resumeId);
        resumeMapper.deleteLicense(resumeId);
        resumeMapper.deleteCareer(resumeId);
        resumeMapper.deleteEducation(resumeId);
    }

    private void insertEducation(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getEducationList() != null && !resumeVO.getEducationList().isEmpty()) {
            for (ResumeEducationVO resumeEducationVO : resumeVO.getEducationList()) {
                resumeEducationVO.setResumeId(resumeId);
                resumeMapper.insertEducation(resumeEducationVO);
            }
        }
    }

    private void insertCareer(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getCareerList() != null && !resumeVO.getCareerList().isEmpty()) {
            for (ResumeCareerVO resumeCareerVO : resumeVO.getCareerList()) {
                resumeCareerVO.setResumeId(resumeId);
                resumeMapper.insertCareer(resumeCareerVO);
            }
        }
    }

    private void insertLicense(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getLicenseList() != null && !resumeVO.getLicenseList().isEmpty()) {
            for (ResumeLicenseVO resumeLicenseVO : resumeVO.getLicenseList()) {
                resumeLicenseVO.setResumeId(resumeId);
                resumeMapper.insertLicense(resumeLicenseVO);
            }
        }
    }

    private void insertSkill(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getSkillList() != null && !resumeVO.getSkillList().isEmpty()) {
            for (ResumeSkillVO resumeSkillVO : resumeVO.getSkillList()) {
                resumeSkillVO.setResumeId(resumeId);
                resumeMapper.insertSkill(resumeSkillVO);
            }
        }
    }

}