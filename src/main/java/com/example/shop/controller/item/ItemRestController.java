package com.example.shop.controller.item;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.Item.Item;
import com.example.shop.service.ItemService;
import com.example.shop.util.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    ItemService itemService;

    //createProduct
    @PostMapping("/createItem")
    public void createItem(@ModelAttribute("createItemRq") @Valid CreateItemRq createItemRq,
                                              @RequestParam("mainImg") MultipartFile mainImg,
                                              Model model,
                                             HttpServletResponse response){
//        @RequestParam("mainImg") MultipartFile mainImg,
//        itemService.createItem()
        System.out.println("createItem");
//        model.addAttribute("createItemRq",new CreateItemRq());

        ResponseEntity<String> entity = null;
        int result = itemService.createItem(createItemRq,mainImg);

        try {
            response.sendRedirect("/admin/item/list?page=1");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    //updateItem
    @PostMapping("/updateItem")
    public void updateItem(@ModelAttribute("item") Item item,
                          @RequestParam("mainImg") MultipartFile mainImg,
                          Model model,
                           HttpServletResponse response) {
        System.out.println("updateItem");
        String result = itemService.updateItem(item, mainImg);

        try {
//            response.sendRedirect("/admin/item/detail?itemNo="+item.getItemSn());
            response.sendRedirect("/admin/item/list?page=1");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //findAll
    @PostMapping("/itemlist")
    public Map<String, Object> findAll(@RequestBody Paging paging, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemlist",itemService.findAll(paging));
        map.put("paging",paging);
        return map;
    }

    @GetMapping("/itemTotalCount")
    public String findTotalCount(Model model){
        model.addAttribute("totalCount", itemService.findTotalCount());
        return "success";
    }

    //deleteItem
    @GetMapping("/deleteItem")
    public void deleteItem(@RequestParam("itemNo") int itemNo, HttpServletResponse response){
        String result = itemService.deleteItem(itemNo);

        try {
            response.sendRedirect("/admin/item/list?page=1");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
