package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.IAppUserService;
import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;
import com.hurtowania.hurtowniaspozywcza.Client.ClientServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientTests {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private IAppUserService appUserService;

    @InjectMocks
    private ClientServiceImpl clientService;
    private AppUser appUser;

    @BeforeEach
    public void setup(){
        appUser = AppUser.builder()
                .id(1)
                .type(UserType.CLIENT)
                .build();
    }

    @Test
    void CreateClient_Success(){
        Client client = Client.builder()
                .id(1)
                .name("stefan")
                .address(null)
                .appUser(appUser)
                .build();

        CreateClientRequest request = new CreateClientRequest(null, null, "stefan", null);
        when(appUserService.save(any(AppUser.class))).thenReturn(appUser);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        clientService.createClient(request);
    }


}
