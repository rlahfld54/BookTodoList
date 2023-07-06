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
    int updel(UpdDel dto);
    int updTodo(UpdTodoDto dto);
    SelDetailDto  selTodo(int itodo);

    int updCount();
    int updLevel();

    int DelTodo(DelTodoDto dto);

}
