package com.cry.web_delivery.Provider;

import com.cry.web_delivery.Entity.Users;
import com.cry.web_delivery.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users users = userRepository.findByUserName(name);
        if(users != null){
            if(passwordEncoder.matches(password, users.getPassword())){

                List<GrantedAuthority> roles = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(users.getRoles().getRoleName());
                roles.add(grantedAuthority);

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name,password,roles);

                SecurityContextHolder.getContext().setAuthentication(token);

                return  token;

            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
