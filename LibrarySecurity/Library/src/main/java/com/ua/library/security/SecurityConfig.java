package com.ua.library.security;

import com.ua.library.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("/")
                .antMatchers("/welcome");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests()
                .antMatchers("/book/add", "book/{id}/*")
                .hasAuthority("ADMIN")

                .and()

                .authorizeRequests()
                .antMatchers("/book","/book/{id}","/book/{id}/comment/**")
                .hasAuthority("USER")

                .and()

                .authorizeRequests()
                .antMatchers("/**")
                .denyAll()

                .and()

                .formLogin()
                .failureForwardUrl("/loginError")
                .defaultSuccessUrl("/welcome")

                .and()

//                .anonymous()
//                .authorities("ROLE_ANONYMOUS")
//                .principal("Anon")
//
//                .and()

                .rememberMe()
                .key("AppKey")
                .tokenValiditySeconds(60*60*24*30)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    //.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN")
}
