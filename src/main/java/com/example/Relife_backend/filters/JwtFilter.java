package com.example.Relife_backend.filters;

import com.example.Relife_backend.entities.UserEntity;
import com.example.Relife_backend.services.JwtService;
import com.example.Relife_backend.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");//get JWt token from header
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {//If no token or wrong format → skip authentication, continue to next filter
                filterChain.doFilter(request, response);
                return;
            }

            String jwtToken = requestTokenHeader.split("Bearer ")[1].trim();//Removes "Bearer " prefix to get the actual token
            Long userId = jwtService.getUserIdFromToken(jwtToken);//Decodes and validates JWT, extracts the user ID from claims

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
            filterChain.doFilter(request, response);//Pass request to the next filter/controller
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }


    }
}
