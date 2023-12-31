package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            "del: 1이면 삭제 \n\n " +
            "start: 투두 시작날짜 \n\n " +
            "end: 투두 종료 날짜 \n\n "
    )
    public SelMainVo GetMain(){
        return service.selMain();
    }

    @PatchMapping
    @Operation(summary = "완료처리")
    public int PatchDel(@RequestBody UpdDel dto){
        return service.updel(dto);
    }

    @GetMapping("/{itodo}")
    @Operation(summary = "상세조회",description = "상세 조회 하고 싶은 itodo 의 값")
    public SelDetailDto GetDetail(@PathVariable int itodo){

        return service.selDetail(itodo);
    }

    @PatchMapping("/{itodo}")
    @Operation(summary = "투두 수정",description = "start: 투두시작날짜 예시: 2022-06-29  \n " +
            "end: 투두종료 날짜 예시:2022-07-01  \n " +
            "finish: 1이면 완료 0이면 미완료  \n ")
    public int PatchTodo(@RequestBody UpdTodoDto dto){
        return service.UpdTodo(dto);
    }

    @DeleteMapping("/{itodo}")
    @Operation(summary = "투두 삭제",description = "삭제 하고 싶은 itodo 값 (2~33 사이의 숫자)")
    public int DelTodo(@PathVariable int itodo){
        return service.DelTodo(itodo);
    }
}
