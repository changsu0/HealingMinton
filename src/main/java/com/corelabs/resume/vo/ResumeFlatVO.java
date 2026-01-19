package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("resumeFlatVO")
public class ResumeFlatVO {

    private String resumeId;
    private String name;
    private Date birth;
    private String military;
    private String marriage;
    private String address;
    private String schoolName;
    private String schoolStatus;
    private String schoolDate;
    private String licenseName;
    private Date companyStartTerm;
    private Date companyEndTerm;
    private String projectLanguage;
    private Date createdTime;
    private Date updatedTime;
}
