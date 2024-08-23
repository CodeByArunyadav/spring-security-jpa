package com.springsecurityjpa;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        http.authorizeHttpRequests(requests -> requests
        		.antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll());
	
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
