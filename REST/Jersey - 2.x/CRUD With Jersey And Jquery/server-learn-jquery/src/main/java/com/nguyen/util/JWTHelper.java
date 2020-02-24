package com.nguyen.util;


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
import java.util.stream.Collectors;

public class JWTHelper {

    private static final long EXPIRATION_LIMIT_IN_MINUTES = 30;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String SECRET_KEY = "ytbhoanghai-gtjqnmivyaryv-nqefdqyvslcndrh-nucgloorupfberzfiwgh";
    private static final String ISSUER = "ytbhoanghai";

    public static String createJWT(User user) {
        Date now = new Date(System.currentTimeMillis());
        Date expiredDate = new Date(
                now.getTime() + TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT_IN_MINUTES));

        // Key Secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

        Claims claims = Jwts.claims();
        claims.put("username", user.getUsername());
        claims.put("roles", user.getRoles());

        JwtBuilder jwtBuilder = Jwts.builder().
                addClaims(claims).
                setId(UUID.randomUUID().toString()).
                setIssuedAt(now).
                setIssuer(ISSUER).
                setExpiration(expiredDate).
                signWith(signingKey, SIGNATURE_ALGORITHM);

        return jwtBuilder.compact();
    }


    public static User getUserFormToken(String jwt) {
        Claims claims = decodeJWT(jwt);

        User user = new User();
        user.setUsername(claims.get("username", String.class));
        List<String> roles = ((List<?>) claims.get("roles")).stream().map(o -> (String) o).collect(Collectors.toList());
        user.setRoles(roles);

        return user;
    }

    public static Claims decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt)
                .getBody();
    }
}
