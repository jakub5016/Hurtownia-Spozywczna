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

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUserRepository;
import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.AuthAppUserResponseDTO;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.CreateAppUserDTO;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.LoginAppUserDTO;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;

@Component
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil utils;

    @Autowired
    AppUserRepository repo;

    @Autowired
    ClientRepository clientRepository;

    public ResponseEntity<?> registerUser(CreateAppUserDTO payload) {

        if(repo.existsByUserName(payload.getUserName())) {
            return ResponseEntity.badRequest().body("Username is already in use!");
        }

        AppUser user = new AppUser();
        user.setUserName(payload.getUserName());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setType(UserType.CLIENT);
        
        Client client = new Client();
        client.setAddress(payload.getAddress());
        client.setName(payload.getFirstName() + payload.getSecondName());
        
        user.setClient(client);

        repo.save(user);
        clientRepository.save(client);

        return ResponseEntity.ok().body("User registered!");
    }

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
