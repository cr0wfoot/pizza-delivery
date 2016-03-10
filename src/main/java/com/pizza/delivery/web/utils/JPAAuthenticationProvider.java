package com.pizza.delivery.web.utils;

import com.pizza.delivery.domain.entities.User;
import com.pizza.delivery.domain.entities.UserRole;
import com.pizza.delivery.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("JPAAuthProvider")
public class JPAAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userservice;
    
    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
	String pass = auth.getCredentials().toString();
	User user = userservice.findByLogin(login, false);
        if (user != null && login.equals(user.getLogin()) && encoder.matches(pass, user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            Set<UserRole> userAutorities = user.getRoles();
            for (UserRole role : userAutorities) {
                authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            }
            return new UsernamePasswordAuthenticationToken(login, pass, authorities);
        }
        throw new BadCredentialsException("Bad credentials");
    }

    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}