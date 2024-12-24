package com.corelabs.user.mapper;

import com.corelabs.ticket.vo.TicketVO;
import com.corelabs.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	List<UserVO> selectUserList(UserVO userVO);
	int insertUser(TicketVO ticketVO);
}
