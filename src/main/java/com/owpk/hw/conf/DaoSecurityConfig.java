package com.owpk.hw.conf;

import com.owpk.hw.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());
    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Authentication Provider");

//        Permit all grants for anonymous
//        http.anonymous().authorities("USER")
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/").permitAll();

        http
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").permitAll()
            .and()
            .authorizeRequests()
                .antMatchers("/api/v1/products").hasAnyRole("USER", "ADMIN")

            .and()
                .formLogin();

//        http.authorizeRequests()
//                .antMatchers("/authenticated/**").authenticated()
//                .antMatchers("/dao").authenticated()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN") // "ADMIN" equals "ROLE_ADMIN"
//                .anyRequest().permitAll()
//                .and()
//                .formLogin();
    }

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