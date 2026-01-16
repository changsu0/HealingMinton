package com.corelabs.profile.mapper;

import com.corelabs.profile.vo.ProfileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {

    List<ProfileVO> selectProfileList();

    int insertProfile(ProfileVO profileVO);

}
