package com.corelabs.resume.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class ResumeSearchVO {

    private String title;
    private String createdUser;
    private String createdTime;
    private String updatedTime;
    private String name;
    private String birth;
    private String gender;
    private String military;
    private String role;
    private String marriage;
    private String landlineNumber;
    private String phoneNumber;
    private String email;
    private String address;

    private String schoolName;
    private String schoolStatus;
    private String schoolDate;

    private String companyName;
    private String companyRole;
    private String companyWork;
    private String companyStartTerm;
    private String companyEndTerm;

    private String licenseName;
    private String licenseIssuer;
    private String licenseDate;

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
}
