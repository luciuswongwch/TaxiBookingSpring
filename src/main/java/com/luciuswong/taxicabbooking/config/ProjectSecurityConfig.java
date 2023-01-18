package com.luciuswong.taxicabbooking.config;

import com.luciuswong.taxicabbooking.constants.TaxiCabBookingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {
    @Autowired
    private CustomProperties customProperties;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers(PathRequest.toH2Console())
                .ignoringRequestMatchers("/data-api/**")
                //.ignoringRequestMatchers("/public/**")
                .and().authorizeHttpRequests()
                .requestMatchers("/profile").authenticated()
                .requestMatchers("/updateProfile").authenticated()
                .requestMatchers("/updatePassword").authenticated()
                .requestMatchers("/admin/**").hasRole(TaxiCabBookingConstants.ADMIN_ROLE)
                .requestMatchers("/api/**").authenticated()
                //.requestMatcher("/data-api/**").authenticated()
                .requestMatchers("/").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/services").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveContactMsg").permitAll()
                .requestMatchers("/booking").permitAll()
                .requestMatchers("/saveBooking").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/profile").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
