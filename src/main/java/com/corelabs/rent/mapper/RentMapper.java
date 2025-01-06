package com.corelabs.rent.mapper;

import com.corelabs.rent.vo.RentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentMapper {
	List<RentVO> selectRentList(RentVO rentVO);
	int insertRent(RentVO rentVO);
	int deleteRent(RentVO rentVO);
}
