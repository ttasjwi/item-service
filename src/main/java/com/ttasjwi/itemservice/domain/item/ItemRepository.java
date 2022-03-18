package com.ttasjwi.itemservice.domain.item;

import com.ttasjwi.itemservice.web.basic.ItemUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Long save(Item item);
    Optional<Item> findById(Long id);
    List<Item> findAll();
    void update(Long id, ItemUpdateRequest updateRequest);
}
