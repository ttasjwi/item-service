package com.ttasjwi.itemservice.web.basic;

import com.ttasjwi.itemservice.domain.item.Item;
import com.ttasjwi.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("해당 상품 id에 일치하는 상품이 존재하지 않습니다."));
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(Item.builder()
                .itemName("testA")
                .price(10000)
                .quantity(10)
                .build());
        itemRepository.save(Item.builder()
                .itemName("testB")
                .price(20000)
                .quantity(20)
                .build());
    }
}
