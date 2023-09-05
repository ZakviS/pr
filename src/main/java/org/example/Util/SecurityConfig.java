package org.example.Util;


//import org.example.controller.AuthController;
//import org.example.controller.HelloController;
//import org.example.service.security.TokenAuthenticationManager;
import org.example.Controller.AuthController;
import org.example.Controller.HelloController;
import org.example.services.Security.TokenAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

import static org.example.Controller.AuthController.AUTH_CONTROLLER_METHOD_REG_PATH_ANT;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    private final String authenticatedPathAnt = "/**";
    private final String[] publicPathsAnt = new String[]{
            HelloController.HELLO_CONTROLLER_METHOD_PUBLIC_PATH_ANT,
            AuthController.AUTH_CONTROLLER_METHOD_SIGN_IN_PATH_ANT,
            AuthController.AUTH_CONTROLLER_METHOD_CONFIG_PATH_ANT,
            AUTH_CONTROLLER_METHOD_REG_PATH_ANT,
            "/employee/**",
            "/position/**",
            "/premium/**",
            "/allowance/**",
            "/salary/**"


    };
    private final RequestMatcher publicPathsRequestMatcher = new OrRequestMatcher(
            Arrays.stream(publicPathsAnt).map(v -> new AntPathRequestMatcher(v)).toArray(value -> new RequestMatcher[value])
    );

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(authenticatedPathAnt, publicPathsRequestMatcher);
        filter.setAuthenticationManager(tokenAuthenticationManager);
        return filter;
    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//        http.csrf().disable();
//        http.logout().disable();
//        http.requestCache().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new CORSFilter(), TokenAuthenticationFilter.class);
//        http.authorizeRequests()
//                .antMatchers(publicPathsAnt).permitAll()
//                .antMatchers(authenticatedPathAnt).authenticated();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(httpSecurityCorsConfigurer -> )
//                .addFilterBefore(new CORSFilter(), TokenAuthenticationFilter.class);
//                .authorizeRequests(authorize -> authorize
    //                .requestMatchers(publicPathsAnt).permitAll()
    //                .requestMatchers(authenticatedPathAnt).authenticated()
//                  )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
        //        http.headers().frameOptions().disable();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CORSFilter(), TokenAuthenticationFilter.class)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(
                                publicPathsAnt).permitAll()
                        .requestMatchers(authenticatedPathAnt).authenticated()
                );
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.logout(AbstractHttpConfigurer::disable);
//        http.requestCache(AbstractHttpConfigurer::disable);
//        http.sessionManagement((session) -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new CORSFilter(), TokenAuthenticationFilter.class);
//        http.authorizeRequests(authorize -> authorize
//                .requestMatchers(publicPathsAnt).permitAll()
//                .requestMatchers(authenticatedPathAnt).authenticated()
//        );
        return http.build();
    }


}
