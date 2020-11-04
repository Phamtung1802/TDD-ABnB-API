package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NotNull
    @Column(name="user_name", unique = true)
    private String name;

    @NotNull
    @Column(name="user_password")
    private String password;

    @Column(name="user_avatar")
    private String avatar;

    @NotNull
    @Column(name="user_email")
    private String email;

    @NotNull
    @Column(name="user_phone_number")
    @Size(min = 10, max = 11)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRole appRole;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Collection<AppReview> appReviews;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Collection<AppBooking> appBookings;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Collection<AppInvoice> appInvoices;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Collection<AppProperty> appProperties;


}
