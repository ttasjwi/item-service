package com.ttasjwi.itemservice.web.basic;

import com.ttasjwi.itemservice.domain.item.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemCreateRequest {

    private final String itemName; // 상품명
    private final Integer price; // 가격
    private final Integer quantity; // 수량

    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
