package com.example.shop.util;

import lombok.Data;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Data
public class PagingSearchAndSort extends Paging {
    private String searchType;
    private String searchText;
    private String orderBy;
    private String startDate;
    private String endDate;
    private Object whereVo;

    public String makeQueryString(int page) {
        setPageIndex(page);
        Optional<String> searchText = Optional.ofNullable(this.getSearchText());
        UriComponents uriComponents = null;
        if (searchText.isPresent()) {
            uriComponents = UriComponentsBuilder.newInstance()
                                                .queryParam("pageIndex", page)
                                                .queryParam("searchType", searchType)
                                                .queryParam("searchText", searchText.get())
                                                .queryParam("orderBy", orderBy)
                                                .build()
                                                .encode();
        }
        else {
            uriComponents = UriComponentsBuilder.newInstance()
                                                .queryParam("pageIndex", page)
                                                .queryParam("orderBy", orderBy)
                                                .build()
                                                .encode();
        }
        return uriComponents.toUriString();
    }
}
