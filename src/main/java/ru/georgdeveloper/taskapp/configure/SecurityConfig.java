package ru.georgdeveloper.taskapp.configure;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.georgdeveloper.taskapp.services.CustomUserDetailsService;

/**
 * Авторизация и контроль доступа к ресурсам
 */
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/registration/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").authenticated()
                                .requestMatchers("/index/**").authenticated()
                                .requestMatchers("/create/**").authenticated()
                                .requestMatchers("/**").authenticated()
                                .requestMatchers("/search/**").hasRole("ADMIN")
                                .requestMatchers("task_table/delete/**").hasRole("ADMIN")
                                .requestMatchers("/task_table/change/**").hasRole("ADMIN")
                                .requestMatchers("task_table/find/**").hasRole("ADMIN")
                                .requestMatchers("update/**").hasRole("ADMIN")
                                .anyRequest().authenticated());
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


}
