package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "property")
@Data
@NoArgsConstructor
public class AppProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="property_id")
    private Long id;

    @NotNull
    @Column(name="property_name")
    private String name;

    @NotNull
    @Column(name="property_bedroom")
    private int bedroomNum;

    @NotNull
    @Column(name="property_bathroom")
    private int bathroomNum;

    @NotNull
    @Column(name="property_address")
    private String address;


    //kinh do
    @NotNull
    @Column(name="property_longitude")
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

    @NotNull
    @Column(name="property_price")
    private long pricePerDay;


    //vi do
    @NotNull
    @Column(name="property_latitude")
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;


}
