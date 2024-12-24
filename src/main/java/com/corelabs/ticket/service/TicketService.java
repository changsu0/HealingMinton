package com.corelabs.ticket.service;

import com.corelabs.ticket.mapper.TicketMapper;
import com.corelabs.ticket.vo.TicketVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    final
    TicketMapper ticketMapper;

    public TicketService(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public List<TicketVO> selectTicketRegList(TicketVO ticketVO) {
        return ticketMapper.selectTicketRegList(ticketVO);
    }
    public int insertTicketRegHist(TicketVO ticketVO) {
        // TODO : LoginUser 정보를 가져와서 넣어줘야 함
        ticketVO.setCreateUserId("LoginUser");
        ticketVO.setUpdateUserId("LoginUser");
        return ticketMapper.insertTicketRegHist(ticketVO);
    }
    public int deleteTicketRegHist(TicketVO ticketVO) {
        ticketVO.setCreateUserId("LoginUser");
        ticketVO.setUpdateUserId("LoginUser");
        return ticketMapper.deleteTicketRegHist(ticketVO);
    }

    public List<TicketVO> selectTicketUseList(TicketVO ticketVO) {
        return ticketMapper.selectTicketUseList(ticketVO);
    }
    public int insertTicketUseHist(TicketVO ticketVO) {
        ticketVO.setCreateUserId("LoginUser");
        ticketVO.setUpdateUserId("LoginUser");
        return ticketMapper.insertTicketUseHist(ticketVO);
    }
    public int deleteTicketUseHist(TicketVO ticketVO) {
        ticketVO.setCreateUserId("LoginUser");
        ticketVO.setUpdateUserId("LoginUser");
        return ticketMapper.deleteTicketUseHist(ticketVO);
    }

    public TicketVO selectUserTicketCnt(TicketVO ticketVO) {
        return ticketMapper.selectUserTicketCnt(ticketVO);
    }

    public List<TicketVO> selectUserTicketRemainList(TicketVO ticketVO) {
        return ticketMapper.selectUserTicketRemainList(ticketVO);
    }

    public List<TicketVO> selectUserTicketRemainSameDayList(TicketVO ticketVO) {
        return ticketMapper.selectUserTicketRemainSameDayList(ticketVO);
    }

    public List<TicketVO> selectTicketRegHistList(TicketVO ticketVO) {
        return ticketMapper.selectTicketRegHistList(ticketVO);
    }

    public List<TicketVO> selectTicketUseHistList(TicketVO ticketVO) {
        return ticketMapper.selectTicketUseHistList(ticketVO);
    }

}