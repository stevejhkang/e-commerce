package com.example.shop.controller.item;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.service.ItemService;
import com.example.shop.util.PagingSearchAndSort;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    private ItemService itemService;

    private static final String FIRST_PAGE_URL = "/admin/item/list/1";

    @PostMapping("/createItem")
    public ResponseEntity createItem(@ModelAttribute("createItemRq") @Valid CreateItemRq createItemRq,
                                     @RequestParam("mainImg") MultipartFile mainImg) {

        String result = itemService.createItem(createItemRq, mainImg);

        return ResponseEntityUtil.redirectResponse(FIRST_PAGE_URL);
    }

    @PostMapping("/updateItem")
    public ResponseEntity updateItem(@ModelAttribute("item") Item item,
                                     @RequestParam("mainImg") MultipartFile mainImg) {

        String result = itemService.updateItem(item, mainImg);

        return ResponseEntityUtil.redirectResponse(FIRST_PAGE_URL);
    }

    @PostMapping("/itemlist")
    public ResponseEntity readAllItems(@RequestBody PagingSearchAndSort pagingSearchAndSort, Model model) {

        Map<String, Object> map = new HashMap<>();
        map.put("itemlist", itemService.readAllItems(pagingSearchAndSort));
        map.put("paging", pagingSearchAndSort);

        return ResponseEntityUtil.successResponse(map);
    }

    @GetMapping("/itemTotalCount")
    public ResponseEntity readTotalCount(Model model, PagingSearchAndSort pagingSearchAndSort) {

        model.addAttribute("totalCount", itemService.readTotalCount(pagingSearchAndSort));
        return ResponseEntityUtil.successResponse();
    }

    @GetMapping("/deleteItem")
    public ResponseEntity deleteItem(@RequestParam("itemNo") int itemNo) {
        String result = itemService.deleteItem(itemNo);

        return ResponseEntityUtil.redirectResponse(FIRST_PAGE_URL);
    }
}
