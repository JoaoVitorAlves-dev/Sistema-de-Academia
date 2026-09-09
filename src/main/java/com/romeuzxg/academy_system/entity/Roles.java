package com.romeuzxg.academy_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Roles implements GrantedAuthority {

    @Id
    private Integer id;
    private String nome;

    @Override
    public @Nullable String getAuthority() {
        return nome;
    }
}
