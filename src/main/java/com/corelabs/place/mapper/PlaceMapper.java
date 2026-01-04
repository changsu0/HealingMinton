package com.corelabs.place.mapper;

import com.corelabs.place.vo.PlaceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceMapper {
    List<PlaceVO> selectPlaceList(PlaceVO placeVO);
    boolean checkPlaceExists(String placeId);
    int insertPlace(PlaceVO placeVO);
    int updatePlace(PlaceVO placeVO);
    PlaceVO selectPlaceByPlaceId(String placeId);
    void deletePlace(@Param("placeId") String placeId);
    int checkPlaceId(String placeId);
}
