package com.project.shopapp.configurations;

import com.project.shopapp.filters.JwtTokenFilter;
import com.project.shopapp.models.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    @Value("${api.prefix}")
    private String apiPrefix;

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix)
                            )
                            .permitAll()
                            // category
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/categories**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/categories", apiPrefix)).hasRole( RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/categories/**", apiPrefix)).hasRole( RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/categories/**", apiPrefix)).hasRole( RoleModel.ADMIN)

                            //user
//                            .requestMatchers(HttpMethod.GET,
//                                    String.format("%s/users?**", apiPrefix)).hasRole(RoleModel.ADMIN)
//                            .requestMatchers(HttpMethod.POST,
//                                    String.format("%s/users/register", apiPrefix)).hasRole(RoleModel.USER)

                            // product
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/products**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/products", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/products/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/products/**", apiPrefix)).hasRole(RoleModel.ADMIN)

                            //order
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/orders", apiPrefix)).hasRole(RoleModel.USER)
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/orders/**", apiPrefix)).hasAnyRole(RoleModel.ADMIN, RoleModel.USER)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(RoleModel.ADMIN)

                            // order-detail
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/order-details/order/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/order-details/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/order-details", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/order-details/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/order-details/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)
                            
                            .anyRequest().authenticated()
                    ;
                })
        ;
        return http.build();
    }
}
