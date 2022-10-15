package com.nhn.config;

import com.nhn.common.Constant;
import com.nhn.filter.JwtFilter;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // hasAuthority for jwt

        http
                .cors()
                .and()
                .authorizeRequests()
//                .antMatchers("/authenticated").permitAll()
//                .antMatchers("/current-user").permitAll()
//                .antMatchers("/public/**").permitAll()
//                .antMatchers("/success").authenticated()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/index").permitAll()
//                .antMatchers("/assets/**").permitAll()
                .antMatchers("/admin/api/**").hasAuthority(Constant.USER_ROLE.ADMIN)
                .antMatchers("/candidate/api/**").hasAuthority(Constant.USER_ROLE.CANDIDATE)
                .antMatchers("/company/api/**").hasAuthority(Constant.USER_ROLE.COMPANY)
                .antMatchers("/auth/**", "/authed/api/**", "/public/**").permitAll()
                .anyRequest().authenticated()
//                .antMatchers("/**").permitAll()

//                .formLogin().permitAll()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/success")
//                .failureUrl("/error")

                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}