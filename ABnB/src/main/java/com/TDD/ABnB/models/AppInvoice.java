package com.TDD.ABnB.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appPropertySet"})
public class AppInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private Long id;

    @Column(name="invoice_date")
    @NotNull
    private String invoiceDate;


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
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    @JsonIgnoreProperties({"appInvoiceSet","appBookingSet"})
    Set<AppProperty> appPropertySet;


}
