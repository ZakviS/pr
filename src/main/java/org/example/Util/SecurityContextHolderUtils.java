package org.example.Util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityContextHolderUtils {

    public static Optional<TokenAuthentication> getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication instanceof TokenAuthentication) && authentication.isAuthenticated() ?
            Optional.of((TokenAuthentication) authentication) : Optional.empty();
    }

    public static void setAuthentication(TokenAuthentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static String getCurrentUserLogin() {
        return getAuthentication()
            .map(TokenAuthentication::getLogin)
            .orElse(null);
    }

    public static Long getCurrentUserPersonId() {
        return getAuthentication()
            .map(TokenAuthentication::getPersonId)
            .orElse(null);
    }

    public static Set<String> getCurrentUserAuthorities() {
        return getAuthentication()
            .map(TokenAuthentication::getAuthorities)
            .orElse(Collections.emptySet())
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());
    }
}
