package com.corelabs.rent.service;

import com.corelabs.rent.mapper.RentMapper;
import com.corelabs.rent.vo.RentVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    final
    RentMapper rentMapper;

    public RentService(RentMapper rentMapper) {
        this.rentMapper = rentMapper;
    }

    public List<RentVO> selectRentList(RentVO rentVO) {
        return rentMapper.selectRentList(rentVO);
    }
    public int insertRent(RentVO rentVO) {
        // TODO : LoginUser 정보를 가져와서 넣어줘야 함
        rentVO.setCreateUserId("LoginUser");
        rentVO.setUpdateUserId("LoginUser");
        return rentMapper.insertRent(rentVO);
    }
    public int deleteRent(RentVO rentVO) {
        rentVO.setCreateUserId("LoginUser");
        rentVO.setUpdateUserId("LoginUser");
        return rentMapper.deleteRent(rentVO);
    }

}