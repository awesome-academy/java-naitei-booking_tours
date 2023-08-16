package com.naite.bookingTour.config;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naite.bookingTour.model.User;
import com.naite.bookingTour.model.User.Role;
import com.naite.bookingTour.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }

//       Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if (userOptional.isPresent()) {
//        	User user = userOptional.get();
//
//            return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                    user.getPassword(),
//                    mapRolesToAuthorities(Collections.singleton(user.getRole())));
//        }else{
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}