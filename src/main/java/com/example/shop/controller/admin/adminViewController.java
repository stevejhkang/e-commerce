package com.example.shop.controller.admin;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.DeliveryOption;
import com.example.shop.domain.item.DisplayOption;
import com.example.shop.service.ItemService;
import com.example.shop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class adminViewController {
    @Autowired
    ItemService itemService;

    @GetMapping("/item/register")
    public String registerItem(Model model) {
        CreateItemRq rq = new CreateItemRq();
        model.addAttribute("createItemRq", rq);

        return "admin/item/register";
    }

    @GetMapping("/item/list")
    public String findItemList(@RequestParam("page") int page, Model model) {
        Paging paging = new Paging();
        paging.setPageIndex(page);

        model.addAttribute("paging", paging);
        model.addAttribute("itemlist", itemService.findAllItems(paging));

        return "admin/item/list";
    }

    @GetMapping("/item/detail")
    public String findItem(@RequestParam("itemNo") int itemNo, Model model) {
        model.addAttribute("item", itemService.findItem(itemNo));
        return "admin/item/detail";
    }

    @GetMapping("/item/edit")
    public String updateItem(@RequestParam("itemNo") int itemNo, Model model) {
        model.addAttribute("item", itemService.findItem(itemNo));
        return "admin/item/edit";
    }


}
