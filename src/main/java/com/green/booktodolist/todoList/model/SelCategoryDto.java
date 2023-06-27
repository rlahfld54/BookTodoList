package com.green.booktodolist.todoList.model;

import lombok.Data;

import java.util.List;

@Data
public class SelCategoryDto {
    private String cate_name;
    List<SelTitleDto> list;
}
