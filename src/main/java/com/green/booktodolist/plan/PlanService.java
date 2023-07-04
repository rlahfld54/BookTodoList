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

    public Long insBook(PlanTodoInsDto dto) {
        PlanBookDto Bookdto = new PlanBookDto();

        log.info("book DB작성시작");
        Bookdto.setIcate(dto.getCate()); // 카테고리넣기
        Bookdto.setPublisher(dto.getCompany());
        Bookdto.setTitle(dto.getTitle());
        Bookdto.setIsbn(dto.getIsbn());
        Bookdto.setWriter(dto.getAuthor());
        Bookdto.setPage(dto.getPage()); // 페이지에서 숫자만 남기고 제거
        log.info("book DB작성완료");
        return mapper.insBook(Bookdto);
    }

    public int postTodolist(PlanTodoInsDto dto, Long ibook) {
        log.info("투두리스트 작성 시작");
        PlanTodoDto todoDto = new PlanTodoDto();
        todoDto.setIuser(dto.getIuser());
        todoDto.setIbook(ibook);
        todoDto.setStartDate(dto.getStart()); // 시작날짜
        todoDto.setFinishedDate(dto.getEnd()); //완료(목표)날짜
        todoDto.setDel_yn(dto.getDel());
        todoDto.setFinish_yn(dto.getDel()); // 완료여부
        todoDto.setBookmark(dto.getBookmark());
        log.info("투두리스트 작성 완료");

        return mapper.insTodoList(todoDto);
    }

    public int bookCategory(String eaAddCode){

        log.info("카테고리 분류 중");
        int temp = 0;
        if (eaAddCode.length() >= 3) {
            temp = Integer.parseInt(eaAddCode.substring(2, 3)) + 1;
        }
        return temp;
    }

}