package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

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
    @Column(name="property_status")
    private String status;

    @NotNull
    @Column(name="property_type")
    private String type;


    @NotNull
    @Column(name="property_bedroom")
    private int bedroomNum;

    @NotNull
    @Column(name="property_bathroom")
    private int bathroomNum;

    @NotNull
    @Column(name="property_address")
    private String address;

    @NotNull
    @Column(name="property_description")
    private String description;



    //kinh do
    @NotNull
    @Column(name="property_longitude")
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

    //vi do
    @NotNull
    @Column(name="property_latitude")
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;

    @NotNull
    @Column(name="property_price")
    private long pricePerDay;



    @ManyToMany
    Set<AppInvoice> appInvoiceSet;

    @ManyToMany
    Set<AppBooking> appBookingSet;

    @OneToMany(mappedBy = "appProperty", cascade = CascadeType.ALL)
    private Collection<AppReview> appReviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @OneToMany(mappedBy = "appProperty", cascade = CascadeType.ALL)
    private Collection<AppImage> appImages;





}
