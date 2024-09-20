package org.nic.bug_tracker_system.config;

import org.nic.bug_tracker_system.serviceImpl.UserDetailsServiceImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/api/ticket/**", "/all").hasAnyRole("ADMIN", "DEVELOPER")
//                .requestMatchers("/developer/**").hasRole("DEVELOPER")
//                .requestMatchers("/assigner/**").hasRole("ASSIGNER")
//                .requestMatchers("/**").permitAll()  // Allow all other requests
//            )
//            .formLogin(form -> form
//                .loginPage("/login")  // Default login page
//                .permitAll()
//            )
//            .httpBasic()  // Enable basic auth for Postman
//            .and()
//            .csrf().disable();  // Disable CSRF for Postman testing
//
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/BTS/app/Login").permitAll()  // Allow login page
                .requestMatchers("/api/ticket/**", "/all").hasAnyRole("ADMIN", "DEVELOPER")
                .requestMatchers("/developer/**").hasRole("DEVELOPER")
                .requestMatchers("/assigner/**").hasRole("ASSIGNER")
                .requestMatchers("/**").permitAll()  // Allow all other requests
            )
            .formLogin(form -> form
                .loginPage("/BTS/app/Login")  // Define custom login page URL
                .defaultSuccessUrl("/all", true)  // Redirect after successful login
                .permitAll()
            )
            .httpBasic()  // Enable basic auth for Postman
            .and()
            .csrf().disable();  // Disable CSRF for Postman testing

        return http.build();
    }



  
}
