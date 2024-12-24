
package com.corelabs.user.vo;

import com.corelabs.common.vo.CommonVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userVO")
public class UserVO extends CommonVO {

	private String userUid;
	private String userNm;
	private String userPhone;
	private String userLoc;
	private String userRank;
	private String userSex;
	private String userBirth;
	private String userAge;
	private String userDescShort;
	private String userDesc;
	private String ticketCnt;

}
