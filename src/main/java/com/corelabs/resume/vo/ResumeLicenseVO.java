package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@Alias("resumeLicenseVO")
public class ResumeLicenseVO {
    private int resumeLicenseId;
    private String licenseName;
    private String licenseIssuer;
    private String licenseDate;
    private String createdTime;
    private String resumeId;
}
