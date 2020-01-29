package com.nguyen.context;

import com.nguyen.model.User;
import lombok.AllArgsConstructor;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@AllArgsConstructor
public class BasicSecurity implements SecurityContext {

    private User user;
    private boolean secure;

    public Principal getUserPrincipal() {
        return () -> { return user.getUsername(); };
    }

    public boolean isUserInRole(String s) {
        return user.getRole().contains(s);
    }

    public boolean isSecure() {
        return secure;
    }

    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
