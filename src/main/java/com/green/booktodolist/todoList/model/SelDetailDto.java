package com.green.booktodolist.todoList.model;

import lombok.Data;

@Data
public class SelDetailDto {
    private String createdTodo;
    private String catename;
    private String title;
    private String writer;
    private String startDate;
    private String finishedDate;
    private String memo;
    private String finishYn;


}
