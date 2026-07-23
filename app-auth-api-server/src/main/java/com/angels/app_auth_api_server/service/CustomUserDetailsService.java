package com.angels.app_auth_api_server.service;

import com.angels.app_auth_api_server.repo.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "users", key = "#username")
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .or(() -> userRepository.findByUsername(username))
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole())
                        .accountLocked(user.isAccountLocked())
                        .disabled(!user.isEnabled())
                        .build()
                )
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuário não encontrado: " + username));
    }
}