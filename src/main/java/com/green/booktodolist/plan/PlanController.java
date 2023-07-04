package com.green.booktodolist.plan;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.green.booktodolist.plan.model.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService service;
    private int tmep_cate;
    @Autowired
    public PlanController(PlanService service) {
        this.service = service;
    }

//    @Value("${isbn.API_KEY}") // api키값
//    String API_KEY;



    @GetMapping("/search")
    @Operation(summary = "검색기능-테스트용")
    public List<PlanBookDataDto> callapihttp(@RequestParam String str) throws JSONException {

        log.info("책 검색 - api 호출 시작");

        String apiKey = "e7e239ae4128719a998e3a31ab3041b1a2cc0b014e95d5f4f2914e3187bbdc29"; // 인증키
        String result = null;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        StringBuffer requestApi = new StringBuffer();

        try {
            String urlstr = "https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" // 기본세팅 주소
                    + apiKey // 인증키
                    + "&result_style=json&page_no=1&page_size=30" // 페이지사이즈 30 고정
                    + "&title=" + result; // 검색어

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                requestApi.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("테스트지점2");

        result = requestApi.toString();

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
            dto.setCate(service.bookCategory(eaAddCode)); // 카테고리분류
            dto.setIsbn(eaIsbn);
            dto.setCompany(publisher);
            dto.setTitle(title);
            dto.setAuthor(author);
            dto.setPage(page.replaceAll("[^0-9]", "")); // 페이지에서 숫자만 남기고 제거 );
            SerachBookList.add(dto);
        }
        log.info("테스트지점1");
        return SerachBookList;
    }

//    @PostMapping("/book")
//    @Operation(summary = "책정보 입력")
//    public int postBook(@RequestBody PlanTodoInsDto dto){
//        Long ibook = service.insBook(dto); // 책 db작성후 ibook값 받아옴
//        return service.postTodolist(dto, ibook);
//    }

    @PostMapping("/Todolist")
    @Operation(summary = "투두리스트 작성")
    public int postTodolist(@RequestBody PlanTodoInsDto dto){
        Long ibook = service.insBook(dto); // 책 db작성후 ibook값 받아옴
        return service.postTodolist(dto, ibook);
    }


}
