package com.green.booktodolist.plan;


import com.green.booktodolist.plan.model.PlanBookInsDto;
import com.green.booktodolist.plan.model.PlanTodoDto;
import com.green.booktodolist.todoList.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static java.sql.DriverManager.println;

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
    public String callapihttp(@RequestParam String str) throws JSONException {

        StringBuffer result = new StringBuffer();
        StringBuffer sbf = new StringBuffer();
        String jsonPrintString = null;
        String strurl= URLEncoder.encode(str);
        //String strurl2= URLEncoder.encode("도서");
        //sbf.append("https://www.nl.go.kr/NL/search/openApi/search.do?key=20c6b5bcd4ef4e19948eb0be9dd73ab096f12c273a56cae1bcaa9fb3193c4f7d");
//        sbf.append("https://www.nl.go.kr/NL/search/openApi/search.do?key=");
//        sbf.append("20c6b5bcd4ef4e19948eb0be9dd73ab096f12c273a56cae1bcaa9fb3193c4f7d");
//        sbf.append("&result_style=xml&page_size=10");
//        sbf.append("&title="+strurl);

        // 테스트용
        sbf.append("https://www.nl.go.kr/seoji/SearchApi.do?cert_key=20c6b5bcd4ef4e19948eb0be9dd73ab096f12c273a56cae1bcaa9fb3193c4f7d&result_style=xml&page_no=1&page_size=30&title="+strurl);


        //https://www.nl.go.kr/seoji/SearchApi.do?cert_key=e7e239ae4128719a998e3a31ab3041b1a2cc0b014e95d5f4f2914e3187bbdc29&result_style=json&page_no=1&page_size=100&title=%EC%9E%90%EB%B0%94

        try {
            //String apiUrl = "https://www.nl.go.kr/NL/search/openApi/search.do?key=a29b041386e3197429c36f81b0dc7354c5154ee69f0c2279234a9048c54cdce2&kwd=%ED%86%A0%EC%A7%80";
            URL url = new URL(sbf.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }
            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = ((JSONObject) jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }


    @PostMapping("/book")
    @Operation(summary = "책정보 입력")
    public int postBook(PlanBookInsDto dto){
        return tmep_cate = service.insBook(dto);
    }

    @PostMapping("/Todolist")
    @Operation(summary = "투두리스트 작성")
    public void postTodolist(PlanTodoDto dto){
        service.postTodolist(dto);
    }
}

