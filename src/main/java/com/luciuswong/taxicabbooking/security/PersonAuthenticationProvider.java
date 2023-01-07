package com.luciuswong.taxicabbooking.security;

import com.luciuswong.taxicabbooking.model.Role;
import com.luciuswong.taxicabbooking.model.Person;
import com.luciuswong.taxicabbooking.repository.PersonRepository;
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

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PersonRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person user = userRepository.readByUsername(username);
        if (user != null && user.getUserId() > 0 && passwordEncoder.matches(password, user.getPassword())) {
            // null is passed to authentication token as user password is not used in the application
            return new UsernamePasswordAuthenticationToken(username, null, getGrantedAuthorities(user.getRole()));
        } else {
            throw new BadCredentialsException("Credentials are invalid");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
