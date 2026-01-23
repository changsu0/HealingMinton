package com.corelabs.resume.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("commonMaster")
public class CommonMaster {
    String comCd;
    String comNm;
    String comDesc;
    int orderNo;
    String useYn;
    String createUserId;
    String createDt;
    String updateUserId;
    String updateDt;
}
