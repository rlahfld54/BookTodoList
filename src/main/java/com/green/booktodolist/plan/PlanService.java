package com.green.booktodolist.plan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.plan.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;




@Slf4j
@Service
public class PlanService {
    private final PlanMapper mapper;

    @Autowired
    public PlanService(PlanMapper mapper) {
        this.mapper = mapper;
    }

    public int insBook(PlanBookInsDto dto) {
        PlanBookDto Bookdto = new PlanBookDto();

        int temp;
        temp = Integer.parseInt((dto.getAddcode().substring(2, 3))) + 1;

        log.info("카테고리 분류 중");
        Bookdto.setIcate(temp); // 카테고리넣기
        Bookdto.setPublisher(dto.getPublisher());
        Bookdto.setTitle(dto.getTitle());
        Bookdto.setIsbn(dto.getIsbn());
        Bookdto.setWriter(dto.getWriter());
        Bookdto.setAddcode(dto.getAddcode());
        log.info("book DB작성완료");
        return mapper.insBook(Bookdto);
    }

    public int postTodolist(PlanTodoDto dto) {
        log.info("투두리스트 작성 시작");
        dto.setIuser(dto.getIuser());
        dto.setIbook(dto.getIbook());
        dto.setStartDate(dto.getStartDate());
        dto.setFinishedDate(dto.getFinishedDate());
        dto.setDel_yn(dto.getDel_yn());
        dto.setFinish_yn(dto.getDel_yn());
        dto.setBookmark(dto.getBookmark());
        log.info("투두리스트 작성 완료");

        return mapper.insTodoList(dto);
    }

    public String callapihttp(String str) { // api 호출


        return "a";
    }
}
