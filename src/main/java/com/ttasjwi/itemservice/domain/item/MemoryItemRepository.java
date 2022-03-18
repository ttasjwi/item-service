package com.ttasjwi.itemservice.domain.item;

import com.ttasjwi.itemservice.web.basic.ItemUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryItemRepository implements ItemRepository{

    private final Map<Long, Item> store;
    private final AtomicLong sequence;

    public MemoryItemRepository() {
        this.store = new ConcurrentHashMap<>();
        this.sequence = new AtomicLong();
    }

    @Override
    public Long save(Item item) {
        Long id = sequence.incrementAndGet();
        item.initId(id);
        store.put(id, item);
        return id;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, ItemUpdateRequest updateRequest) {
        Item findItem = findById(id)
                .orElseThrow(() -> new NoSuchElementException("그런 상품은 존재하지 않습니다."));

        findItem.updateItemName(updateRequest.getItemName());
        findItem.updatePrice(updateRequest.getPrice());
        findItem.updateQuantity(updateRequest.getQuantity());
    }
}
