package com.green.booktodolist.plan.model;

import lombok.Builder;
import lombok.Data;
@Data

//todolist 입력 데이터
public class PlanTodoDto {
    private int iuser;
    private Long ibook;
    private String start;
    private String end;
    private String memo;
    private int del;
    private int finish_yn;
    private int createdAt;
    private int bookmark;
}
