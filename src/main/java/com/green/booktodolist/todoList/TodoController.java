package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;
    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public String callapihttp(@RequestParam String str) throws JSONException {
        StringBuffer result = new StringBuffer();
        StringBuffer sbf = new StringBuffer();
        String jsonPrintString = null;
        String strurl= URLEncoder.encode(str);
        String strurl2= URLEncoder.encode("도서");
        sbf.append("https://www.nl.go.kr/NL/search/openApi/search.do?key=20c6b5bcd4ef4e19948eb0be9dd73ab096f12c273a56cae1bcaa9fb3193c4f7d");
        sbf.append("&kwd="+strurl);
        sbf.append("&pageSize=500&pageNum=1");
        sbf.append("&category="+strurl2);
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

    @GetMapping
    @Operation(summary = "메인조회", description = "Try it out -> Execute 눌러주세요")
    public SelMainVo GetMain(){
        return service.selMain();
    }

    @GetMapping("/{itodo}")
    @Operation(summary = "상세조회",description = "상세 조회 하고 싶은 itodo 의 값 2~33사이")
    public SelDetailDto GetDetail(@PathVariable int itodo){

        return service.selDetail(itodo);
    }
    @PostMapping
    @Operation(summary = "투두 수정",description = "start: 투두시작날짜 예시: 2022-06-29  \n " +
            "end: 투두종료 날짜 예시:2022-07-01  \n " +
            "finish: 1이면 완료 0이면 미완료  \n " +
            "itodo: 수정하고 싶은 itodo 값 2~33사이 \n ")
    public int PostTodo(@RequestBody UpdTodoDto dto){
        return service.UpdTodo(dto);
    }

    @DeleteMapping("/{itodo}")
    @Operation(summary = "투두 삭제",description = "삭제 하고 싶은 itodo 값 (2~33 사이의 숫자)")
    public int DelTodo(@PathVariable int itodo){
        return service.DelTodo(itodo);
    }
}
