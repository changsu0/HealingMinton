package com.corelabs.common.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("commonVO")
public class CommonVO {
	private String createUserId;
	private String createDt;
	private String updateUserId;
	private String updateDt;
	private String stDt;
	private String enDt;
}
