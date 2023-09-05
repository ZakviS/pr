package org.example.Util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String TOKEN_REQUEST_HEADER = "Authorization";
    private static final String TOKEN_REQUEST_PARAM = "token";

    private final RequestMatcher excludeRequestMatcher;

    public TokenAuthenticationFilter(String defaultFilterProcessesUrl, RequestMatcher excludeRequestMatcher) {
        super(defaultFilterProcessesUrl);
        this.excludeRequestMatcher = excludeRequestMatcher;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(TOKEN_REQUEST_HEADER));
        if (tokenOptional.isEmpty()) {
            Optional<String[]> tokenParamsOptional = Optional.ofNullable(request.getParameterMap().get(TOKEN_REQUEST_PARAM));
            if (tokenParamsOptional.isPresent()) {
                tokenOptional = tokenParamsOptional.map(values -> values[0]);
            }
            if (tokenOptional.isEmpty()) {
                throw new BadCredentialsException("Token not found in headers or params");
            }
        }
        TokenAuthentication authentication = new TokenAuthentication(tokenOptional.get());
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().print("Authentication Failed: " + failed.getMessage());
        response.flushBuffer();
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (excludeRequestMatcher != null && excludeRequestMatcher.matches(request)) {
            return false;
        }
        return super.requiresAuthentication(request, response);
    }
}
