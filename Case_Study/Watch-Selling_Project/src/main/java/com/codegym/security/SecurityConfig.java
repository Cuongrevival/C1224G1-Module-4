package com.codegym.security;

import com.codegym.model.Customer;
import com.codegym.model.Role;
import com.codegym.repository.ICustomerRepo;
import com.codegym.service.impl.CustomerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
public class SecurityConfig {

    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;
    @Autowired
    private ICustomerRepo customerRepository;
    @PostConstruct
    public void initAdminAccount() {
        Optional<Customer> admin = customerRepository.findByUsername("admin");
        if (!admin.isPresent()) {
            Customer adminUser = new Customer();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder().encode("admin123"));
            adminUser.setFullName("Administrator");
            adminUser.setPhone("0123456789");
            adminUser.setRole(Role.ADMIN);

            customerRepository.save(adminUser);
        }
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/register", "/login", "/css/**", "/js/**", "/images/**", "/watches", "/search").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/watches", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .csrf().disable();


        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customerDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
