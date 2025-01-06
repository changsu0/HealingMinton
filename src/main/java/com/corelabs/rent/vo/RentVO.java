package com.corelabs.rent.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("rentVO")
public class RentVO extends UserVO {
	private String rentUid;
	private String rentTitle;
	private String rentStDt;
	private String rentEnDt;
	private String delYn;
}
