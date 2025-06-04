package com.corelabs.dragLayout.controller;

import com.corelabs.dragLayout.service.DragService;
import com.corelabs.dragLayout.vo.DragVO;
import com.corelabs.ticket.vo.TicketVO;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/drag")
public class DragController {

    final DragService dragService;

    public DragController(DragService dragService) {
        this.dragService = dragService;
    }

    @GetMapping("/dragLayout")
    public String dragLayout(Model model) {
        return "drag/dragLayout";
    }

    @GetMapping("/courtCount")
    public String courtCount(Model model) {
        return "drag/courtCount";
    }

    @GetMapping("/reservation")
    public String reservation(Model model) {
        return "drag/coatReservation";
    }

    @PostMapping("/insertCoatRes")
    @ResponseBody
    public String insertCoatRes(@RequestBody List<DragVO> dragVO) {
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<String> userUids = dragService.insertCoatRes(dragVO);
            rstMap.put("data", userUids);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        }
        return gson.toJson(rstMap);
    }

    @PostMapping("/selectReservationsByStatus")
    @ResponseBody
    public String selectReservationsByStatus(@RequestBody DragVO dragVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", dragService.selectReservationsByStatus());
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/saveOrUpdateItems")
    @ResponseBody
    public String saveOrUpdateItems(@RequestBody Map<String, Object> request) {
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            // Validate the "items" key
            List<Map<String, String>> items = (List<Map<String, String>>) request.get("itemList");
            if (items == null || items.isEmpty()) {
                rstMap.put("code", -1);
                rstMap.put("failMsg", "The 'items' field is missing or empty");
                return gson.toJson(rstMap);
            }

            // Process the courtNumber
            String courtNumber = request.get("courtNumber").toString();

            // Call the service method
            dragService.saveOrUpdateItems(items, courtNumber);

            rstMap.put("code", 200);
            rstMap.put("successMsg", "Items saved or updated successfully");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "Error occurred while processing the request");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        }
        return gson.toJson(rstMap);
    }

    @PostMapping("/endGame")
    @ResponseBody
    public String endGame(@RequestBody Map<String, Object> request) {
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<String> itemIds = (List<String>) request.get("itemIds");
            dragService.endGame(itemIds);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "Game ended successfully");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "Error occurred while ending the game");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        }
        return gson.toJson(rstMap);
    }

    @PostMapping("/deleteGroupItems")
    @ResponseBody
    public String deleteGroupItems(@RequestBody Map<String, Object> request) {
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            // Extract itemIds from the request
            List<String> itemIds = (List<String>) request.get("itemIds");
            if (itemIds == null || itemIds.isEmpty()) {
                rstMap.put("code", -1);
                rstMap.put("failMsg", "The 'itemIds' field is missing or empty");
                return gson.toJson(rstMap);
            }

            // Call the service to delete the group items
            dragService.deleteGroupItems(itemIds);

            rstMap.put("code", 200);
            rstMap.put("successMsg", "Group items deleted successfully");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "Error occurred while deleting group items");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        }
        return gson.toJson(rstMap);
    }

    @PostMapping("/cancelGame")
    @ResponseBody
    public String cancelGame(@RequestBody Map<String, Object> request) {
        HashMap<String, Object> rstMap = new HashMap<>();
        Gson gson = new Gson();
        try {
            List<String> itemIds = (List<String>) request.get("itemIds");
            String status = (String) request.get("status");
            dragService.cancelGame(itemIds, status);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "경기가 취소되었습니다.");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "경기 취소 중 오류가 발생했습니다.");
            rstMap.put("failCause", e.getCause());
            System.out.println(e.getMessage());
        }
        return gson.toJson(rstMap);
    }

}
