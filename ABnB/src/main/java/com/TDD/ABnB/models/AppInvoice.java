package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class AppInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private Long id;

    @Column(name="invoice_checkin_date")
    @NotNull
    private String checkinDate;

    @Column(name="invoice_checkout_date")
    @NotNull
    private String checkoutDate;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "app_property_invoice",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    Set<AppProperty> appPropertySet;


}
