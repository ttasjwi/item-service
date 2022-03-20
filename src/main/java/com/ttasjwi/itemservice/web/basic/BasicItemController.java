package com.ttasjwi.itemservice.web.basic;

import com.ttasjwi.itemservice.domain.item.Item;
import com.ttasjwi.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();

        itemRepository.save(item);
        model.addAttribute(item);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("itemCreateRequest") ItemCreateRequest itemCreateRequest,
                            Model model) {
        Item item = itemCreateRequest.toEntity();
        itemRepository.save(item);
        model.addAttribute(item); // @ModelAttribute("item")으로 바로 item을 생성했을 경우, item이 자동으로 model에 추가됨
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute ItemCreateRequest itemCreateRequest,
                            Model model) {
        Item item = itemCreateRequest.toEntity();
        itemRepository.save(item);
        model.addAttribute(item); // @ModelAttribute("item")으로 바로 item을 생성했을 경우, item이 자동으로 model에 추가됨
        return "/basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(ItemCreateRequest itemCreateRequest,
                            Model model) {
        Item item = itemCreateRequest.toEntity();
        itemRepository.save(item);
        model.addAttribute(item); // @ModelAttribute("item")으로 바로 item을 생성했을 경우, item이 자동으로 model에 추가됨
        return "/basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new NoSuchElementException("해당 상품 id에 일치하는 상품이 존재하지 않습니다."));
        model.addAttribute("item", item);
        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateRequest itemUpdateRequest) {
        itemRepository.update(itemId, itemUpdateRequest);
        return "redirect:/basic/items/{itemId}";
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
