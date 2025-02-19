package com.corelabs.baccara.mapper;

import com.corelabs.baccara.vo.BaccaraVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaccaraMapper {
	int insertBaccara(BaccaraVO baccaraVO);
}
