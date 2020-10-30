package com.example.shop.controller.item;

import com.example.shop.controller.item.rqrs.createItemRq;
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

    @PostMapping("/createItem")
    public ResponseEntity createItem(@ModelAttribute("createItemRq") @Valid createItemRq createItemRq,
                           @RequestParam("mainImg") MultipartFile mainImg){

        int result = itemService.createItem(createItemRq,mainImg);

        String newURL = "/admin/item/list?page=1";
        return ResponseEntityUtil.redirectResponse(newURL);
    }

    @PostMapping("/updateItem")
    public ResponseEntity updateItem(@ModelAttribute("item") Item item,
                          @RequestParam("mainImg") MultipartFile mainImg) {

        String result = itemService.updateItem(item, mainImg);

        String newURL = "/admin/item/list?page=1";
        return ResponseEntityUtil.redirectResponse(newURL);
    }

    @PostMapping("/itemlist")
    public ResponseEntity findAll(@RequestBody PagingSearchAndSort pagingSearchAndSort, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemlist",itemService.findAllItems(pagingSearchAndSort));
        map.put("paging", pagingSearchAndSort);

        return ResponseEntityUtil.successResponse(map);
    }

    @GetMapping("/itemTotalCount")
    public ResponseEntity findTotalCount(Model model, PagingSearchAndSort pagingSearchAndSort){
        model.addAttribute("totalCount", itemService.findTotalCount(pagingSearchAndSort));
        return ResponseEntityUtil.successResponse();
    }

    //deleteItem
    @GetMapping("/deleteItem")
    public ResponseEntity deleteItem(@RequestParam("itemNo") int itemNo){
        String result = itemService.deleteItem(itemNo);
        String newURL = "/admin/item/list?page=1";

        return ResponseEntityUtil.redirectResponse(newURL);
    }
}
