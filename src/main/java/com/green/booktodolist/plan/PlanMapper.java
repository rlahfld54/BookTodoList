package com.green.booktodolist.plan;

import com.green.booktodolist.plan.model.PlanBookDataDto;
import com.green.booktodolist.plan.model.PlanBookDto;
import com.green.booktodolist.plan.model.PlanBookInsDto;
import com.green.booktodolist.plan.model.PlanTodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    int insBook(PlanBookDto dto);
    int insTodoList(PlanTodoDto dto);
    List<PlanBookDataDto> callapihttp();
}
