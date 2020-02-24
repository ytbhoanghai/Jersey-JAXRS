package com.nguyen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicSecurityContext implements SecurityContext {

    private User user;
    private boolean secure;


    public Principal getUserPrincipal() {
        return () -> user.getUsername();
    }

    public boolean isUserInRole(String s) {
        return user.getRoles().contains(s);
    }

    public boolean isSecure() {
        return secure;
    }

    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
