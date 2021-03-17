package com.nikolai.riali.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String street;

    @Column
    private String status;

    @Column
    private Long price;

    @Column
    private Integer bedrooms;

    @Column
    private Integer bathrooms;

    @Column
    private Integer sq_ft;

    @Column
    private Double lat;

    @Column
    private Double lng;
}
