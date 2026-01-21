package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("resumeCareerVO")
public class ResumeCareerVO {
    private String resumeCareerId;
    private String companyName;
    private String companyRole;
    private String companyWork;
    private String companyStartTerm;
    private String companyEndTerm;
    private String createdTime;
    private String resumeId;
}
