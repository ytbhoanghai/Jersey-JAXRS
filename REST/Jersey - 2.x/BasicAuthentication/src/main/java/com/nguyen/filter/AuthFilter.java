package com.nguyen.filter;

import com.nguyen.context.BasicSecurity;
import com.nguyen.exception.AuthorizationException;
import com.nguyen.model.Role;
import com.nguyen.model.User;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final HashMap<String, User> USERS;
    static {
        USERS = new HashMap<>();
        USERS.put("admin", new User("admin", "123", Arrays.asList(Role.ROLE_ADMIN)));
        USERS.put("customer", new User("customer", "123", Arrays.asList(Role.ROLE_CUSTOMER)));
    }

    private static User getUser(String username, String password, HashMap<String, User> USERS) {
        if (USERS.containsKey(username)) {
            User _user = USERS.get(username);
            if (password.equals(_user.getPassword())) {
                return _user;
            }
        }
        return null;
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        // Lấy thông tin xác thực
        String authCredentials = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authCredentials == null || authCredentials.isEmpty()) {
            return;
        }

        // Tiến hành decode và phân tích
        String encodeUsernameAndPassword = authCredentials.replace("Basic ", "");
        byte[] decodeBytes = DatatypeConverter.parseBase64Binary(encodeUsernameAndPassword);
        String userNameAndPassword = new String(decodeBytes, StandardCharsets.UTF_8.name());

        StringTokenizer tokenizer = new StringTokenizer(userNameAndPassword, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        // So khớp thông tin
        User user = getUser(username, password, USERS);
        if (user == null) {
            throw new AuthorizationException("not found username and password");
        }

        // Tạo ra đối tượng thực thi giao diện SecurityContext và đưa và cho ContainerRequestContext quản lý phân quyền
        boolean secure = context.getSecurityContext().isSecure();
        context.setSecurityContext(new BasicSecurity(user, secure));
    }
}
