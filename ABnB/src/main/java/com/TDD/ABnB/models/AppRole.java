package com.TDD.ABnB.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor

public class AppRole implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @NotNull
    @Column(name="role_name")
    private String name;

    @OneToMany
    private Collection<AppUser> appUsers;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
