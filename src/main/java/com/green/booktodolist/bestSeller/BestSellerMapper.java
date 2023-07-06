package com.green.booktodolist.bestSeller;

import com.green.booktodolist.bestSeller.model.BestSellerBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BestSellerMapper {
    void insertBestseller(BestSellerBook dto);
}
