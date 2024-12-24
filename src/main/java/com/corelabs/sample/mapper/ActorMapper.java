package com.corelabs.sample.mapper;

import com.corelabs.sample.vo.ActorVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActorMapper {
	ActorVO selectActorOne(ActorVO actorVO);
	List<ActorVO> selectActorList(ActorVO actorVO);
	int insertActor(ActorVO actorVO);
	int updateActor(ActorVO actorVO);
	int deleteActor(ActorVO actorVO);
}
