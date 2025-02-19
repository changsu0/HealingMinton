package com.corelabs.baccara.service;

import com.corelabs.baccara.mapper.BaccaraMapper;
import com.corelabs.baccara.vo.BaccaraVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaccaraService {

    final
    BaccaraMapper baccaraMapper;

    public BaccaraService(BaccaraMapper baccaraMapper) {
        this.baccaraMapper = baccaraMapper;
    }

    public int insertBaccara(BaccaraVO baccaraVO) {
        return baccaraMapper.insertBaccara(baccaraVO);
    }
}