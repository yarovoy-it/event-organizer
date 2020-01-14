package by.home.eventOrganizer.config;

import by.home.eventOrganizer.security.filter.AuthenticationTokenFilter;
import by.home.eventOrganizer.service.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(TokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .mvcMatchers("/authentication/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/beverages/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/staff/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/customers/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/orders/**").hasAnyRole(USER, ADMIN)
                .mvcMatchers(HttpMethod.POST, "/customers/**").hasAnyRole(USER, ADMIN)
                .mvcMatchers(HttpMethod.POST, "/beverages/**", "/staff/**", "/goods/**","/roles/**").hasRole(ADMIN)
                .mvcMatchers(HttpMethod.PUT, "/beverages/**", "/staff/**", "/goods/**").hasRole(ADMIN)
                .mvcMatchers(HttpMethod.DELETE, "/beverages/**", "/staff/**", "/goods/**","/roles/**").hasRole(ADMIN);
        final AuthenticationTokenFilter filter = new AuthenticationTokenFilter(tokenService, userDetailsService);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }


}
