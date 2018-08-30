package com.rabiloo.sharedocument.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void cofigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			
			.antMatchers("/").hasRole("CUSTOMER")
			.antMatchers("/info-user").hasRole("CUSTOMER")
			.antMatchers("/user").hasRole("ADMIN")
			.antMatchers("/feedback").hasRole("ADMIN")
			.antMatchers("/subject").hasRole("ADMIN")
			.antMatchers("/document").hasRole("CUSTOMER")
			.antMatchers("/document/user-document/*").hasRole("CUSTOMER")
			.antMatchers("/exam").hasRole("CUSTOMER")
			.antMatchers("/share-document").permitAll()
			.antMatchers("/my-document").hasRole("CUSTOMER")
			.and()
			.formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/share-document")
				.failureUrl("/login?error").and()
				.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
