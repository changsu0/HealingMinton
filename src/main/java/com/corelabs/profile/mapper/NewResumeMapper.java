package com.corelabs.profile.mapper;

import com.corelabs.profile.vo.NewResumeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewResumeMapper {

    List<NewResumeVO> selectResumeList();

    int insertResume(NewResumeVO newResumeVO);

}
