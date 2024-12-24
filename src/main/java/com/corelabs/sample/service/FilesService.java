package com.corelabs.sample.service;

import com.corelabs.sample.mapper.FilesMapper;
import com.corelabs.sample.vo.FilesVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    final
    FilesMapper filesMapper;

    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public FilesVO selectFilesOne(FilesVO filesVO) {
        return filesMapper.selectFilesOne(filesVO);
    }

    public List<FilesVO> selectFilesList(FilesVO filesVO) {
        return filesMapper.selectFilesList(filesVO);
    }

    public int insertFiles(List<FilesVO> filesVOList) {
        return filesMapper.insertFiles(filesVOList);
    }

    public int deleteFiles(FilesVO filesVOList) {
        return filesMapper.deleteFiles(filesVOList);
    }

}