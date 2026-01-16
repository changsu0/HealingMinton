package com.corelabs.profile.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Alias("newResumeVO")
public class NewResumeVO {
    private String resumeId;
    private String title;
    private String createdUser;
    private String name;
    private LocalDate birth;
    private String gender;
    private String military;
    private String role;
    private String marriage;
    private String landlineNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String photoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
