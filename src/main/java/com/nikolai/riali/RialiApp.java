package com.nikolai.riali;

import com.nikolai.riali.property.RequestProperty;
import com.nikolai.riali.repository.ListingRepository;
import com.nikolai.riali.runner.DBRunner;
import com.nikolai.riali.service.ListingService;
import com.nikolai.riali.service.impl.ListingServiceImpl;
import jdk.nashorn.api.scripting.URLReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

@SpringBootApplication
@EnableConfigurationProperties
public class RialiApp {

    @Value("${source.file.url}")
    private String sourceFileUrl;

    public static void main(String[] args) {
        SpringApplication.run(RialiApp.class, args);
    }

    @Bean
    public CSVParser csvParser() throws IOException {
        return CSVFormat.DEFAULT
                .withHeader("id", "street", "status", "price", "bedrooms", "bathrooms", "sq_ft", "lat", "lng")
                .withFirstRecordAsHeader()
                .parse(new URLReader(URI.create(sourceFileUrl).toURL(), Charset.defaultCharset()));
    }

    @Bean
    public CommandLineRunner dbRunner(CSVParser csvParser, ListingRepository listingRepository) {
        return new DBRunner(csvParser, listingRepository);
    }

    @Bean
    public ListingService listingService(ListingRepository listingRepository) {
        return new ListingServiceImpl(listingRepository);
    }

    @Bean
    public RequestProperty requestProperty() {
        return new RequestProperty();
    }
}
