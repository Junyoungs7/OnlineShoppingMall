package com.SesinMall.repository;

import com.SesinMall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm);

    @Query("select o from Item o where o.itemDetail like %:itemDetail% order by o.price desc ")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
