package com.corelabs.ase.vo;

import lombok.Data;

@Data
public class AseAuthVO {
    private String authId;
    private String authNm;
    private String authDescription;
    private String useYn;
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
} 