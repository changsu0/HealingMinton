package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("resumeSkillVO")
public class ResumeSkillVO {
    private int resumeSkillId;
    private String projectName;
    private String projectCustomer;
    private String projectCompany;
    private String projectWork;
    private String projectLanguage;
    private String projectDb;
    private String projectTool;
    private String projectFramework;
    private String projectStartTerm;
    private String projectEndTerm;
    private String projectWorkDetail;
    private String projectEtc;
    private String createdTime;
    private String resumeId;
}
