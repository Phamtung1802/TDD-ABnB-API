package com.TDD.ABnB.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class AppReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @NotNull
    @Column(name="review_comment")
    private String comment;

    @Column(name="review_rating")
    @Range(min = 1, max = 5)
    private int rating;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"appProperties","appReviews","appBookings"})
    private AppUser appUser;

    @Column(name="review_date")
    @NotNull
    private String date;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnoreProperties({"appUser","appReviews","appBookings"})
    private AppProperty appProperty;



}
