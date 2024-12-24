package com.corelabs.sample.mapper;

import com.corelabs.sample.vo.FilesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilesMapper {
	FilesVO selectFilesOne(FilesVO filesVO);
	List<FilesVO> selectFilesList(FilesVO filesVO);
	int insertFiles(List<FilesVO> filesVOList);
	int deleteFiles(FilesVO filesVO);
}
