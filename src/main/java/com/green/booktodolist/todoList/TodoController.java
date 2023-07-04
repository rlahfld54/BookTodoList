package com.green.booktodolist.todoList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.todoList.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping
    @Operation(summary = "메인조회",
            description = "Try it out -> Execute 눌러주세요 \n\n "+
            "cate_name: 카테고리 이름 \n\n " +
            "itodo : 투두리스트 PK 값 \n\n " +
            "bookmark : 책갈피 \n\n " +
            "del: 1이면 삭제인척 \n\n " +
            "start: 투두 시작날짜 \n\n " +
            "end: 투두 종료 날짜 \n\n "
    )
    public SelMainVo GetMain(){
        return service.selMain();
    }

    @GetMapping("/{itodo}")
    @Operation(summary = "상세조회",description = "상세 조회 하고 싶은 itodo 의 값 2~33사이")
    public SelDetailDto GetDetail(@PathVariable int itodo){

        return service.selDetail(itodo);
    }

    @PostMapping("/{itodo}")
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

    @GetMapping("/naver")
    @Operation(summary = "api 연습")
    public List<BookVo2> GetNaverBook(String text){
        String clientId = "ncAR9prYDibH9NS7yQuA";
        String clientSecret = "vl2B4Z4tvO";

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 100)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResultVo resultVO = null;

        try {
            resultVO = om.readValue(resp.getBody(), NaverResultVo.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<BookVo2> books =resultVO.getItems();
        //model.addAttribute("books", books);
        return books;
    }
}
