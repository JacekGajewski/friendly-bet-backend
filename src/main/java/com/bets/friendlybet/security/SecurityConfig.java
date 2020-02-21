package com.bets.friendlybet.security;

import com.bets.friendlybet.auth.ApplicationUserService;
import com.bets.friendlybet.jwt.JwtConfig;
import com.bets.friendlybet.jwt.JwtTokenVerifier;
import com.bets.friendlybet.jwt.JwtUsernamePasswordAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder PASSWORD_ENCODER;
    private final ApplicationUserService APPLICATION_USER_SERVICE;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public SecurityConfig(PasswordEncoder password_encoder, ApplicationUserService application_user_service, SecretKey secretKey, JwtConfig jwtConfig) {
        PASSWORD_ENCODER = password_encoder;
        APPLICATION_USER_SERVICE = application_user_service;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

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
                    .antMatchers("/", "index", "/bets", "/users", "/css/*", "/js/*")
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/login")
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                .httpBasic();
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
        provider.setPasswordEncoder(PASSWORD_ENCODER);
        provider.setUserDetailsService(APPLICATION_USER_SERVICE);
        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTION", "PUT"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type", "UserId", "Access-Control-Allow-Origin", "ExpiresIn"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "UserId", "Access-Control-Allow-Origin", "ExpiresIn"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
