package com.wish.app.config.security;

import com.wish.app.config.auth2.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by wangl on 2017/2/10.
 */
@Configuration
//用于@PreAuthorize的生效,基于方法的权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthUserService authUserService;

    @Autowired
    public WebSecurityConfig(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(authUserService)
                .passwordEncoder(passwordEncoder());
        // 设置手机验证码登陆的AuthenticationProvider
//        authenticationManagerBuilder.authenticationProvider(mobileAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/oauth/token", "/oauth/authorize", "/oauth/confirm_access").permitAll()
                .antMatchers("/oauth/authorize").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }


}
