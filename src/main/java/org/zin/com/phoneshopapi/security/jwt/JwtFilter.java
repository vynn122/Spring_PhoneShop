//package org.zin.com.phoneshopapi.security.jwt;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import org.zin.com.phoneshopapi.security.user.CustomUserDetailService;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private jwtUtil jwtUtil;
//
//    @Autowired
//    private CustomUserDetailService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // Skip login and register
//        if (request.getServletPath().startsWith("/api/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//
//        String token = null;
//        String email = null;
//
//        try {
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//
//                token = authHeader.substring(7);
//                email = jwtUtil.extractEmail(token);
//
//            }
//
//            if (email != null &&
//                    SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//                if (jwtUtil.validateToken(token, userDetails)) {
//
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities());
//
//                    authenticationToken.setDetails(
//                            new WebAuthenticationDetailsSource()
//                                    .buildDetails(request));
//
//                    SecurityContextHolder.getContext()
//                            .setAuthentication(authenticationToken);
//                }
//            }
//
//        } catch (Exception e) {
//
//            // Prevent filter from crashing on invalid JWT
//            System.out.println("JWT error: " + e.getMessage());
//
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}