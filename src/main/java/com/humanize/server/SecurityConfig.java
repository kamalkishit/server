package com.humanize.server;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Value("${pie.admin}")
    String pieAdmin;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        // TODO - Add CSRF support if required, disabling as of now (hampering
        // POST requests).
        http.csrf().disable().addFilter(customLoginFilter()).authorizeRequests().antMatchers("/favicon.ico").permitAll()
        .antMatchers("/static/**").permitAll()
        .antMatchers("/api/pie/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/health").permitAll()
        .antMatchers("/home/**").hasAuthority(pieAdmin)
        .antMatchers("/**").hasAuthority(pieAdmin)
        .antMatchers("/partials/**").permitAll()
        .antMatchers("/templates/**").permitAll()
        .anyRequest().authenticated().and().formLogin().loginPage(
                "/login").permitAll().defaultSuccessUrl("/home").and().logout().permitAll()
        .and().exceptionHandling().accessDeniedPage("/noaccess");
        // @formatter:on
    }

    public static final String FORM_USERNAME_KEY = "username";
    public static final String FORM_PASSWORD_KEY = "password";

    public static final String LOGIN_POST_URL = "/login/process";

    /**
     * Simple customization of UsernamePasswordAuthenticationFilter.
     */
    @Bean
    public Filter customLoginFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_POST_URL));
        filter.setUsernameParameter(FORM_USERNAME_KEY);
        filter.setPasswordParameter(FORM_PASSWORD_KEY);
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/home"));
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
               authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }
}