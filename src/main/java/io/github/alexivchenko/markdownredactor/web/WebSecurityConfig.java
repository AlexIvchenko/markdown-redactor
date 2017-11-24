package io.github.alexivchenko.markdownredactor.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Alex Ivchenko
 */
@Configuration
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/signUp", "/signIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/signIn")
                .and().logout().logoutUrl("/logout");
    }
}
