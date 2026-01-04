package com.corelabs.place.mapper;

import com.corelabs.place.vo.PlaceReservationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceReservationMapper {
    List<PlaceReservationVO> selectPlaceReservationList(PlaceReservationVO placeReservationVO);
    boolean checkReservationExists(String reservationId);
    int insertPlaceReservation(PlaceReservationVO placeReservationVO);
    int updatePlaceReservation(PlaceReservationVO placeReservationVO);
    PlaceReservationVO selectPlaceReservationByReservationId(String reservationId);
    void deletePlaceReservation(@Param("reservationId") String reservationId);
    int checkReservationId(String reservationId);
}
