package com.hurtowania.hurtowniaspozywcza.ControllerTests.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.IAppUserService;
import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;
import com.hurtowania.hurtowniaspozywcza.Client.ClientServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientTests {

    @Autowired
    private MockMvc mvc;




}
