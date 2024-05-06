package com.hurtowania.hurtowniaspozywcza.AppUser;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RestController
@RequestMapping("/user")
public class AppUserAPI {

    @Autowired
    AppUserRepository repository;

    @GetMapping("/")
    public List<AppUser> getUsers() {
        return repository.findAll();
    }
    
}
