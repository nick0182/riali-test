package com.nikolai.riali.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "param")
@Data
public class RequestProperty {

    private String page;
    private String pageSize;
    private String minPrice;
    private String maxPrice;
    private String minBed;
    private String maxBed;
    private String minBath;
    private String maxBath;
}
