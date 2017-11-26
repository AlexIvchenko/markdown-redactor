package io.github.alexivchenko.markdownredactor.security;

import io.github.alexivchenko.markdownredactor.core.repositories.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class RootSecurityConfig {
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder builder, UserRepository repository) {
        builder.authenticationProvider(authenticationProvider(repository));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserRepository repository) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(repository));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return new UserDetailsServiceImpl(repository);
    }
}
