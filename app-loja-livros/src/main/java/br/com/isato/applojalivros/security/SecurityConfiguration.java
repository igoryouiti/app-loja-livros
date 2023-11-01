package br.com.isato.applojalivros.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(new AntPathRequestMatcher("/users", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/**", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/{id}", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/password", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/active/{id}", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/inactive/{id}", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/{id}", HttpMethod.DELETE.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers", HttpMethod.DELETE.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers/**", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers/**", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers/**", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/customers/**", HttpMethod.DELETE.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books/**", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books/**", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books/**", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books/**", HttpMethod.DELETE.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/inventory/**", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/inventory/**", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/inventory/**", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/inventory/**", HttpMethod.DELETE.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/chart-inventory/**", HttpMethod.GET.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/chart-inventory/**", HttpMethod.POST.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/chart-inventory/**", HttpMethod.PUT.toString())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/chart-inventory/**", HttpMethod.DELETE.toString())).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .httpBasic(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
