package com.corelabs.notice.controller;

import com.corelabs.notice.service.NoticeService;
import com.corelabs.notice.vo.NoticeVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    final
    NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @GetMapping("/noticeList")
    public String noticeList(Model model) {
        return "notice/noticeList";
    }

    @GetMapping("/notice")
    public String notice(Model model) {
        return "notice/notice";
    }

    @GetMapping("/faqList")
    public String faqList(Model model) {
        return "notice/faqList";
    }
    @GetMapping("/faqOne")
    public String faqOne(Model model) {
        return "notice/faqOne";
    }


    @GetMapping("/select/noticeList")
    @ResponseBody
    public String selectNoticeList(@ModelAttribute NoticeVO noticeVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", noticeService.selectNoticeList(noticeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @GetMapping("/select/noticeOne")
    @ResponseBody
    public String selectNoticeOne(@ModelAttribute NoticeVO noticeVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", noticeService.selectNoticeOne(noticeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @GetMapping("/select/faqList")
    @ResponseBody
    public String selectFAQList(@ModelAttribute NoticeVO noticeVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", noticeService.selectFAQList(noticeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @GetMapping("/select/faqOne")
    @ResponseBody
    public String selectFAQOne(@ModelAttribute NoticeVO noticeVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
            rstMap.put("data", noticeService.selectFAQOne(noticeVO));
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }
}