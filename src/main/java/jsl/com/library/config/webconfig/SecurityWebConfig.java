package jsl.com.library.config.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c.requestMatchers(HttpMethod.GET, "/api/v1/*").permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(HttpMethod.POST, "/api/v1/*").permitAll())
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
