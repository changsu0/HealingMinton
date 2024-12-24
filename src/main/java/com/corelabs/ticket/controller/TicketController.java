package com.corelabs.ticket.controller;

import com.corelabs.ticket.service.TicketService;
import com.corelabs.ticket.vo.TicketVO;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticketRegHistList")
    public String ticketRegHistList(Model model) {
        return "ticket/ticketRegHistList";
    }

    @GetMapping("/ticketUseHistList")
    public String ticketUseHistList(Model model) {
        return "ticket/ticketUseHistList";
    }



    @PostMapping("/selectTicketRegList")
    @ResponseBody
    public String selectTicketRegList(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.selectTicketRegList(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/insertTicketRegHist")
    @ResponseBody
    public String insertTicketRegHist(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.insertTicketRegHist(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/deleteTicketRegHist")
    @ResponseBody
    public String deleteTicketRegHist(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.deleteTicketRegHist(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/selectTicketUseList")
    @ResponseBody
    public String selectTicketUseList(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.selectTicketUseList(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/insertTicketUseHist")
    @ResponseBody
    public String insertTicketUseHist(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.insertTicketUseHist(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/deleteTicketUseHist")
    @ResponseBody
    public String deleteTicketUseHist(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.deleteTicketUseHist(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/selectUserTicketCnt")
    @ResponseBody
    public String selectUserTicketCnt(@RequestBody TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.selectUserTicketCnt(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/selectUserTicketRemainList")
    @ResponseBody
    public String selectUserTicketRemainList(@ModelAttribute TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            List<TicketVO> remainList = ticketService.selectUserTicketRemainList(ticketVO);
            List<TicketVO> sameDayList = ticketService.selectUserTicketRemainSameDayList(ticketVO);
            HashMap dataMap = new HashMap();
            dataMap.put("remainList", remainList);
            dataMap.put("sameDayList", sameDayList);

            rstMap.put("data", dataMap);
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/selectTicketRegHistList")
    @ResponseBody
    public String selectTicketRegHistList(@ModelAttribute TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.selectTicketRegHistList(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
        } catch (Exception e) {
            rstMap.put("code", -1);
            rstMap.put("failMsg", "처리중 에러발생");
            rstMap.put("failCause", e.getCause());
            System.out.println( e.getMessage() );
        }finally {
            return gson.toJson( rstMap );
        }
    }

    @PostMapping("/selectTicketUseHistList")
    @ResponseBody
    public String selectTicketUseHistList(@ModelAttribute TicketVO ticketVO) {
        String rstJson = null;
        HashMap rstMap = new HashMap();
        Gson gson = new Gson();
        try {
            rstMap.put("data", ticketService.selectTicketUseHistList(ticketVO));
            rstMap.put("code", 200);
            rstMap.put("successMsg", "조회 성공");
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