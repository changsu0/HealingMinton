package com.corelabs.notice.service;

import com.corelabs.notice.mapper.NoticeMapper;
import com.corelabs.notice.vo.NoticeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    final
    NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) {
        return noticeMapper.selectNoticeList(noticeVO);
    }
    public NoticeVO selectNoticeOne(NoticeVO noticeVO) {
        return noticeMapper.selectNoticeOne(noticeVO);
    }
    public List<NoticeVO> selectFAQList(NoticeVO noticeVO) {
        return noticeMapper.selectFAQList(noticeVO);
    }

    public NoticeVO selectFAQOne(NoticeVO noticeVO) {
        return noticeMapper.selectFAQOne(noticeVO);
    }


}