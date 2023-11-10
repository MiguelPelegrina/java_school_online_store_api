package com.javaSchool.FinalTask3.security;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import io.jsonwebtoken.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * The {@code JwtUtil} class is a utility component responsible for managing JSON Web Tokens (JWT) in a Spring
 * application. JWTs are used for user authentication and authorization within the application.
 */
@Component
public class JwtUtil {
    // Class methods
    /**
     * Decodes a JSON Web Token (JWT) to extract its claims.
     * @param token The JWT to decode.
     * @return A Claims object containing the decoded claims from the JWT. Returns null if the token is invalid or an
     *          exception occurs during decoding.
     */
    public static Claims decodeToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret_key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // TODO Handle exceptions (e.g., token expired, invalid signature, etc.)
            return null;
        }
    }

    public static int getIdFromToken(RequestAttributes requestAttributes){
        // Get the token
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // Parse the token to get the id
        Claims claims = JwtUtil.decodeToken(request.getHeader("Authorization").substring(7));

        return claims.get("id", Integer.class);
    }

    // Fields
    private final static String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;
    private final JwtParser jwtParser;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    /**
     * Default constructor.
     */
    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    // Instance methods
    /**
     * Creates a JWT token for a user.
     * @param user The user entity for which the token is created.
     * @return JWT token as a string.
     */
    public String createToken(UserEntity user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("roles", user.getRoles().stream().map(
                userRoleEntity -> userRoleEntity.getRole().getName())
                .collect(Collectors.toList())
        );
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }



    /**
     * Parses the claims from a JWT token.
     * @param token The JWT token as a string.
     * @return Claims extracted from the token.
     */
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Resolves and extracts JWT claims from an HTTP request.
     * @param req The HTTP request from which the JWT is extracted.
     * @return JWT claims as extracted from the token.
     * @throws AuthenticationException If an exception occurs while resolving and parsing the token.
     */
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    /**
     * Resolves and extracts a JWT token from an HTTP request.
     * @param request The HTTP request.
     * @return The JWT token as a string or null if not found.
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    /**
     * Validates the expiration time of JWT claims.
     * @param claims The JWT claims to be validated.
     * @return True if the JWT is not expired, false otherwise.
     * @throws AuthenticationException - If an exception occurs during validation.
     */
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        return claims.getExpiration().after(new Date());
    }

    /**
     * Retrieves the email from JWT claims.
     * @param claims The JWT claims from which to extract the email.
     * @return The email address stored in the JWT claims.
     */
    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    /**
     * Retrieves the roles from JWT claims.
     * @param claims The JWT claims from which to extract the roles.
     * @return List of role names stored in the JWT claims.
     */
    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }
}
