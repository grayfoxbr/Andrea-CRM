package com.angels.app_auth_api_server.service;

import com.angels.app_auth_api_server.model.User;
import com.angels.app_auth_api_server.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OidcUserInfoService {

    private final UserRepository userRepository;

    @Cacheable(value = "oidcUserInfo", key = "#principalName")
    public OidcUserInfo loadUser(String principalName) {
        User user = userRepository.findByEmail(principalName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + principalName));

        return OidcUserInfo.builder()
                .subject(user.getUsername())
                .preferredUsername(user.getUsername())
                .name(user.getFirstName() + " " + user.getLastName())
                .givenName(user.getFirstName())
                .familyName(user.getLastName())
                .email(user.getEmail())
                .emailVerified(user.isEmailVerified())
                .phoneNumber(user.getPhoneNumber())
                .phoneNumberVerified(user.isPhoneVerified())
                .picture(user.getPictureUrl())
                .locale(user.getLocale())
                .zoneinfo(user.getZoneinfo())
                .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null)
                .claim("role", user.getRole())
                .build();
    }
}