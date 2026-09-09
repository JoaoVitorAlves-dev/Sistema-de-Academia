package com.romeuzxg.academy_system.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RegisterRequestDto {

    private String nome;
    private String email;
    private String senha;

}
