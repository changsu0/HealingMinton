package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.util.List;

@Data
@Alias("resumeVO")
public class ResumeVO {
    // UUID
    private String resumeId;
    private String title;
    private String createdUser;
    private Date createdTime;
    private Date updatedTime;
    private String name;
    private Date birth;
    private String gender;
    private String military;
    private String role;
    private String marriage;
    private String landlineNumber;
    private String phoneNumber;
    private String email;
    private String address;

    private List<ResumeEducationVO> educationList;
    private List<ResumeCareerVO> careerList;
    private List<ResumeLicenseVO> licenseList;
    private List<ResumeSkillVO> skillList;
}
