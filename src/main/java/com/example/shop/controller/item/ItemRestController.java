package com.example.shop.controller.item;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.service.ItemService;
import com.example.shop.util.Paging;
import com.example.shop.util.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    ItemService itemService;

    @PostMapping("/createItem")
    public ResponseEntity createItem(@ModelAttribute("createItemRq") @Valid CreateItemRq createItemRq,
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
    public ResponseEntity findAll(@RequestBody Paging paging, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemlist",itemService.findAllItems(paging));
        map.put("paging",paging);

        return ResponseEntityUtil.successResponse(map);
    }

    @GetMapping("/itemTotalCount")
    public ResponseEntity findTotalCount(Model model){
        model.addAttribute("totalCount", itemService.findTotalCount());
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
