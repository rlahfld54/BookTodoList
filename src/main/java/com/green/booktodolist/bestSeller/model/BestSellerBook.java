package com.green.booktodolist.bestSeller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BestSellerBook {
    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String description;
    private String isbn13;
    private int priceStandard;
    private String cover;
    private int categoryName;
    private String publisher;
    private int totalpage;
}
