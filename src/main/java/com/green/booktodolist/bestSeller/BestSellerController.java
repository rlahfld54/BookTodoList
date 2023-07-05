package com.green.booktodolist.bestSeller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.bestSeller.model.BestSellerBook;
import com.green.booktodolist.todoList.TodoService;
import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/aladinbestseller")
public class BestSellerController {
    private AladinSearchApi aladin;
    private TodoService todoService;

    @Autowired
    public BestSellerController(AladinSearchApi aladin, TodoService todoService) {
        this.aladin = aladin;
        this.todoService = todoService;
    }

    public SelMainVo GetMain(){
        return todoService.selMain();
    }

    @GetMapping
    public String aladinbestbook(){
        String jsonPrintString = null;
        try {
            // json을 map으로 변하기
            String json = aladin.search();
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(json, Map.class);
            // 여기서 캐스팅 안되는듯..
            List<LinkedHashMap<String, Object>> numList = (List<LinkedHashMap<String, Object>>) map.get("item");

            List<BestSellerBook> bestSellerBookList = new ArrayList<>();

            for(LinkedHashMap<String, Object> item : numList) {

                bestSellerBookList.add(BestSellerBook.builder()
                                .title((String)item.get("title"))
                                .link((String)item.get("link"))
                                .author((String)item.get("author"))
                                .pubDate((String)item.get("pubDate"))
                                .description((String)item.get("description"))
                                .isbn13((String)item.get("isbn13"))
                                .priceStandard((int)item.get("priceStandard"))
                                .cover((String)item.get("cover"))
                                .categoryName((String)item.get("categoryName"))
                                .publisher((String) item.get("publisher"))
                        .build());
            }

            //List<BestSellerBook> numList = LinkedHashMap.values(map.get("item"));
//            System.out.println(numList.getClass().getName());  // java.util.ArrayList
//            System.out.println(bestSellerBookList.getClass().getName());  // java.util.ArrayList

            //TodoList에 있는 isbn이 있다면 여기서 삭제
            List<SelCategoryDto> icategoryList = this.GetMain().getIcategory();
//            System.out.println(icategoryList);
//
//            System.out.println("icategoryList : "+icategoryList.get(0).getIsbn());
//            System.out.println(numList.size());
//            System.out.println("numList : " + bestSellerBookList.get(0).getIsbn13());
//            System.out.println(icategoryList.get(0).getIsbn().equals(bestSellerBookList.get(0).getIsbn13()));

            for (int i = 0; i < bestSellerBookList.size(); i++) {
                for (int j = 0; j < icategoryList.size(); j++) {
                    if(icategoryList.get(j).getIsbn().equals(bestSellerBookList.get(i).getIsbn13())){ // 수정이 잦다 링크드 리스트 써라....
                        System.out.println("중복된 책 제거!!!!!!!!");
                        System.out.println(bestSellerBookList.get(i));
                        // 중복된 책 제거
                        bestSellerBookList.remove(i);
                        System.out.println("중복된 책 제거 완료 !!!");
                    }else{
                        System.out.println("중복된 책 없음!!!!!");
                    }
                }
            }

            // 배열 이니까 random으로 순서 바꿔놓기
            Collections.shuffle(bestSellerBookList);

            // 다시 map을 json으로 변하기
            jsonPrintString = mapper.writeValueAsString(bestSellerBookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }

}
