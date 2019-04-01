package com.fsd.springboothibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
		@Override
		protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
			authBuilder.inMemoryAuthentication().withUser("user").password("{noop}password1").roles("USER")
			.and()
			.withUser("admin").password("{noop}password2").roles("ADMIN");
			
		}
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        		http
	                .httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers("/bookstore/books/**").hasRole("USER")  // allow to show books for only USER role
	                .antMatchers("/bookstore/subjects/**").hasRole("ADMIN") // allow to show Subjects for only ADMIN role
	                .and()
	                .csrf().disable()
	                .formLogin().disable();
	    }

}
