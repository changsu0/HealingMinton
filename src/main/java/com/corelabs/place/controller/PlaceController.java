package com.corelabs.place.controller;

import com.corelabs.place.service.PlaceService;
import com.corelabs.place.vo.PlaceVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/place")
public class PlaceController {

    final PlaceService placeService;

    public PlaceController(PlaceService placeService) { this.placeService = placeService; }

    @GetMapping("/placeList")
    public String placeList() {
        return "/place/placeList";
    }

    @GetMapping("/createPlace")
    public String createPlace() {
        return "/place/createPlace";
    }

    @PostMapping("/selectPlaceList")
    @ResponseBody
    public String selectPlaceList(@ModelAttribute PlaceVO placeVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<PlaceVO> placeList = placeService.selectPlaceList(placeVO);
            System.out.println("Place List Size: " + placeList.size());
            for (PlaceVO place : placeList) {
                System.out.println("Place: " + place.toString());
            }

            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", placeList);
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

    @PostMapping("/savePlace")
    @ResponseBody
    public String savePlace(@ModelAttribute PlaceVO placeVO) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "저장 성공");
            rstMap.put("data", placeService.savePlace(placeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        } finally {
            return gson.toJson(rstMap);
        }
    }

    @PostMapping("/selectPlaceByPlaceId")
    @ResponseBody
    public String selectPlaceByPlaceId(@RequestParam String placeId) {
        String rstJson = null;
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            System.out.println("Selecting place by ID: " + placeId);
            PlaceVO place = placeService.selectPlaceByPlaceId(placeId);
            System.out.println("Found place: " + (place != null ? place.toString() : "null"));

            if (place == null) {
                rstMap.put("code", 404);
                rstMap.put("failMsg", "메뉴를 찾을 수 없습니다: " + placeId);
            } else {
                rstMap.put("code", 200);
                rstMap.put("successMsg", "조회 성공");
                rstMap.put("data", place);
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

    @PostMapping("/deletePlace")
    @ResponseBody
    public String deletePlace(@RequestParam String placeId) {
        HashMap<String, Object> rstMap = new HashMap<>();
        try {
            placeService.deletePlace(placeId);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "메뉴가 삭제되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", 500);
            rstMap.put("failMsg", "메뉴 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return new Gson().toJson(rstMap);
    }
}
