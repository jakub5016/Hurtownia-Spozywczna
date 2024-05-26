package com.hurtowania.hurtowniaspozywcza.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurtowania.hurtowniaspozywcza.Client.IClientService;
import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class ClientTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService clientService;

    @Test
    public void createClient_Success() throws Exception {
        CreateClientRequest request = Mockito.mock(CreateClientRequest.class);

        doNothing().when(clientService).createClient(Mockito.any(CreateClientRequest.class));
        mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))).andExpect(status().is(201));
    }

    @Test
    public void deleteOrder_Success() throws Exception {

        doNothing().when(clientService).deleteClient(1);
        mockMvc.perform(delete("/client/1")).andExpect(status().is(204));
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
