package com.green.booktodolist.bestSeller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BestSellerBook {
    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String description;
    private String isbn13;
    private int priceStandard;
    private String cover;
    private String categoryName;
}
