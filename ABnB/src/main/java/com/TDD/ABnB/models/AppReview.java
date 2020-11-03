package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class AppReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @NotNull
    @Column(name="review_comment")
    private String comment;

    @NotNull
    @Column(name="review_rating")
    @Size(min = 1, max = 5)
    private String rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @Column(name="review_date")
    @NotNull
    private String date;


    @ManyToMany
    @NotNull
    @JoinTable(
            name = "app_property_review",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    Set<AppProperty> appPropertySet;



}
