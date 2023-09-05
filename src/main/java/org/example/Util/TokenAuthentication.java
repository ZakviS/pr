package org.example.Util;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.Token;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Getter
@Setter
public class TokenAuthentication extends AbstractAuthenticationToken implements Principal {

    private final String token;
    private String login;
    private String principal;
    private Token credentials;
    private Long personId;

    public TokenAuthentication(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    public TokenAuthentication(Collection<String> authorities, String token, String login, Long personId) {
        super(authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        this.token = token;
        this.login = login;
        this.principal = login;
        this.credentials = new DefaultToken(token, new Date().getTime(), login);
        this.setAuthenticated(true);
        this.setPersonId(personId);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
