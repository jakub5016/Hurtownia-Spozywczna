package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUserRepository;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUserServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Price.PriceRepository;
import com.hurtowania.hurtowniaspozywcza.Price.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserTests {
    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserServiceImpl appUserService;
    private AppUser appUser;

    @BeforeEach
    public void setup(){
        appUser = AppUser.builder()
                .id(1)
                .build();
    }

    @Test
    public void SavePrice_Success(){
        when(appUserRepository.save(appUser)).thenReturn(appUser);
        AppUser tmp = appUserRepository.save(appUser);

        assertEquals(appUser,tmp);
    }

}
