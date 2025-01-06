package com.corelabs.tournament.service;

import com.corelabs.tournament.mapper.TournamentMapper;
import com.corelabs.tournament.vo.TournamentVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    final
    TournamentMapper tournamentMapper;

    public TournamentService(TournamentMapper tournamentMapper) {
        this.tournamentMapper = tournamentMapper;
    }

    public List<TournamentVO> selectTournamentList(TournamentVO tournamentVO) {
        return tournamentMapper.selectTournamentList(tournamentVO);
    }
    public int insertTournament(TournamentVO tournamentVO) {
        // TODO : LoginUser 정보를 가져와서 넣어줘야 함
        tournamentVO.setCreateUserId("LoginUser");
        tournamentVO.setUpdateUserId("LoginUser");
        return tournamentMapper.insertTournament(tournamentVO);
    }
    public int deleteTournament(TournamentVO tournamentVO) {
        tournamentVO.setCreateUserId("LoginUser");
        tournamentVO.setUpdateUserId("LoginUser");
        return tournamentMapper.deleteTournament(tournamentVO);
    }

}