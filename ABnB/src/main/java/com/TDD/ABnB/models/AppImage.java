package com.TDD.ABnB.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "iamges")
@Getter
@Setter
@NoArgsConstructor
public class AppImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long id;

    @Column(name="image_name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties({"appUser","appReviews","appBookingSet","appInvoiceSet","appImages"})
    private AppProperty appProperty;

}
