package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import jdk.jfr.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<SelTitleDto> selTitle();
    int selUserLevel();
    List<SelCategoryDto> selcategorylist();
    List<SelFinishDto> selNotFinish();
    List<SelFinishDto> selFinish();
    SelDetailDto selDetail(SelDetailVo vo);
    int UpdTodo(UpdTodoDto dto);
    int DelTodo(DelTodoDto dto);


}
