package com.hurtowania.hurtowniaspozywcza.AppUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.UserDetailsDTO;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.IClientService;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AppUserController {
    
    @Autowired 
    AppUserService appUserService;

    @Autowired
    IClientService clientService;

    @GetMapping("")
    public ResponseEntity<?> getUserDetails(@CurrentSecurityContext SecurityContext context) {
        if ("anonymousUser".equals(context.getAuthentication().getPrincipal())) {
            return new ResponseEntity<>("You are not logged in, can't check your user info", HttpStatus.UNAUTHORIZED);
        }

        String username = context.getAuthentication().getName();
        long clientId = appUserService.findByUserName(username).getClient().getId();
        GetClientDTO client = clientService.getClientById(clientId);

        // Extracting authorities
        Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
        
        // Convert authorities to a list of strings
        List<String> roles = authorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList());

        // Building the UserDetailsDTO
        UserDetailsDTO detailsDTO = UserDetailsDTO.builder()
                                                  .clientInfo(client)
                                                  .roles(roles)
                                                  .build();

        return new ResponseEntity<>(detailsDTO, HttpStatus.OK);
    }
}
