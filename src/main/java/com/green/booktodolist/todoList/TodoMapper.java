package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int selUserLevel();
    List<SelCategoryDto> selcategorylist();
    SelDetailDto selDetail(int itodo);
    int UpdTodo(UpdTodoDto dto);
    int DelTodo(DelTodoDto dto);


}
