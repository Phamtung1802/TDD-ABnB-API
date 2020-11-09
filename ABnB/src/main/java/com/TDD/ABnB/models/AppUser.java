package com.TDD.ABnB.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements Serializable {
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

    @Column(name="user_address")
    private String address;

    @Column(name="user_realname")
    private String realName;


    @NotNull
    @Column(name="user_phone_number")
    @Size(min = 10, max = 11)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRole appRole;

    @OneToMany
    private Collection<AppReview> appReviews;

    @OneToMany
    private Collection<AppBooking> appBookings;

    @OneToMany
    private Collection<AppInvoice> appInvoices;

    @OneToMany(targetEntity = AppProperty.class, cascade = CascadeType.ALL, mappedBy = "appUser")
    private Set<AppProperty> appProperties=new HashSet<AppProperty>();


}
