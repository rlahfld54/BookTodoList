package com.green.booktodolist.bestSeller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.booktodolist.bestSeller.model.BestSellerBook;
import com.green.booktodolist.plan.PlanMapper;
import com.green.booktodolist.plan.model.PlanBookDto;
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
    private BestSellerMapper bestSellerMapper;

    @Autowired
    public BestSellerController(AladinSearchApi aladin, TodoService todoService, BestSellerMapper bestSellerMapper) {
        this.aladin = aladin;
        this.todoService = todoService;
        this.bestSellerMapper = bestSellerMapper;
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

                if(item.get("isbn13") != ""){
                    // ibook 테이블에 저장하기
                    bestSellerMapper.insertBestseller(
                            BestSellerBook.builder()
                                    .categoryName(11)
                                    .title((String)item.get("title"))
                                    .author((String)item.get("author"))
                                    .publisher((String) item.get("publisher"))
                                    .isbn13((String)item.get("isbn13"))
                                    .totalpage(100)
                                    .build()
                    );

                    // 프론트엔드에 보내줄 리스트 결과
                    bestSellerBookList.add(BestSellerBook.builder()
                            .title((String)item.get("title"))
                            .link((String)item.get("link"))
                            .author((String)item.get("author"))
                            .pubDate((String)item.get("pubDate"))
                            .description((String)item.get("description"))
                            .isbn13((String)item.get("isbn13"))
                            .priceStandard((int)item.get("priceStandard"))
                            .cover((String)item.get("cover"))
                            .categoryName(11)
                            .publisher((String) item.get("publisher"))
                            .build());
                }
            }
            
            // bestSellerBookList와 TodoList에 있는 책이 중복된 것이 있다고 하면 제거
            // -- isbn으로 같은 책인지 구분함

            List<SelCategoryDto> icategoryList = this.GetMain().getIcategory();
            System.out.println("icategoryList : "+this.GetMain().getIcategory()); // 추후에 테스트 확인
            for (int i = 0; i < bestSellerBookList.size(); i++) {
                for (int j = 0; j < icategoryList.size(); j++) {
                    if(icategoryList.get(j).getIsbn().equals(bestSellerBookList.get(i).getIsbn13())){ // 수정이 잦다 링크드 리스트 써라....
                        System.out.println(bestSellerBookList.get(i));
                        // 중복된 책 제거
                        bestSellerBookList.remove(i);
                        System.out.println("중복된 책 제거 완료 !!!");
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
