package com.corelabs.dragLayout.mapper;

import com.corelabs.dragLayout.vo.DragVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DragMapper {
	int insertCoatRes(List<DragVO> dragVO);
	int getMaxGroupNumber(List<DragVO> dragVO);
	List<DragVO> selectReservationsByStatus();
	void deleteGroupItems(List<String> itemIds);
	void updateItem(DragVO dragVO);
	void updateMatchDuration(DragVO dragVO);
	void insertNewItem(DragVO newDragVO);
	DragVO selectById(String itemId);
}
