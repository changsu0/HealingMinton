package com.corelabs.sample.service;

import com.corelabs.sample.mapper.ActorMapper;
import com.corelabs.sample.vo.ActorVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    final
    ActorMapper actorMapper;

    public ActorService(ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
    }

    public ActorVO selectActorOne(ActorVO actorVO) {
        return actorMapper.selectActorOne(actorVO);
    }

    public List<ActorVO> selectActorList(ActorVO actorVO) {
        return actorMapper.selectActorList(actorVO);
    }

    public int insertActor(ActorVO actorVOList) {
        return actorMapper.insertActor(actorVOList);
    }

    public int updateActor(ActorVO actorVOList) {
        return actorMapper.updateActor(actorVOList);
    }

    public int deleteActor(ActorVO actorVOList) {
        return actorMapper.deleteActor(actorVOList);
    }

}