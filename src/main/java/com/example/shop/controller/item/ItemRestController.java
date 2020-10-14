package com.example.shop.controller.item;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    ItemService itemService;

    //createProduct
    @PostMapping("/createItem")
    public ResponseEntity<String> createItem(@ModelAttribute("createItemRq") @Valid CreateItemRq createItemRq,
                                              @RequestParam("mainImg") MultipartFile mainImg,
                                              Model model){
//        @RequestParam("mainImg") MultipartFile mainImg,
//        itemService.createItem()
        System.out.println("test");
//        model.addAttribute("createItemRq",new CreateItemRq());

        ResponseEntity<String> entity = null;
        int result = itemService.createItem(createItemRq,mainImg);
        return entity;
    }

    //deleteProduct

    //updateProduct

    //readProduct
}
