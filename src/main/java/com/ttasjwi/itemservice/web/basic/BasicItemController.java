package com.ttasjwi.itemservice.web.basic;

import com.ttasjwi.itemservice.domain.item.Item;
import com.ttasjwi.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

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
