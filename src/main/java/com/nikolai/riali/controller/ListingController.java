package com.nikolai.riali.controller;

import com.nikolai.riali.property.RequestProperty;
import com.nikolai.riali.service.ListingService;
import lombok.AllArgsConstructor;
import org.geojson.GeoJsonObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ListingController {

    private final ListingService listingService;

    private final RequestProperty requestProperty;

    @GetMapping("/listings")
    public GeoJsonObject filterListings(@RequestParam Map<String, String> requestParams) {
        int page = Integer.parseInt(Optional.ofNullable(requestParams.get("page")).orElse(requestProperty.getPage()));
        int pageSize = Integer.parseInt(Optional.ofNullable(requestParams.get("page_size")).orElse(requestProperty.getPageSize()));
        long priceMin = Long.parseLong(Optional.ofNullable(requestParams.get("min_price")).orElse(requestProperty.getMinPrice()));
        long priceMax = Long.parseLong(Optional.ofNullable(requestParams.get("max_price")).orElse(requestProperty.getMaxPrice()));
        int bedMin = Integer.parseInt(Optional.ofNullable(requestParams.get("min_bed")).orElse(requestProperty.getMinBed()));
        int bedMax = Integer.parseInt(Optional.ofNullable(requestParams.get("max_bed")).orElse(requestProperty.getMaxBed()));
        int bathMin = Integer.parseInt(Optional.ofNullable(requestParams.get("min_bath")).orElse(requestProperty.getMinBath()));
        int bathMax = Integer.parseInt(Optional.ofNullable(requestParams.get("max_bath")).orElse(requestProperty.getMaxBath()));
        return listingService.getListings(priceMin, priceMax, bathMin, bathMax, bedMin, bedMax, PageRequest.of(page, pageSize));
    }
}
