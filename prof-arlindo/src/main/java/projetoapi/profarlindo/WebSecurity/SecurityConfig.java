package projetoapi.profarlindo.WebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Desabilita proteção CSRF
                .authorizeRequests()
                .requestMatchers("/funcionario").authenticated()  // Requer autenticação para /funcionarios
                .anyRequest().permitAll()  // Permite todas as outras solicitações
                .and()
                .httpBasic();  // Usa autenticação básica HTTP
        return http.build();
    }
}