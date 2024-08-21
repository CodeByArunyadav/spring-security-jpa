package com.springsecurityjpa;

import javax.security.auth.message.config.AuthConfigProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.HttpRequestHandler;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoauthprovider = new DaoAuthenticationProvider();
		daoauthprovider.setUserDetailsService(userDetailsService());
		daoauthprovider.setPasswordEncoder(passwordEncoder());
		return daoauthprovider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
	}

//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()
//		    .withUser("Admin")
//		    .password("admin")
//		    .roles("ADMIN")
//		    .and()
//		    .withUser("User")
//		    .password("user")
//		    .roles("USER");
//	} 
//	
//	public void configure(HttpSecurity http) throws Exception
//	{
//		http.authorizeHttpRequests()
//		    .antMatchers("/admin").hasRole("ADMIN")
//		    .antMatchers("/user").hasAnyRole("USER","ADMIN")
//		    .antMatchers("/").permitAll()
//		    .and()
//		    .formLogin();
//	  	
//		
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return NoOpPasswordEncoder.getInstance();
//	}

}
