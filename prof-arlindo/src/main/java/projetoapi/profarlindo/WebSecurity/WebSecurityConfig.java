package projetoapi.profarlindo.WebSecurity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import projetoapi.profarlindo.Error.ErrorMessage;

@Configuration
public class WebSecurityConfig {

    private final ErrorMessage errorMessage;

    public WebSecurityConfig(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Operation(description = "Permissões de acesso ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Error de autorização, nao possui permissao"),
            @ApiResponse(responseCode = "200", description = "Permissao autorizada e poderá acessar todo o banco"),
    })

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated() // Protege todos os métodos do endpoint /funcionario
                        .anyRequest().permitAll()
                )
                .httpBasic()
                .authenticationEntryPoint(errorMessage) // Configura o entry point personalizado
                .and()
                .csrf().disable(); // Desativa CSRF para facilitar testes (não recomendado para produção)
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configura o encoder de senha
    }
}
