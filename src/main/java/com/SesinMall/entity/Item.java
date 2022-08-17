package com.SesinMall.entity;

import com.SesinMall.constant.ItemCategory;
import com.SesinMall.constant.SellStatus;
import com.SesinMall.dto.ItemFormDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String itemNm;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;


//    public Item updateItem(ItemFormDto itemFormDto){
//
//    }
}
