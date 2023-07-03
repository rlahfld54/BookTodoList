package com.green.booktodolist.plan;

import com.green.booktodolist.plan.model.PlanBookDataDto;
import com.green.booktodolist.plan.model.PlanBookDto;
import com.green.booktodolist.plan.model.PlanTodoDto;
import com.green.booktodolist.plan.model.PlanTodoInsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    Long insBook(PlanBookDto dto);
    int insTodoList(PlanTodoInsDto dto);
    List<PlanBookDataDto> callapihttp();
}
