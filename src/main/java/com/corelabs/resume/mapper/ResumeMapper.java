package com.corelabs.resume.mapper;

import com.corelabs.resume.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResumeMapper {

    ResumeVO selectResumeById(String resumeId);
    List<Map<String, Object>> selectResumeList(List<String> resumeIds);
    int insertResume(ResumeVO resumeVO);
    int updateResume(ResumeVO resumeVO);
    int deleteResume(String resumeId);

    List<ResumeEducationVO> selectEducationById(String resumeId);
    int insertEducation(ResumeEducationVO resumeEducationVO);
    int deleteEducation(String resumeId);

    List<ResumeCareerVO> selectCareerById(String resumeId);
    int insertCareer(ResumeCareerVO resumeCareerVO);
    int deleteCareer(String resumeId);

    List<ResumeLicenseVO> selectLicenseById(String resumeId);
    int insertLicense(ResumeLicenseVO resumeLicenseVO);
    int deleteLicense(String resumeId);

    List<ResumeSkillVO> selectSkillById(String resumeId);
    int insertSkill(ResumeSkillVO resumeSkillVO);
    int deleteSkill(String resumeId);

    List<String> selectResumeIdList(ResumeSearchVO resumeSearchVO);
}
