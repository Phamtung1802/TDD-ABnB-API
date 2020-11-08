package com.TDD.ABnB.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appPropertySet"})
public class AppBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long id;

    @Column(name="booking_check_in_date")
    @NotNull
    private String checkinDate;

    @Column(name="booking_check_out_date")
    @NotNull
    private String checkoutDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "app_property_booking",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    @JsonIgnoreProperties({"appInvoiceSet","appBookingSet"})
    Set<AppProperty> appPropertySet;
}
