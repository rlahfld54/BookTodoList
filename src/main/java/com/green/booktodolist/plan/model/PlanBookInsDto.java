package com.green.booktodolist.plan.model;

import lombok.Builder;
import lombok.Data;
@Data
public class PlanBookInsDto {
    private String title;
    private String writer;
    private String publisher;
    private String isbn;
    private String addcode; // 책 부가기호
}
