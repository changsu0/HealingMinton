package com.corelabs.dragLayout.service;

import com.corelabs.dragLayout.mapper.DragMapper;
import com.corelabs.dragLayout.vo.DragVO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DragService {

    final
    DragMapper dragMapper;

    public DragService(DragMapper dragMapper) {
        this.dragMapper = dragMapper;
    }

    public List<String> insertCoatRes(List<DragVO> dragVO) {
        // Get the current max group number from the database
        int maxGroupNumber = dragMapper.getMaxGroupNumber(dragVO);
        int newGroupNumber = maxGroupNumber + 1;

        // Assign the new group number to each DragVO
        dragVO.forEach(drag -> {
            drag.setUserUid(UUID.randomUUID().toString());
            drag.setGroupNo(newGroupNumber);
            drag.setCreateUserId("LoginUser");
            drag.setUpdateUserId("LoginUser");
        });

        dragMapper.insertCoatRes(dragVO);
        return dragVO.stream().map(DragVO::getUserUid).toList();
    }

    public List<DragVO> selectReservationsByStatus() {
        return dragMapper.selectReservationsByStatus();
    }

    public void deleteGroupItems(List<String> itemIds) {
        dragMapper.deleteGroupItems(itemIds);
    }

    public void saveOrUpdateItems(List<Map<String, String>> items, String courtNumber) {
        items.forEach(item -> {
            String itemId = item.get("id");
            String status = item.get("status");
            //String matchStartDatetime = item.get("matchStartDatetime");

            DragVO dragVO = dragMapper.selectById(itemId);
            if (dragVO != null) {
                // Update existing record
                dragVO.setStatus(status);
                dragVO.setCourtNumber(courtNumber);

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String matchStartDatetime = now.format(formatter);

                dragVO.setMatchStartDateTime(matchStartDatetime);
                dragVO.setUpdateUserId("LoginUser");
                dragMapper.updateItem(dragVO);
            } else {
                // Insert new record
                DragVO newDragVO = new DragVO();
                newDragVO.setUserUid(itemId);
                newDragVO.setStatus(status);
                newDragVO.setCourtNumber(courtNumber);
                //newDragVO.setMatchStartDateTime(matchStartDatetime);
                newDragVO.setCreateUserId("LoginUser");
                newDragVO.setUpdateUserId("LoginUser");
                dragMapper.insertNewItem(newDragVO);
            }
        });
    }

    public void endGame(List<String> itemIds) {
        itemIds.forEach(itemId -> {
            DragVO dragVO = dragMapper.selectById(itemId);
            if (dragVO != null) {
                // Update game end time and total duration
                dragVO.setStatus("E"); // E for Ended
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String timeOnly = now.format(formatter);

                dragVO.setMatchEndDateTime(timeOnly);
                dragVO.setMatchDuration(calculateDuration(dragVO.getMatchStartDateTime(), timeOnly));
                dragMapper.updateMatchDuration(dragVO);
            }
        });
    }

    private String calculateDuration(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Parse the start and end times
        LocalTime start = LocalTime.parse(startTime, formatter);
        LocalTime end = LocalTime.parse(endTime, formatter);

        // Calculate the duration
        Duration duration = Duration.between(start, end);

        // Format the duration as HH:mm:ss
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void cancelGame(List<String> itemIds, String status) {
        itemIds.forEach(itemId -> {
            DragVO dragVO = dragMapper.selectById(itemId);
            if (dragVO != null) {
                dragVO.setStatus(status); // Set status to "W"
                dragVO.setMatchStartDateTime(null);
                dragVO.setUpdateUserId("LoginUser");
                dragMapper.updateItem(dragVO);
            }
        });
    }

}