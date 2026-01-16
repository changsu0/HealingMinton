package com.corelabs.resume.mapper;

import com.corelabs.resume.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResumeMapper {

    List<ResumeVO> selectResumeList();
    ResumeVO selectResumeById(String resumeId);
    List<ResumeVO> selectResumeList(String searchType, String keyword);
    int insertResume(ResumeVO resumeVO);
    int updateResume(ResumeVO resumeVO);
    int deleteResume(String resumeId);

    int insertEducation(ResumeEducationVO resumeEducationVO);
    int deleteEducation(String resumeId);

    int insertCareer(ResumeCareerVO resumeCareerVO);
    int deleteCareer(String resumeId);

    int insertLicense(ResumeLicenseVO resumeLicenseVO);
    int deleteLicense(String resumeId);

    int insertSkill(ResumeSkillVO resumeSkillVO);
    int deleteSkill(String resumeId);

}
