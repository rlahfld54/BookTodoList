package com.green.booktodolist.plan;

import com.green.booktodolist.plan.model.PlanBookDto;
import com.green.booktodolist.plan.model.PlanBookInsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.sql.DriverManager.println;

@Slf4j
@Service
public class PlanService {
    private final PlanMapper mapper;

    @Autowired
    public PlanService(PlanMapper mapper) {
        this.mapper = mapper;
    }

    public int insBook(PlanBookInsDto dto){
        PlanBookDto Bookdto = new PlanBookDto();

        //받아온 addcode값에서 icate값 추출
        int temp;
        temp = Integer.parseInt((dto.getAddcode().substring(2,3))) + 1;

        // 3글자만 잘리는지 확인
        log.info("3글자만 잘리는지 확인중");
        Bookdto.setIcate(temp); // 카테고리넣기
        Bookdto.setPublisher(dto.getPublisher());
        Bookdto.setTitle(dto.getTitle());
        Bookdto.setIsbn(dto.getIsbn());
        Bookdto.setWriter(dto.getWriter());
        Bookdto.setAddcode(dto.getAddcode());

        return mapper.insBook(Bookdto);
    }
}
