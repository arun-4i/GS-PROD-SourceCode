package com.mobile.integration.grandstores.Utils.JwtWebSecure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired 
    private MyUserDetailsService myUserDetailsService;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        // super.configure(auth);
    }

    @Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

        
    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
        System.out.println("===>1");
    	httpSecurity.csrf().disable()
				.authorizeRequests()
                .antMatchers(getPublicURL()).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	
    }


    public String[] getPublicURL(){
        
        String[] pathArray = new String[]
        {
            // Application Open API
            "/grandstores/module/authentication",
            "/grandstores/module/temp",
            "/module/authentication",
            "/module/temp",
            "/module/authentication",
            "/module/authen",
            "/module/getIPDetails",
            // "/grandstores/module/pi/docnumber",
            // Swagger
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
            // Others
        };
        return pathArray;
    }




}      
