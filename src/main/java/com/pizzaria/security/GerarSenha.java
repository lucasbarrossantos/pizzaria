package com.pizzaria.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

public class GerarSenha {

    public static void main(String[] args) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //System.out.printf(encoder.encode("admin"));

        LocalDate hoje = LocalDate.now();
        System.out.println(hoje.minusMonths(6L));

    }

}
