package com.example.apollofy.rest;

import com.example.apollofy.service.SearchService;
import com.example.apollofy.service.dto.SearchDTO;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    private final SearchService searchService;

    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    //GET api/search/?
    @GetMapping()
    public SearchDTO getSearchByParam (@RequestParam String q, @PageableDefault(page=0, size=1) Pageable pageable) {
        return searchService.findByKeyword(q, pageable);
    }
}


