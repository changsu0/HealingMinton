package com.corelabs.tournament.vo;

import com.corelabs.user.vo.UserVO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("tournamentVO")
public class TournamentVO extends UserVO {
	private String tournamentUid;
	private String tournamentTitle;
	private String tournamentStDt;
	private String tournamentEnDt;
	private String oriFileNm;
	private String newFileNm;
	private String fullFileNm;
	private String delYn;
}
