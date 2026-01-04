package com.corelabs.place.controller;

import com.corelabs.place.service.PlaceReservationService;
import com.corelabs.place.vo.PlaceReservationVO;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/placeReservation")
public class PlaceReservationController {

    final PlaceReservationService placeReservationService;

    public PlaceReservationController(PlaceReservationService placeReservationService) { this.placeReservationService = placeReservationService; }

    @GetMapping("/placeReservationList")
    public String placeReservationList() {
        return "/place/placeReservationList";
    }

    @GetMapping("/createPlaceReservation")
    public String createPlace() {
        return "/place/createPlaceReservation";
    }

    @PostMapping("/selectPlaceReservationList")
    @ResponseBody
    public String selectPlaceReservationList(@ModelAttribute PlaceReservationVO placeReservationVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<PlaceReservationVO> placeReservationList = placeReservationService.selectPlaceReservationList(placeReservationVO);
            System.out.println("PlaceReservation List Size: " + placeReservationList.size());
            for (PlaceReservationVO placeReservation : placeReservationList) {
                System.out.println("PlaceReservation: " + placeReservation.toString());
            }

            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", placeReservationList);
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }
    }

    @PostMapping("/savePlaceReservation")
    @ResponseBody
    public String savePlace(@ModelAttribute PlaceReservationVO placeReservationVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "저장 성공");
            rstMap.put("data", placeReservationService.savePlaceReservation(placeReservationVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/selectPlaceReservationByReservationId")
    @ResponseBody
    public String selectPlaceReservationByReservationId(@RequestParam String reservationId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            System.out.println("Selecting reservation by ID: " + reservationId);
            PlaceReservationVO reservation = placeReservationService.selectPlaceReservationByReservationId(reservationId);
            System.out.println("Found reservation: " + (reservation != null ? reservation.toString() : "null"));

            if (reservation == null) {
                rstMap.put("code", 404);
                rstMap.put("failMsg", "메뉴를 찾을 수 없습니다: " + reservationId);
            } else {
                rstMap.put("code", 200);
                rstMap.put("successMsg", "조회 성공");
                rstMap.put("data", reservation);
            }
        } catch (Exception e) {
            System.err.println("Error in selectPlaceByPlaceId: " + e.getMessage());
            e.printStackTrace();
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생: " + e.getMessage());
            rstMap.put("failCause", e.getCause() != null ? e.getCause().getMessage() : "Unknown cause");
        } finally {
            rstJson = gson.toJson(rstMap);
            System.out.println("Response JSON: " + rstJson);
            return rstJson;
        }
    }

    @PostMapping("/deletePlaceReservation")
    @ResponseBody
    public String deletePlaceReservation(@RequestParam String reservationId) {
        HashMap<String, Object> rstMap = new HashMap<>();
        try {
            placeReservationService.deletePlaceReservation(reservationId);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "메뉴가 삭제되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", 500);
            rstMap.put("failMsg", "메뉴 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return new Gson().toJson(rstMap);
    }

    @PostMapping("/checkReservationId")
    @ResponseBody
    public int checkReservationId(String reservationId) {
        int result = placeReservationService.checkReservationId(reservationId);
        return result;
    }
}
