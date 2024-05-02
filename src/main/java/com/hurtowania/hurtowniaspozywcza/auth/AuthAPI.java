package com.hurtowania.hurtowniaspozywcza.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.LoginAppUserDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthAPI {
    @Autowired
    AuthController authController;

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginAppUserDTO payload) {
        return authController.authUser(payload);
    }
    
    @GetMapping("/logout")
    public ResponseEntity<?> getMethodName() {
        return authController.logoutUser();
    }
    
}
