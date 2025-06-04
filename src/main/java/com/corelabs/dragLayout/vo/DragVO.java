package com.corelabs.dragLayout.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Alias("dragVO")
public class DragVO {

	private String userNm;

	private String userUid;

	private String status;

	private String grade;

	private String gender;

	private String courtNumber;

	private String matchStartDateTime;

	private String matchEndDateTime;

	private String matchDuration;

	private String createUserId;

	private String createDt;

	private String updateUserId;

	private String updateDt;

	private Integer groupNo; // New field


}
