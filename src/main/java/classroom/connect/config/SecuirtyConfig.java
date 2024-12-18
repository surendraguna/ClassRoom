package classroom.connect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
@EnableWebSecurity
public class SecuirtyConfig {

    private final CustomSuccessHandler successHandler;

    public SecuirtyConfig(CustomSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
            .csrf(res -> res.disable())
            .authorizeHttpRequests(req -> { req
                .requestMatchers("/reg","/css/**","/js/**", "/img/**").permitAll()
                .requestMatchers("/verify-otp").authenticated()
                .requestMatchers("/classroom/**", "/user/**").access((authentication, request) -> {
                    HttpServletRequest servletRequest = (HttpServletRequest) request.getRequest();
                    Boolean is2faVerified = (Boolean) servletRequest.getSession().getAttribute("2faVerified");
                    return new AuthorizationDecision(is2faVerified != null && is2faVerified);
                })
                .anyRequest().denyAll();
            })
            .formLogin(httpForm ->{ httpForm
                .loginPage("/").permitAll()
                .successHandler(successHandler)
                .failureUrl("/?error=true");
            })
            .logout(logout -> { logout
                .logoutSuccessUrl("/")
                .permitAll();
            })
            .exceptionHandling(ex -> ex
                .accessDeniedHandler(accessDeniedHandler) 
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder()); 
        return provider;
    }    
}
