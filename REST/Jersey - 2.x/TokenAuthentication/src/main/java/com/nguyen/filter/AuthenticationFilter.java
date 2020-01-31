package com.nguyen.filter;

import com.nguyen.helper.JWTHelper;
import com.nguyen.model.BasicSecurityContext;
import com.nguyen.model.User;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEMA = "Bearer";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        // lấy token
        String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (!isTokenBasedAuthentication(token)) {
            System.out.println("No JWT");
            return; // nếu không có, return để tiếp tục đi tiếp
        }

        // phân tích chuỗi [Bỏ prefix "bearer "]
        token = token.substring(AUTHENTICATION_SCHEMA.length() + 1);

        // lấy user từ JWT (ngoại lệ sẽ tự động được ném ra trong quá trình decode)
        User user = JWTHelper.getUserFromToken(token);

        // chuẩn bị cho authorization
        SecurityContext securityContext = containerRequestContext.getSecurityContext();
        containerRequestContext.setSecurityContext(new BasicSecurityContext(user, securityContext.isSecure()));
    }

    private boolean isTokenBasedAuthentication(String token) {
        if (token == null || !token.toLowerCase().startsWith(AUTHENTICATION_SCHEMA.toLowerCase() + " ")) {
            return false;
        }
        return true;
    }
}
