package com.hurtowania.hurtowniaspozywcza.AppUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import com.hurtowania.hurtowniaspozywcza.AppUser.DTO.CreateAppUserDTO;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository userRepo;

    @Autowired 
    ClientRepository clientRepo;

    public String createAppUser(CreateAppUserDTO dto){
        Client client = Client.builder().name(dto.getFirstName() + dto.getSecondName()).address(dto.getAddress()).build();
        
        AppUser appUser = AppUser.builder().userName(dto.getUserName()).password(dto.getPassword()).type(UserType.ADMIN).build();
        
        client.setAppUser(appUser);

        userRepo.save(appUser);
        clientRepo.save(client);
        return "New user succesfully created";
    }

    public AppUser findByUserName(String username){
        if (userRepo.findByUserName(username).size() > 0){
            return (userRepo.findByUserName(username)).get(0);

        }
        else{
            return null;
        }
    }


    public List<AppUser> getAllUsers(){
        return userRepo.findAll();
    }
}
