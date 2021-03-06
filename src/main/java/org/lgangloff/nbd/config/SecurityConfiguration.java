package org.lgangloff.nbd.config;

import org.lgangloff.nbd.repository.PersistentTokenRepository;
import org.lgangloff.nbd.repository.UserRepository;
import org.lgangloff.nbd.security.AjaxAuthenticationFailureHandler;
import org.lgangloff.nbd.security.AjaxAuthenticationSuccessHandler;
import org.lgangloff.nbd.security.AjaxLogoutSuccessHandler;
import org.lgangloff.nbd.security.AuthoritiesConstants;
import org.lgangloff.nbd.security.CustomPersistentRememberMeServices;
import org.lgangloff.nbd.security.Http401UnauthorizedEntryPoint;
import org.lgangloff.nbd.web.filter.CsrfCookieGeneratorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
	private PersistentTokenRepository tokenRepository;
    
    @Autowired
	private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
	        .antMatchers("/*.{js,html}")
	        .antMatchers("/scripts/**/*.{js,html}")
            .antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String remerberMeKey = env.getProperty("security.rememberme.key");
    	
        http
        .csrf()
	        .ignoringAntMatchers("/**")
	    .and()
	        .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
	        .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .rememberMe().key(remerberMeKey)
            .rememberMeServices(new CustomPersistentRememberMeServices(remerberMeKey, userDetailsService, tokenRepository, userRepository))
            .rememberMeParameter("remember-me")
        .and()
            .formLogin()
            .loginProcessingUrl("/api/authentication")
            .successHandler(ajaxAuthenticationSuccessHandler)
            .failureHandler(ajaxAuthenticationFailureHandler)
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(ajaxLogoutSuccessHandler)
            .deleteCookies("JSESSIONID")
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/dl-files/**").permitAll()
            .antMatchers("/api/account").authenticated()
            .antMatchers(HttpMethod.POST, "/api/files/**").hasAuthority(AuthoritiesConstants.MANAGER) //creer fichier
            .antMatchers("/api/website/**").hasAuthority(AuthoritiesConstants.MANAGER) //Gerer website
            .antMatchers("/api/coachs/**").hasAuthority(AuthoritiesConstants.MANAGER) //Gerer coach
            .antMatchers("/api/programs/**").hasAuthority(AuthoritiesConstants.MANAGER) //Gerer programs
            .antMatchers("/api/**").hasAuthority(AuthoritiesConstants.ADMIN); //sinon faut être admin

    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
