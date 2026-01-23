package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("commonDetail")
public class CommonDetail {
    String comCd;
    String dtlCd;
    String dtlNm;
    String dtlDesc;
    int dtlOrderNo;
    String useYn;
    String createUserId;
    String createDt;
    String updateUserId;
    String updateDt;
}
