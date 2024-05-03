package com.hurtowania.hurtowniaspozywcza.AppUser;

import org.springframework.web.bind.annotation.RestController;

import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.CreateAppUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class AppUserAPI {

    @Autowired
    AppUserService service;

    @GetMapping("/")
    public List<AppUser> getUsers() {
        return service.getAllUsers();
    }    
    
}
