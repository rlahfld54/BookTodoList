package com.green.booktodolist.todoList.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelMainVo {
    private int level;
    List<SelCategoryDto> icategory;
}
