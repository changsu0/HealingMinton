package com.corelabs.ase.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("aseMenuVO")
public class AseMenuVO {
	private String menuId;
	private String parentMenuId;
	private String menuNm;
	private String menuUrl;
	private String menuOrder;
	private String useYn;
	private String createUserId;
	private String createDt;
	private String updateUserId;
	private String updateDt;
}
