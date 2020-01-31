package com.nguyen.helper;

import com.nguyen.model.Role;
import com.nguyen.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class JWTHelper {

    private static final long EXPIRATION_LIMIT_IN_MINUTES = 30;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String SECRET_KEY = "ytbhoanghai-gtjqnmivyaryv-nqefdqyvslcndrh-nucgloorupfberzfiwgh";
    private static final String ISSUER = "ytbhoanghai";

    public static String createJWT(User user) {
        // Thời điểm bắt đầu
        Date now = new Date(System.currentTimeMillis());
        // Thời điểm hết hạn
        Date expiredDate = new Date(
                now.getTime() + TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT_IN_MINUTES));

        // Key Secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

        // Thông tin tự định nghĩa
        Claims claims = Jwts.claims();
        claims.put("username", user.getUsername());
        claims.put("roles", user.getRoles());

        // Tiến hành Build
        JwtBuilder jwtBuilder = Jwts.builder().
                addClaims(claims).
                setId(UUID.randomUUID().toString()).
                setIssuedAt(now).
                setIssuer(ISSUER).
                setExpiration(expiredDate).
                signWith(signingKey, SIGNATURE_ALGORITHM);

        return jwtBuilder.compact();
    }

    public static User getUserFromToken(String token) {
        Claims claims = decodeJWT(token);

        User user = new User();
        user.setUsername((String) claims.get("username"));
        user.setRoles((List<String>) claims.get("roles"));

        return user;
    }

    private static Claims decodeJWT(String jwt) {
        return Jwts.parser().
                setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).
                parseClaimsJws(jwt).
                getBody();
    }
}
