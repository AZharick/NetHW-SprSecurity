package com.example.hiber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Bean
   protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests(auth -> {
                         auth.requestMatchers("/hi").permitAll();
                         auth.requestMatchers("/create", "/update", "/delete").hasAuthority("write");
                         auth.requestMatchers("/getAll", "/getByCity", "/youngerThan", "/getByPK").hasAuthority("read");
                         auth.anyRequest().authenticated();
                      }
              )
              .formLogin(withDefaults());
      return http.build();
   }

   @Bean
   protected UserDetailsService userDetailsService() {
      var admin = User.builder()
              .username("admin")
              .password("{noop}admin")
              .authorities("read", "write")
              .build();
      var user = User.builder()
              .username("Alex")
              .password("{noop}qwerty")
              .authorities("read")
              .build();
      return new InMemoryUserDetailsManager(admin, user);
   }

}