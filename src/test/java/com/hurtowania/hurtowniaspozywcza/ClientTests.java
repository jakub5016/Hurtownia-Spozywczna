package com.hurtowania.hurtowniaspozywcza;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientTests {
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper; // Jackson ObjectMapper for JSON serialization

    @Test
    void testAddUser() throws Exception{
        CreateClientRequest request = new CreateClientRequest("jakub", "1234", "JJ", "Wroclaw");
        try {
            // Serialize the request object into JSON
            byte[] requestJson = objectMapper.writeValueAsBytes(request);

            mockMvc.perform(MockMvcRequestBuilders.post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace(); 
        }
    }
}
