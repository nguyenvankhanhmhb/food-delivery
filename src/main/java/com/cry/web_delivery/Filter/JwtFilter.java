package com.cry.web_delivery.Filter;

import com.cry.web_delivery.util.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerValue = request.getHeader("Authorization");

        if(headerValue != null && headerValue.startsWith("Bearer")){
            String token =  headerValue.substring(7);
            String data = jwtHelper.parseToken(token);

            if(data != null && !data.isEmpty()){
                Type resultType = new TypeToken<List<SimpleGrantedAuthority>>() {}.getType();
                List<GrantedAuthority> roles = gson.fromJson(data, resultType);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","", roles);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(user);

            }
        }else {
            System.out.println("noi dung hheader ko hop le");

        }
        filterChain.doFilter(request,response);
    }
}