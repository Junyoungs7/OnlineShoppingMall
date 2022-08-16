package com.SesinMall.dto;

import com.SesinMall.constant.ItemCategory;
import com.SesinMall.constant.SellStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private int price;

    @NotBlank(message = "재고량은 필수 입력 값입니다.")
    private int stockNumber;

    @NotNull(message = "상품 설명은 필수 입력 값입니다.")
    private String itemDetail;

    private ItemCategory itemCategory;

    private SellStatus sellStatus;

}
