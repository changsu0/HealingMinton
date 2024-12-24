package com.corelabs.ticket.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ticketVO")
public class TicketVO extends UserVO {
	private String ticketRegUid;
	private String regCnt;
	private String regPrice;
	private String regDt;
	private String regDesc;
	private String delYn;

	private String ticketUseUid;
	private String useCnt;
	private String useDt;
	private String useDesc;

	private String remainCnt;
	private String lastDt;
}
