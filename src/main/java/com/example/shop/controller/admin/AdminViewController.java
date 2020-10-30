package com.example.shop.controller.admin;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.service.ItemService;
import com.example.shop.util.PagingSearchAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminViewController {
    private static final int NUMBER_OF_ITEMS_IN_A_ADMIN_PAGE = 20;

    @Autowired
    private ItemService itemService;

    @GetMapping("/item/register")
    public String registerItem(Model model) {
        CreateItemRq rq = new CreateItemRq();
        model.addAttribute("createItemRq", rq);

        return "admin/item/register";
    }

    @GetMapping("/item/list")
    public String findItemList(@RequestParam("page") int page, Model model) {
        PagingSearchAndSort pagingSearchAndSort = new PagingSearchAndSort();
        pagingSearchAndSort.setPageIndex(page);
        pagingSearchAndSort.setPageCount(NUMBER_OF_ITEMS_IN_A_ADMIN_PAGE);

        model.addAttribute("paging", pagingSearchAndSort);
        model.addAttribute("itemlist", itemService.findAllItems(pagingSearchAndSort));

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
