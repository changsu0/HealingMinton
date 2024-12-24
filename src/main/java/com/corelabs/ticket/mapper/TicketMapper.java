package com.corelabs.ticket.mapper;

import com.corelabs.ticket.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper {
	List<TicketVO> selectTicketRegList(TicketVO ticketVO);
	int insertTicketRegHist(TicketVO ticketVO);
	int deleteTicketRegHist(TicketVO ticketVO);
	List<TicketVO> selectTicketUseList(TicketVO ticketVO);
	int insertTicketUseHist(TicketVO ticketVO);
	int deleteTicketUseHist(TicketVO ticketVO);
	TicketVO selectUserTicketCnt(TicketVO ticketVO);
	List<TicketVO> selectUserTicketRemainList(TicketVO ticketVO);
	List<TicketVO> selectUserTicketRemainSameDayList(TicketVO ticketVO);
	List<TicketVO> selectTicketRegHistList(TicketVO ticketVO);
	List<TicketVO> selectTicketUseHistList(TicketVO ticketVO);
}
