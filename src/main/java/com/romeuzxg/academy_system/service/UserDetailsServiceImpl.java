package com.romeuzxg.academy_system.service;

import com.romeuzxg.academy_system.entity.Aluno;
import com.romeuzxg.academy_system.repository.AlunoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AlunoRepository alunoRepository;

    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        Aluno aluno = alunoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(aluno.getNome())
                .password(aluno.getSenha())
                .roles(aluno.getRoles().name())
                .build();
    }

}
