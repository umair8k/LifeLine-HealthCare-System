package com.lhs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityHandler extends WebSecurityConfigurerAdapter {

	@Autowired
	ImplementationUserDetailsService serve;

	@Bean
	public AuthenticationProvider auth() {

		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(serve);
		dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return dao;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		
	http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("logout")).logoutSuccessUrl("/logg").permitAll();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//	}
//	
//	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		// http.csrf().disable().authorizeRequests().antMatchers("/test").permitAll().antMatchers("/addreg").permitAll().anyRequest().authenticated();
//
//		http.csrf().disable().authorizeRequests().antMatchers("/login","/home","/addreg").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logg").permitAll();
//
//	}
//	
//	
//	
//	
//	@Bean
//	public AuthenticationProvider auth()
//	{
//		DaoAuthenticationProvider dao= new DaoAuthenticationProvider();
//		dao.setUserDetailsService(serve);
//		dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		return dao;
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(serve);
//
//	}

//	@Bean
//	public PasswordEncoder encode() {
//		return NoOpPasswordEncoder.getInstance();
//	}


}
}
