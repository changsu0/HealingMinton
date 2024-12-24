package com.corelabs.notice.mapper;

import com.corelabs.notice.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeVO> selectNoticeList(NoticeVO noticeVO);
    NoticeVO selectNoticeOne(NoticeVO noticeVO);
    List<NoticeVO> selectFAQList(NoticeVO noticeVO);
    NoticeVO selectFAQOne(NoticeVO noticeVO);
}
