package com.ff.config;

import com.ff.entity.enum_pkg.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigure {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    private final HeaderSettingFilter headerSettingFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .requestMatchers("/manageProduct/**").hasAnyRole(RoleEntity.ADMIN.name(), RoleEntity.PRODUCT_MANAGER.name())
                .requestMatchers("/manageOrder/**").hasRole(RoleEntity.ADMIN.name())
                .requestMatchers("/manageCategory/**").hasAnyRole(RoleEntity.ADMIN.name(), RoleEntity.PRODUCT_MANAGER.name())
                .requestMatchers("/manageCustomer/**").hasRole(RoleEntity.ADMIN.name())
                .requestMatchers("/product").hasAnyRole(RoleEntity.CONTENT.name(), RoleEntity.ADMIN.name(), RoleEntity.CUSTOMER.name(), RoleEntity.MODERATOR.name(), RoleEntity.SHIPPER.name(), RoleEntity.PRODUCT_MANAGER.name())
                .requestMatchers("/user").hasAnyRole(RoleEntity.CONTENT.name(), RoleEntity.ADMIN.name(), RoleEntity.CUSTOMER.name(), RoleEntity.MODERATOR.name(), RoleEntity.SHIPPER.name(), RoleEntity.PRODUCT_MANAGER.name())
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(headerSettingFilter, CorsFilter.class);
        return http.build();
    }
}