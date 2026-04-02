//package org.zin.com.phoneshopapi.security.jwt;
//
//import java.util.Date;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class jwtUtil {
//
//    private String secret = "my-super-secret-key-for-jwt-authentication-2026-very-secure-key";
//
//    /**
//     * * Generates a JWT token for the authenticated user. * * The email is stored
//     * as the "subject" inside the token payload. * * JWT Payload Example: * { *
//     * "sub": "user@email.com", * "iat": 1710000000, * "exp": 1710086400 * } *
//     *
//     * @param email user's email (used as unique identifier) *
//     *
//     * @return signed JWT token
//     */
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .compact();
//    }
//
//    /**
//     * * Extracts the user's email from a JWT token.
//     * The email is stored in the
//     * "sub" (subject) claim.
//     * Steps:
//     * 1. Parse the token
//     * 2. Verify signature
//     * using the secret key
//     * 3. Extract claims
//     * 4. Return subject (email)
//     *
//     * @param token JWT token sent from the client
//     *
//     * @return email stored inside
//     *         the token
//     */
//
//    public String extractEmail(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(secret.getBytes())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//
//    }
//
//    /**
//     * * Validates a JWT token against the user details loaded from the database. *
//     * * Validation checks: * - The email in the token matches the authenticated
//     * user's email
//     *
//     * @param token       JWT token
//     * @param userDetails authenticated user details
//     * @return true if token belongs to the user
//     */
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String email = extractEmail(token);
//        return email.equals(userDetails.getUsername());
//    }
//}
