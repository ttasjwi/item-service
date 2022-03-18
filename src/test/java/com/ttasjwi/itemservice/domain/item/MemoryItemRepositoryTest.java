package com.ttasjwi.itemservice.domain.item;

import com.ttasjwi.itemservice.web.basic.ItemUpdateRequest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryItemRepositoryTest {

    private MemoryItemRepository memoryItemRepository;

    @BeforeEach
    void setUp() {
        this.memoryItemRepository = new MemoryItemRepository();
    }

    @Test
    @DisplayName("상품 등록 -> 성공")
    void saveTest() {
        //given
        Item saveItem = Item.builder().itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        memoryItemRepository.save(saveItem);

        //when
        Item findItem = memoryItemRepository.findById(saveItem.getId()).get();

        //then
        assertThat(findItem.getItemName()).isEqualTo(findItem.getItemName());
    }

    @Test
    @DisplayName("상품 2개 등록, findAll -> 성공")
    void findAllTest() {
        //given
        Item saveItem1 = Item.builder().itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        Item saveItem2 = Item.builder().itemName("itemB")
                .price(20000)
                .quantity(20)
                .build();

        //when
        memoryItemRepository.save(saveItem1);
        memoryItemRepository.save(saveItem2);
        List<Item> items = memoryItemRepository.findAll();

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(items.size()).isEqualTo(2);
        softAssertions.assertThat(items).contains(saveItem1, saveItem2);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("상품 update -> 성공")
    void updateItemTest() {
        //given
        Item item = Item.builder().itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        //when
        memoryItemRepository.save(item);
        Long id = item.getId();

        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest("itemB", 20000, 20);
        memoryItemRepository.update(id, itemUpdateRequest);

        //then
        Item findItem = memoryItemRepository.findById(id).get();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findItem.getItemName()).isEqualTo(itemUpdateRequest.getItemName());
        softAssertions.assertThat(findItem.getPrice()).isEqualTo(itemUpdateRequest.getPrice());
        softAssertions.assertThat(findItem.getQuantity()).isEqualTo(itemUpdateRequest.getQuantity());
        softAssertions.assertAll();
    }
}