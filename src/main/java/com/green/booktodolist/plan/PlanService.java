package com.green.booktodolist.plan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.plan.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public int bookCategory(String eaAddCode){

        log.info("카테고리 분류 중");
        int temp;
        temp = Integer.parseInt(eaAddCode.substring(2, 3)) + 1;
        return temp;
    }

    public List<PlanBookDataDto> callapihttp(String result) { // api 호출

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("docs");

        List<PlanBookDataDto> SerachBookList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String publisher = obj.optString("PUBLISHER"); // 출판사 (발행자)
            String eaAddCode = obj.optString("EA_ADD_CODE"); // 부가기호
            String author = obj.optString("AUTHOR"); // 저자
            String eaIsbn = obj.optString("EA_ISBN"); // isbn
            String title = obj.optString("TITLE"); // 제목
            String page = obj.optString("PAGE"); // 페이지수

            PlanBookDataDto dto = new PlanBookDataDto();
            dto.setCate(bookCategory(eaAddCode)); // 카테고리분류
            dto.setIsbn(eaIsbn);
            dto.setCompany(publisher);
            dto.setTitle(title);
            dto.setAuthor(author);
            dto.setTotalpage(page);
            SerachBookList.add(dto);
        }
        return SerachBookList;
    }
}
