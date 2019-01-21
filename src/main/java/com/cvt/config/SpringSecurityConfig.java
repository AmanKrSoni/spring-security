package com.cvt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*add user for auth in memory*/
        UserBuilder users= User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
                .withUser(users.username("marry").password("test123").roles("EMPLOYEE","MANAGER"))
                .withUser(users.username("aman").password("test123").roles("EMPLOYEE","MANAGER","ADMIN"))
                .withUser(users.username("susan").password("test123").roles("EMPLOYEE","ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                                                /*.anyRequest().authenticated()*/
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/managers/**").hasRole("MANAGER")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                    .and()
                    .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/access-denied");
    }
}
