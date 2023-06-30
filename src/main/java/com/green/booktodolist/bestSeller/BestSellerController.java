package com.green.booktodolist.bestSeller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.todoList.TodoMapper;
import com.green.booktodolist.todoList.TodoService;
import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelMainVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/aladinbestseller")
public class BestSellerController {

    private AladinSearchApi aladin;

    public BestSellerController(AladinSearchApi aladin) {
        this.aladin = aladin;
    }

    @GetMapping
    public String aladinbestbook(){
        String jsonPrintString = null;
        try {
            // 제일 먼저 todolist에 있는 책과 같은 것이 있다면 제거하기
            // mapper에서 조회..? Dto로 받아온 것과 비교? isbn으로 비교하면 될 듯

            // json을 map으로 변하기
            String json = aladin.search();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            ArrayList numList = (ArrayList) map.get("item");
            // 배열 이니까 random으로 순서 바꿔놓기
            Collections.shuffle(numList);
            // System.out.println(numList);

            // 수정하는 곳
            map.put("item", numList);

            // 다시 map을 json으로 변하기
            jsonPrintString = mapper.writeValueAsString(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }
}
