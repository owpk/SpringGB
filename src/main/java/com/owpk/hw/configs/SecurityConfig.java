package com.owpk.hw.configs;

import com.owpk.hw.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authed/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .and()
//                .loginPage("/login") // Default GET /login
//                .loginProccessingUrl("/authTheUser");
    }

    //In memory auth
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$z9KmUV97xn0OjChmdK7To.y5YcPAacMxuBXLRj2DjM6gRmLuyfLVC")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$z9KmUV97xn0OjChmdK7To.y5YcPAacMxuBXLRj2DjM6gRmLuyfLVC")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
