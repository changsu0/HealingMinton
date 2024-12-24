package com.corelabs.image.mapper;

import com.corelabs.image.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    List<ImageVO> selectImageList(ImageVO imageVO);
}
