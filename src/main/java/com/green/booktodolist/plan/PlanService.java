package com.green.booktodolist.plan;

import com.green.booktodolist.plan.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RegExUtils.replaceAll;


@Slf4j
@Service
public class PlanService {
    private final PlanMapper mapper;

    @Autowired
    public PlanService(PlanMapper mapper) {
        this.mapper = mapper;
    }

//    public int insBook(PlanTodoInsDto dto) {
//        PlanBookDto Bookdto = new PlanBookDto();
//        int temp;
//        temp = Integer.parseInt((dto.getAddcode().substring(2, 3))) + 1;
//        log.info("카테고리 분류 중");
//        Bookdto.setIcate(temp); // 카테고리넣기
//        Bookdto.setPublisher(dto.getPublisher());
//        Bookdto.setTitle(dto.getTitle());
//        Bookdto.setIsbn(dto.getIsbn());
//        Bookdto.setWriter(dto.getWriter());
//        Bookdto.setAddcode(dto.getAddcode());
//        log.info("book DB작성완료");
//        return mapper.insBook(Bookdto);
//    }

    public Long postBook(PlanTodoInsDto dto){
        log.info("book DB작성");
        PlanBookDto bookdto = new PlanBookDto();
        bookdto.setWriter(dto.getAuthor());
        bookdto.setPublisher(dto.getCompany());
        bookdto.setTitle(dto.getTitle());
        bookdto.setIcate(Integer.parseInt(dto.getCate()));
        bookdto.setPage(dto.getTotal());
        bookdto.setIsbn(dto.getIsbn());
        mapper.insBook(bookdto);
        log.info("book DB작성완료");
        return bookdto.getIbook();
    }

    public int postTodolist(PlanTodoInsDto dto, Long ibook) {
        final int IUSER = 1;
        System.out.println("테스트 : " +ibook);
        log.info("todolist DB 작성시작");
        PlanTodoDto planDto = new PlanTodoDto();
        planDto.setIuser(IUSER);
        planDto.setFinish_yn(dto.getFinish());
        planDto.setStart(dto.getStart());
        planDto.setEnd(dto.getEnd());
        planDto.setMemo(dto.getMemo());
        planDto.setBookmark(dto.getBookmark());
        planDto.setIbook(ibook);
        System.out.println("테스트2 : "+planDto.getIbook());
        log.info("todolist DB 작성완료");
        return mapper.insTodoList(planDto);
    }

    public int bookCategory(String eaAddCode){
        int temp;
        if (eaAddCode.length() >= 3) {
            char thirdChar = eaAddCode.charAt(2);
            temp = Integer.parseInt(String.valueOf(thirdChar)) + 1;
        } else {
            log.info("카테고리 분류 에러발생");
            temp = 11; // 오류 카테고리 코드
        }
        System.out.println(temp);
        return temp;
    }

    public List<PlanBookDataDto> callapihttp(String result) { // api 호출
        log.info("데이터 파싱 시작");

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
            if (!page.equals("")) { // 페이지 수에서 숫자만 남기고 제거
                String resultpage = page.replaceAll("[^0-9]", "");
                dto.setPage(resultpage);
            }
            else dto.setPage(null); // 페이지 정보 없는 책은 "" 공백처리
            SerachBookList.add(dto);
            log.info("데이터 파싱 완료");
        }
        log.info("검색결과 리스트 작성 완료");
        return SerachBookList;
    }

}
