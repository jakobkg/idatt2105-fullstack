package edu.jakobkg.calculatorbackend.Security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import edu.jakobkg.calculatorbackend.Controller.TokenController;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {
    private final Algorithm algorithm = Algorithm.HMAC512(TokenController.secret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Check if there's an auth header at all, if not there's nothing to do
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Auth header is present, get and verify token
        final String token = authHeader.substring(7);

        if (!tokenIsValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token is valid, get username from it
        final String username = extractUsername(token);

        // Authenticate user
        UsernamePasswordAuthenticationToken auth = 
            new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Finally, let the filter chain continue
        filterChain.doFilter(request, response);
    }

    private boolean tokenIsValid(final String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (final JWTVerificationException ve) {
            return false;
        }
    }

    private String extractUsername(final String token) {
        return verifier.verify(token).getSubject();
    }
}
