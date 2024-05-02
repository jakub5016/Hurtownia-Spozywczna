package com.hurtowania.hurtowniaspozywcza.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUserRepository;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.AuthAppUserResponseDTO;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.LoginAppUserDTO;

@Component
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil utils;

    @Autowired
    AppUserRepository repo;


    public ResponseEntity<?> authUser(LoginAppUserDTO payload){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                payload.getUserName(), payload.getPasswrod()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUserDetails userDetail = (AppUserDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = utils.generateJwtCookie(userDetail);

        String role = userDetail.getAuthorities().iterator().next().toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new AuthAppUserResponseDTO(
                        userDetail.getId(),
                        userDetail.getUsername(),
                        role
                ));
    }

    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = utils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out!");
    }

    
    
}
