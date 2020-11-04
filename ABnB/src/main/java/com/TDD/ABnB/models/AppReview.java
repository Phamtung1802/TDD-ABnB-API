package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    @Column(name="review_rating")
    @Range(min = 1, max = 5)
    private int rating;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @Column(name="review_date")
    @NotNull
    private String date;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id")
    private AppProperty appProperty;



}
