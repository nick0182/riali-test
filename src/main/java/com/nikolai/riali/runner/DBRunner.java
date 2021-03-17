package com.nikolai.riali.runner;

import com.nikolai.riali.model.Listing;
import com.nikolai.riali.repository.ListingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVParser;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class DBRunner implements CommandLineRunner {

    private final CSVParser csvParser;
    private final ListingRepository listingRepository;

    @Override
    public void run(String... args) {
        List<Listing> listingList = new ArrayList<>();
        csvParser.forEach(record -> {
            Listing listing = new Listing();
            listing.setStreet(record.get("street"));
            listing.setStatus(record.get("status"));
            listing.setPrice(Long.valueOf(record.get("price")));
            listing.setBedrooms(Integer.valueOf(record.get("bedrooms")));
            listing.setBathrooms(Integer.valueOf(record.get("bathrooms")));
            listing.setSq_ft(Integer.valueOf(record.get("sq_ft")));
            listing.setLat(Double.valueOf(record.get("lat")));
            listing.setLng(Double.valueOf(record.get("lng")));

            listingList.add(listing);
        });
        listingRepository.saveAll(listingList);
        log.info("Added listing count: {}", listingRepository.count());
    }

    @PreDestroy
    public void closeResource() {
        try {
            log.info("Closing resources...");
            csvParser.close();
        } catch (IOException exception) {
            // ignored
        }
    }
}
