package com.green.booktodolist.plan.model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookVo {
    String publisher; // 출판사 (발행자)
    String eaAddCode; // 부가기호
    String author; // 저자
    String eaIsbn; // isbn
    String title; // 제목
    String page; // 페이지수
}
