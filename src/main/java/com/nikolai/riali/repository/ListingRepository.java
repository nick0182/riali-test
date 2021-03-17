package com.nikolai.riali.repository;

import com.nikolai.riali.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListingRepository extends PagingAndSortingRepository<Listing, Long> {
    Page<Listing> findByPriceBetweenAndBathroomsBetweenAndBedroomsBetween(
            Long priceFrom,
            Long priceTo,
            Integer bathFrom,
            Integer bathTo,
            Integer bedFrom,
            Integer bedTo,
            Pageable pageable
    );
}
