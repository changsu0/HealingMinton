package com.corelabs.ase.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = true)
@Alias("aseUserVO")
public class AseUserVO extends AseAuthVO {
	private String userUid;
	private String userNm;
	private String userPhone;
	private String userLoc;
	private String userRank;
	private String userSex;
	private String userBirth;
	private String userDescShort;
	private String userDesc;

}
