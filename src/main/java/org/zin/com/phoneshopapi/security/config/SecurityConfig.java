//package org.zin.com.phoneshopapi.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebMvc
//public class SecurityConfig {
//
//    /**
//     * Configure the security filter chain for the application. This method disables
//     * CSRF protection and allows unauthenticated access to the /api/auth/**
//     * endpoints, while requiring authentication for all other endpoints.
//     *
//     * @param http the HttpSecurity object to configure
//     * @return a SecurityFilterChain object that defines the security configuration
//     *         for the application
//     * @throws Exception if an error occurs while configuring the security filter
//     *                   chain
//     */
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http
////                .csrf(csrf -> csrf.disable())
////
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/auth/**").permitAll()
////                        .anyRequest().authenticated());
////
////
////
////
////        return http.build();
////    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/auth/**").permitAll()
////                        .anyRequest().authenticated())
////                .exceptionHandling(ex -> ex
////                        .authenticationEntryPoint(((request, response, authException) -> {
////                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
////                        }))
//                .authorizeHttpRequests(auth-> auth.anyRequest().permitAll());
//
//
//
//        ;
//
//
//
//        return http.build();
//
//    }
//
//    /**
//     * Configure the authentication manager for the application. This method
//     * retrieves
//     * the authentication manager from the provided AuthenticationConfiguration
//     * object.
//     *
//     * @param config the AuthenticationConfiguration object to retrieve the
//     *               authentication manager from
//     * @return an AuthenticationManager object that can be used to authenticate
//     *         users in the application
//     * @throws Exception if an error occurs while retrieving the authentication
//     *                   manager
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration config)
//            throws Exception {
//
//        return config.getAuthenticationManager();
//    }
//
//    /**
//     * Configure the password encoder for the application. This method returns a
//     * BCryptPasswordEncoder object that can be used to encode passwords in the
//     * application.
//     *
//     * @return a PasswordEncoder object that can be used to encode passwords in the
//     *         application
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
//
//}
