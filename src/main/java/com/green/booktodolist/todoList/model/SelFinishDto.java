package com.green.booktodolist.todoList.model;

import lombok.Data;

@Data
public class SelFinishDto {
    private int itodo;
    private String title;
    private String bookmark;
    private int finish;
}
