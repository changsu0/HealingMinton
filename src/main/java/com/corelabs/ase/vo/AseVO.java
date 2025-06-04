package com.corelabs.ase.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("aseVO")
public class AseVO {
	private String userUid;
	private String userNm;
	private String userPhone;
	private String userLoc;
	private String userRank;
	private String userSex;
	private String userBirth;
	private String userDescShort;
	private String userDesc;

	private String menuId;
	private String parentMenuId;
	private String menuNm;
	private String menuUrl;
	private String menuIcon;
	private String menuOrder;
	private String isVisible;
	private String isActive;

	private String authId;
	private String authNm;
	private String authDescription;

	private String createUserId;
	private String createDt;
	private String updateUserId;
	private String updateDt;

}
