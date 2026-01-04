package com.corelabs.place.service;

import com.corelabs.place.mapper.PlaceMapper;
import com.corelabs.place.vo.PlaceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    final PlaceMapper placeMapper;

    public PlaceService(PlaceMapper placeMapper) { this.placeMapper = placeMapper; }

    public List<PlaceVO> selectPlaceList(PlaceVO placeVO) {
        return placeMapper.selectPlaceList(placeVO);
    }

    public int savePlace(PlaceVO placeVO) {
        int resultCnt = 0;

        boolean placeExists = placeMapper.checkPlaceExists(placeVO.getPlaceId());

        if (placeExists) {
            resultCnt = placeMapper.updatePlace(placeVO);
        } else {
            resultCnt = placeMapper.insertPlace(placeVO);
        }

        return resultCnt;
    }

    public PlaceVO selectPlaceByPlaceId(String placeId) {
        System.out.println("Service: Selecting place by ID: " + placeId);
        try {
            PlaceVO place = placeMapper.selectPlaceByPlaceId(placeId);
            System.out.println("Service: Found menu: " + (place != null ? place.toString() : "null"));
            return place;
        } catch (Exception e) {
            System.err.println("Service Error in selectPlaceByPlaceId: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void deletePlace(String placeId) {
        placeMapper.deletePlace(placeId);
    }

    public int checkPlaceId(String placeId) {
        return placeMapper.checkPlaceId(placeId);
    }
}
