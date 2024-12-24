package com.corelabs.image.service;

import com.corelabs.image.mapper.ImageMapper;
import com.corelabs.image.vo.ImageVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    final
    ImageMapper imageMapper;

    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public List<ImageVO> selectImageList(ImageVO imageVO) {
        return imageMapper.selectImageList(imageVO);
    }

}