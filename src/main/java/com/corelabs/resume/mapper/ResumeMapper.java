package com.corelabs.resume.mapper;

import com.corelabs.resume.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResumeMapper {
    // 조회
    List<ResumeVO> selectResumeList();

    ResumeVO selectResumeById(String resumeId);

    // 저장
    int insertResume(ResumeVO resumeVO);
    int insertEducation(ResumeEducationVO resumeEducationVO);
    int insertCareer(ResumeCareerVO resumeCareerVO);
    int insertLicense(ResumeLicenseVO resumeLicenseVO);
    int insertSkill(ResumeSkillVO resumeSkillVO);

    // 수정
    int updateResume(ResumeVO resumeVO);

    // 삭제
    int deleteResume(String resumeId);
    int deleteEducation(String resumeId);
    int deleteCareer(String resumeId);
    int deleteLicense(String resumeId);
    int deleteSkill(String resumeId);

}
