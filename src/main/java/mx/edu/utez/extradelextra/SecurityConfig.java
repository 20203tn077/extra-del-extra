package mx.edu.utez.extradelextra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMINISTRATOR")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("capturista")
                .password("123")
                .roles("TRANSCRIBER")
                .build();
        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("cliente")
                .password("123")
                .roles("CLIENT")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/", "/assets/**").permitAll()
                .requestMatchers("/capturistas").hasAnyRole("ADMINISTRATOR")
                .requestMatchers("/clientes").hasAnyRole("ADMINISTRATOR", "TRANSCRIBER")
                .requestMatchers("/pedidos").hasAnyRole("ADMINISTRATOR", "TRANSCRIBER", "CLIENT")
                .requestMatchers("/productos").hasAnyRole("CLIENT", "ANONYMOUS")
                .requestMatchers("/registro").hasAnyRole("ANONYMOUS")
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
        return http.build();
    }
}
