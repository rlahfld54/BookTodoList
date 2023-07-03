package com.green.booktodolist.todoList.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelMainVo {
//    List<SelTitleDto> TodoTitle;
    private int level;
    List<SelCategoryDto> icategory;
//    List<SelFinishDto>NotFinish;
//    List<SelFinishDto>Finish;


}
