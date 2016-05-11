package edu.sjsu.cmpe275.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.sjsu.cmpe275.Constant;

/**
 * Created by yutao on 5/5/16.
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
    private Md5PasswordEncoder md5PasswordEncoder;
	
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user@qw.com").password("password").roles("USER");
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        
        http.authorizeRequests().antMatchers("/", "/index", "/upload_images/**", "/signupform", "/menu").permitAll()
                    .antMatchers("/static_res/**").permitAll()
                    .antMatchers("/upload_images/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/signin")
                    .defaultSuccessUrl("/")
                    .successHandler(new DispatchAuthenticationSuccessHandler())
                    .permitAll()
                    .loginProcessingUrl("/login")
                    .and()
                .logout().logoutSuccessUrl("/?logout")
                    .permitAll();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(this.userDetailsService);
//		provider.setPasswordEncoder(new Md5PasswordEncoder());
//		provider.setSaltSource(new SaltSource() {
//			@Override
//			public Object getSalt(UserDetails user) {
//				return Constant.SALT;
//			}
//			
//		});
//		auth.authenticationProvider(provider).build();
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(this.md5PasswordEncoder);
        authProvider.setUserDetailsService(this.userDetailsService);
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("salt");
        authProvider.setSaltSource(saltSource);
        auth.authenticationProvider(authProvider);
		
		
//		auth.jdbcAuthentication().passwordEncoder(new Md5PasswordEncoder())
//			.dataSource(dataSource)
//			.usersByUsernameQuery("select email as username, password, active as enabled from user where email=?")
//			.authoritiesByUsernameQuery("select email as username, role from user where email=?");
	}
	
    
}
