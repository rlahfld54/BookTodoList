package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelFinishDto;
import com.green.booktodolist.todoList.model.SelTitleDto;
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
}
