package com.wellington.dealership.infra.security;

import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.repositories.DealershipRepository;
import com.wellington.dealership.domains.Dealership;
import com.wellington.dealership.infra.security.TokenService;
import com.wellington.dealership.repositories.DealershipRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    DealershipRepository dealershipRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String login = tokenService.validateToken(token);

            if (login != null) {
                Dealership dealership = dealershipRepository.findByEmail(login).orElse(null);
                if (dealership != null) {
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

                    var userDetails = new org.springframework.security.core.userdetails.User(
                            dealership.getEmail(),
                            dealership.getPassword(),
                            authorities
                    );

                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7);
    }
}
