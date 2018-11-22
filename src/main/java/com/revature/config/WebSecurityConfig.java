package com.revature.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").permitAll();
////                .anyRequest().authenticated()
////                .and()
//////                // Filter before login request
////                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
////                        UsernamePasswordAuthenticationFilter.class)
////                // Filter other requests foor JWT 
////                .addFilterBefore(new JWTAuthenticationFilter(),
////                        UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Create a default account
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("password")
//                .roles("DOCTOR");
//    }
}