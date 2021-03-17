package com.nikolai.riali.service.impl;

import com.nikolai.riali.model.Listing;
import com.nikolai.riali.repository.ListingRepository;
import com.nikolai.riali.service.ListingService;
import lombok.AllArgsConstructor;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;

    @Override
    public GeoJsonObject getListings(Long priceFrom,
                                     Long priceTo,
                                     Integer bathFrom,
                                     Integer bathTo,
                                     Integer bedFrom,
                                     Integer bedTo,
                                     Pageable pageable) {
        Page<Listing> result = listingRepository.findByPriceBetweenAndBathroomsBetweenAndBedroomsBetween(priceFrom,
                priceTo, bathFrom, bathTo, bedFrom, bedTo, pageable);
        List<Feature> features = result.stream()
                .map(listing -> {
                    Feature feature = new Feature();
                    feature.setGeometry(new Point(listing.getLng(), listing.getLat()));
                    feature.setProperty("id", listing.getId());
                    feature.setProperty("price", listing.getPrice());
                    feature.setProperty("street", listing.getStreet());
                    feature.setProperty("bedrooms", listing.getBedrooms());
                    feature.setProperty("bathrooms", listing.getBathrooms());
                    feature.setProperty("sq_ft", listing.getSq_ft());
                    return feature;
                })
                .collect(Collectors.toList());
        FeatureCollection featureCollection = new FeatureCollection();
        featureCollection.setFeatures(features);
        return featureCollection;
    }
}
