package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class AppImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long id;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id")
    private AppProperty appProperty;

}
