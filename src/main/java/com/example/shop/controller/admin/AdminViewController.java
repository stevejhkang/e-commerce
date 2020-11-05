package com.example.shop.controller.admin;

import com.example.shop.controller.item.rqrs.CreateItemRq;
import com.example.shop.domain.item.Item;
import com.example.shop.service.ItemService;
import com.example.shop.util.PagingSearchAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminViewController {
    private static final int NUMBER_OF_ITEMS_IN_A_ADMIN_PAGE = 20;

    @Autowired
    private ItemService itemService;

    @GetMapping("/item/register")
    public String createItem(Model model) {

        CreateItemRq rq = new CreateItemRq();
        model.addAttribute("createItemRq", rq);

        return "admin/item/register";
    }

    @GetMapping("/item/list/{page}")
    public String readItems(@PathVariable("page") int page, Model model) {

        PagingSearchAndSort pagingSearchAndSort = new PagingSearchAndSort();
        pagingSearchAndSort.setPageIndex(page);
        pagingSearchAndSort.setPageCount(NUMBER_OF_ITEMS_IN_A_ADMIN_PAGE);

        model.addAttribute("paging", pagingSearchAndSort);
        model.addAttribute("itemlist", itemService.readAllItems(pagingSearchAndSort));

        return "admin/item/list";
    }

    @GetMapping("/item/detail/{itemNo}")
    public String readItem(@PathVariable("itemNo") int itemNo, Model model) {

        Item item = itemService.readItem(itemNo);
        model.addAttribute("item", item);

        return "admin/item/detail";
    }

    @GetMapping("/item/edit/{itemNo}")
    public String updateItem(@PathVariable("itemNo") int itemNo, Model model) {

        model.addAttribute("item", itemService.readItem(itemNo));

        return "admin/item/edit";
    }
}
