package com.corelabs.baccara.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("baccaraVO")
public class BaccaraVO extends UserVO {
	private String uid;
	private String win;
	private String gameNum;
	private String pair;
	private String pairB;
	private String pairP;
	private String createDt;
}
