package com.bets.friendlybet.security;

import com.bets.friendlybet.auth.ApplicationUserService;
import com.bets.friendlybet.jwt.JwtConfig;
import com.bets.friendlybet.jwt.JwtTokenVerifier;
import com.bets.friendlybet.jwt.JwtUsernamePasswordAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernamePasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/login", "/css/*", "/js/*")
                    .permitAll()
                .antMatchers("/user/{userId}/**")
                    .access("(@webSecurity.checkUserId(authentication,#userId) AND hasRole('STUDENT')) OR hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/users", "/users/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/{userId}")
                    .access("@webSecurity.checkUserId(authentication,#userId) AND hasAnyRole('STUDENT', 'ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/users/{userId}")
                    .access("@webSecurity.checkUserId(authentication,#userId) AND hasAnyRole('STUDENT', 'ADMIN')")
                .antMatchers(HttpMethod.PATCH, "/users/password/{userId}")
                .access("@webSecurity.checkUserId(authentication,#userId)")
                .antMatchers(HttpMethod.POST, "/users", "/users/**")
                    .permitAll()
                .anyRequest()
                .authenticated();
//                    .and()
//                .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                .httpBasic();
//                .loginPage("/login")
//                .defaultSuccessUrl("/bets", true);
//                .loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout") //POST
//                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTION", "PUT"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type", "UserId", "Access-Control-Allow-Origin", "ExpiresIn"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "UserId", "Access-Control-Allow-Origin", "ExpiresIn"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

@Component
class WebSecurity {
    public boolean checkUserId(Authentication authentication, int id) {
        return ((Integer) authentication.getCredentials()).equals(id);
    }
}
