package com.green.booktodolist.plan.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class PlanTodoDto {
    private int iuser;
    private int ibook;
    private String startDate;
    private String finishedDate;
    private String memo;
    private int del_yn;
    private int finish_yn;
    private int createdAt;
    private int bookmark;
}
