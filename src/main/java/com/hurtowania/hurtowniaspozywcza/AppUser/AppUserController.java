package com.hurtowania.hurtowniaspozywcza.AppUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.IClientService;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class AppUserController {
    @Autowired 
    AppUserService appUserService;

    @Autowired
    IClientService clientService;

    @GetMapping("")
    public ResponseEntity<?> getMethodName(@CurrentSecurityContext SecurityContext context) {
        if (context.getAuthentication().getPrincipal() == "anonymousUser"){
            return new ResponseEntity<>("You are not logged in, can't check yout user info", HttpStatus.UNAUTHORIZED);
        }
        String username =  context.getAuthentication().getName();
        
        long clientId = appUserService.findByUserName(username).getClient().getId();
        
        GetClientDTO client= clientService.getClientById(clientId); 

        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    
}
