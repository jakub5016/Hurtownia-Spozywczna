package com.hurtowania.hurtowniaspozywcza.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.CreateAppUserDTO;
import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.LoginAppUserDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginAppUserDTO payload) {
        return authService.authUser(payload);

    }
    
    @GetMapping("/logout")
    public ResponseEntity<?> getMethodName() {
        return authService.logoutUser();
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerUser(CreateAppUserDTO dto) {
        return authService.registerUser(dto);
    }
   
    @GetMapping("/protected/user")
    public String testUser(@CurrentSecurityContext SecurityContext context){
        return "User " + context.getAuthentication().getName() + " " + context.getAuthentication().getAuthorities();
    }
    
}
