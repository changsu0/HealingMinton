package com.corelabs.place.service;

import com.corelabs.place.mapper.PlaceReservationMapper;
import com.corelabs.place.vo.PlaceReservationVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceReservationService {

    final PlaceReservationMapper placeReservationMapper;

    public PlaceReservationService(PlaceReservationMapper placeReservationMapper) { this.placeReservationMapper = placeReservationMapper; }

    public List<PlaceReservationVO> selectPlaceReservationList(PlaceReservationVO placeReservationVO) {
        return placeReservationMapper.selectPlaceReservationList(placeReservationVO);
    }

    public int savePlaceReservation(PlaceReservationVO placeReservationVO) {
        int resultCnt = 0;

        boolean reservationExists = placeReservationMapper.checkReservationExists(placeReservationVO.getReservationId());

        if (reservationExists) {
            resultCnt = placeReservationMapper.updatePlaceReservation(placeReservationVO);
        } else {
            resultCnt = placeReservationMapper.insertPlaceReservation(placeReservationVO);
        }

        return resultCnt;
    }

    public PlaceReservationVO selectPlaceReservationByReservationId(String reservationId) {
        System.out.println("Service: Selecting reservation by ID: " + reservationId);
        try {
            PlaceReservationVO reservation = placeReservationMapper.selectPlaceReservationByReservationId(reservationId);
            System.out.println("Service: Found reservation: " + (reservation != null ? reservation.toString() : "null"));
            return reservation;
        } catch (Exception e) {
            System.err.println("Service Error in selectPlaceReservationByReservationId: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void deletePlaceReservation(String reservationId) {
        placeReservationMapper.deletePlaceReservation(reservationId);
    }

    public int checkReservationId(String reservationId) {
        return placeReservationMapper.checkReservationId(reservationId);
    }
}
