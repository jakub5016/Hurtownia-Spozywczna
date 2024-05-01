package com.hurtowania.hurtowniaspozywcza.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {


    private final AppUserRepository repository;

    @GetMapping("/")
    public List<AppUser> getUsers() {
        return repository.findAll();
    }
    
}
