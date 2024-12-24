package com.corelabs.user.service;

import com.corelabs.ticket.mapper.TicketMapper;
import com.corelabs.ticket.vo.TicketVO;
import com.corelabs.user.mapper.UserMapper;
import com.corelabs.user.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final
    UserMapper userMapper;
    private final TicketMapper ticketMapper;

    public UserService(UserMapper userMapper, TicketMapper ticketMapper) {
        this.userMapper = userMapper;
        this.ticketMapper = ticketMapper;
    }

    public List<UserVO> selectUserList(UserVO userVO) {
        return userMapper.selectUserList(userVO);
    }

    public int insertUser(TicketVO ticketVO) {
        int resultCnt = 0;

        resultCnt = userMapper.insertUser(ticketVO);

        System.out.println(ticketVO.getUserUid());
        if(!ticketVO.getRegCnt().equals("0")) {
            ticketVO.setUserUid(ticketVO.getUserUid());
            ticketVO.setRegCnt(ticketVO.getRegCnt());

            ticketMapper.insertTicketRegHist(ticketVO);
        }

        return resultCnt;
    }

}