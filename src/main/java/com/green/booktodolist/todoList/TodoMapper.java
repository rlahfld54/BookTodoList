package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int selUserLevel();
    int selUserCount();
    List<SelCategoryDto> selcategorylist();
    SelDetailDto selDetail(int itodo);
    int UpdTodo(UpdTodoDto dto);
    SelDetailDto  selTodo(int itodo);

    int UpdCount(int num);
    int UpdLevel();

    int DelTodo(DelTodoDto dto);

}
