package com.green.booktodolist.plan.model;

import lombok.Data;

@Data
public class PlanApiDto {
    // api에서 받아오는 데이터

    String publisher; // 출판사 (발행자)
    String eaAddCode; // 부가기호
    String author; // 저자
    String eaIsbn; // isbn
    String title; // 제목
    String page; // 페이지수

    String ddc;
    String updateDate;
    String publisherUrl;
    String seriesTitle;
    String kdc;
    String editionStmt;
    String bookTbCntUrl;
    String setIsbn;
    String realPublishDate;
    String titleUrl;
    String prePrice;
    String bookIntroductionUrl;
    String depositYn;
    String bookSize;
    String bookSummaryUrl;
    String ebookYn;
    String realPrice;
    String form;
    String formDetail;
    String controlNo;
    String seriesNo;
    String inputDate;
    String setExpression;
    String vol;
    String cipYn;
    String subject;
    String bibYn;
    String publishPredate;
    String relatedIsbn;
    String setAddCode;
}
