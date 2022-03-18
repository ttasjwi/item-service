package com.ttasjwi.itemservice.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {

    private Long id; // 아이디
    private String itemName; // 상품명
    private Integer price; // 가격
    private Integer quantity; // 수량

    void initId(Long id) {
        this.id = id;
    }

    void updateItemName(String itemName) {
        this.itemName = itemName;
    }

    void updatePrice(Integer price) {
        this.price = price;
    }

    void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
