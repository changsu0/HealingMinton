package com.corelabs.tournament.mapper;

import com.corelabs.tournament.vo.TournamentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TournamentMapper {
	List<TournamentVO> selectTournamentList(TournamentVO tournamentVO);
	int insertTournament(TournamentVO tournamentVO);
	int deleteTournament(TournamentVO tournamentVO);
}
