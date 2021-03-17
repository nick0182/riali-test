package com.nikolai.riali.service;

import org.geojson.GeoJsonObject;
import org.springframework.data.domain.Pageable;

public interface ListingService {

    GeoJsonObject getListings(Long priceFrom,
                              Long priceTo,
                              Integer bathFrom,
                              Integer bathTo,
                              Integer bedFrom,
                              Integer bedTo,
                              Pageable pageable);
}
