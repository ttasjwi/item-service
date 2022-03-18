package com.ttasjwi.itemservice.web.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ItemUpdateRequest {

    private final String itemName; // 상품명
    private final Integer price; // 가격
    private final Integer quantity; // 수량

}
