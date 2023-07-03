package com.green.booktodolist.plan.model;

import lombok.Data;

@Data
// 검색시 프론트로 보내는 객체
public class PlanBookDataDto {
    private String title; // 제목
    private int cate; // 카테고리
    private String author; // 지은이
    private String company; // 출판사
    private String isbn; // isbn
    private String addcode; // 부가기호
    private String totalpage;
}
