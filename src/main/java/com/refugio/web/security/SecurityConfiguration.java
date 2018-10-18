package com.refugio.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService cu;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
		
		authManager.userDetailsService(cu).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//deshabilitamos el csrf
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/animal/**")
		.authenticated()
		.antMatchers("/")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.logout()
		//ruta cuando el usuario haga logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//ruta al cerrar sesion
		.logoutSuccessUrl("/login");
			
	}
}
