package ru.otus.ivan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.ivan.model.Role;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/**").authenticated()
                .and()
                    .authorizeRequests().antMatchers("/api/delete/*").hasRole(Role.ADMIN.getAuthority())
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().permitAll()
        ;
    }
}