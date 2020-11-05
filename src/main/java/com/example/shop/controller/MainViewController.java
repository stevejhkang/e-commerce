package com.example.shop.controller;

import com.example.shop.service.ItemService;
import com.example.shop.util.PagingSearchAndSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainViewController {
    static final int NUMBER_OF_ITEMS_IN_MAIN_PAGE = 9;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/store")
    public String store(@ModelAttribute("paging") PagingSearchAndSort pagingSearchAndSortParams, Model model) {
        pagingSearchAndSortParams.setPageCount(NUMBER_OF_ITEMS_IN_MAIN_PAGE);

        model.addAttribute("itemlist", itemService.readAllDisplayedItems(pagingSearchAndSortParams));
        model.addAttribute("paging", pagingSearchAndSortParams);

        return "store";
    }
}
