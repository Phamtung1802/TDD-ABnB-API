package com.TDD.ABnB.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appInvoiceSet","appBookingSet"})
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
    @Column(name="property_longitude")
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double longitude;

    //vi do
    @Column(name="property_latitude")
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double latitude;

    @NotNull
    @Column(name="property_price")
    private long pricePerDay;

    @ManyToMany(mappedBy = "appPropertySet")
    @JsonIgnoreProperties("appPropertySet")
    Set<AppInvoice> appInvoiceSet;

    @OneToMany(targetEntity = AppBooking.class, cascade = CascadeType.ALL, mappedBy = "appProperty")
    @JsonIgnoreProperties({"appProperty"})
    private Set<AppBooking> appBookings= new HashSet<AppBooking>();

    @OneToMany(targetEntity = AppReview.class, cascade = CascadeType.ALL, mappedBy = "appProperty")
    private Set<AppReview> appReviews=new HashSet<AppReview>();

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"appProperties","appReviews","appBookings"})
    private AppUser appUser;

    @OneToMany(targetEntity = AppImage.class, cascade = CascadeType.ALL, mappedBy = "appProperty")
    @JsonIgnoreProperties({"appProperty"})
    private List<AppImage> appImages;
}
