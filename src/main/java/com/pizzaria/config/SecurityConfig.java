package com.pizzaria.config;

import com.pizzaria.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableWebSecurity // Habilita a segurança web do projeto
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMINISTRADOR");


        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/images/**")
                .antMatchers("/js/**")
                .antMatchers("/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/fornecedores/new").hasAnyAuthority("CADASTRAR_FORNECEDOR", "ADMINISTRADOR", "VENDEDOR")
                    .antMatchers("/usuarios/**").hasAnyAuthority("CADASTRAR_USUARIO", "ADMINISTRADOR")
                    .antMatchers("/grupos/**").hasAnyAuthority("CADASTRAR_GRUPO", "ADMINISTRADOR")
                    .antMatchers("/mesas/new").hasAnyAuthority("CADASTRAR_MESA", "ADMINISTRADOR")
                    .antMatchers("/pizzas/new").hasAnyAuthority("CADASTRAR_PIZZA", "ADMINISTRADOR")
                    .antMatchers("/promocoes/new").hasAnyAuthority("CADASTRAR_PROMOCAO", "ADMINISTRADOR")
                    .antMatchers("/titulos/new").hasAnyAuthority("FINANCEIRO", "ADMINISTRADOR", "VENDEDOR")
                    .antMatchers("/produtos/new").hasAnyAuthority("FINANCEIRO", "ADMINISTRADOR", "VENDEDOR")

                    .anyRequest().authenticated() // Para qualquer requisição o usuário deve estar autenticado
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
