package ru.gb.Homework7.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthHandler authenticationSuccessHandler;

    // фильтр безопасности для разных страниц и разных ролей
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/css/**", "/showroom", "/login").permitAll()
                        .requestMatchers("/user-profile").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin-profile", "book-sell/{name}").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf().disable();
        return http.build();
    }

    // кодировщик паролей
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // добавление ролей и паролей для разных пользователей
    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        var commonUser = User.withUsername("user").password("{noop}password").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}password").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(commonUser, admin);
    }
}
