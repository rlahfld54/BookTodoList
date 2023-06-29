package com.green.booktodolist.plan;

import com.green.booktodolist.plan.model.PlanBookDto;
import com.green.booktodolist.plan.model.PlanBookInsDto;
import com.green.booktodolist.plan.model.PlanTodoDto;
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

        int temp;
        temp = Integer.parseInt((dto.getAddcode().substring(2,3))) + 1;

        log.info("카테고리 분류 중");
        Bookdto.setIcate(temp); // 카테고리넣기
        Bookdto.setPublisher(dto.getPublisher());
        Bookdto.setTitle(dto.getTitle());
        Bookdto.setIsbn(dto.getIsbn());
        Bookdto.setWriter(dto.getWriter());
        Bookdto.setAddcode(dto.getAddcode());
        log.info("book DB작성완료");
        return mapper.insBook(Bookdto);
    }

    public int postTodolist(PlanTodoDto dto){
        log.info("투두리스트 작성 시작");
        dto.setIuser(dto.getIuser());
        dto.setIbook(dto.getIbook());
        dto.setStartDate(dto.getStartDate());
        dto.setFinishedDate(dto.getFinishedDate());
        dto.setDel_yn(dto.getDel_yn());
        dto.setFinish_yn(dto.getDel_yn());
        dto.setBookmark(dto.getBookmark());
        log.info("투두리스트 작성 완료");

        return mapper.insTodoList(dto);
    }
}
