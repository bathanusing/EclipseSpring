package com.firstAplication.springboot.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//Crear usuario jean/dummy
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("jean").password("dummy").roles("USER").and().withUser("admin1").password("secret1")
				.roles("USER", "ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
				.antMatchers("/", "/*todo*/**", "/*surveys*/**").access("hasRole('USER')").and()
                .formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
	//Crear un formulario para logearse
}
