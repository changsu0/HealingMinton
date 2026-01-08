package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("resumeEducationVO")
public class ResumeEducationVO {
    private int resumeEducationId;
    private String schoolName;
    private String schoolStatus;
    private Date schoolDate;
    private Date createdTime;
    private String resumeId;
}
