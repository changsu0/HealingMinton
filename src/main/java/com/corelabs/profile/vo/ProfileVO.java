package com.corelabs.profile.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("profileVO")
public class ProfileVO {
    private String profileId;
    private String title;
    private String createdUser;
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
    private String photoPath;
    private String createdAt;
    private String updatedAt;
}
