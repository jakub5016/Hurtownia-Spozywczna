package com.hurtowania.hurtowniaspozywcza.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUserRepository;

import jakarta.transaction.Transactional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    AppUserRepository repo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        AppUser user = repo.findFirstByUserName(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + userName));
        
        
        return AppUserDetails.builder(user);
    }
}
