package com.TDD.ABnB.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor

public class AppRole implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @NotNull
    @Column(name="role_name")
    private String name;

    @OneToMany(targetEntity = AppUser.class, cascade = {CascadeType.ALL})
    private List<AppUser> appUsers;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
