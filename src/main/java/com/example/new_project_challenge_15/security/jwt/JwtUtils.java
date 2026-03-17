package com.example.new_project_challenge_15.security.jwt;

import com.example.new_project_challenge_15.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.public.key}")
    private String publicKeyPEM;

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    private PublicKey getPublicKey() {
        return PublicKeyLoader.loadPublicKey(publicKeyPEM);
    }

    public String getUserNameFromJwtToken(String token) {
        String username;

        try {
            username = Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            logger.debug("Successfully parsed token as SSO token");
            return username;

        } catch (Exception ssoException) {
            logger.error("Failed to parse token as both regular JWT and SSO token. Regular JWT error: {}, SSO error: {}",
                    ssoException.getMessage(), ssoException.getMessage());
            return "";
        }
    }


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    // for SSO
    public boolean validateSSOToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getTokenFromCookie(HttpServletRequest request, String cookieName) {
        String cookieHeader = request.getHeader("Cookie");
        if (cookieHeader != null) {
            for (String cookie : cookieHeader.split(";")) {
                String[] parts = cookie.trim().split("=", 2);
                if (parts.length == 2 && parts[0].equals(cookieName)) {
                    return parts[1];
                }
            }
        }
        return null;
    }
}
