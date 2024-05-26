package com.hurtowania.hurtowniaspozywcza.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurtowania.hurtowniaspozywcza.Order.IOrderService;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderPositionRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class OrderTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  IOrderService orderService;

    @Test
    public void addOrder_Success() throws Exception {
        CreateOrderRequest request = Mockito.mock(CreateOrderRequest.class);

        doNothing().when(orderService).addOrder(Mockito.any(CreateOrderRequest.class));
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))).andExpect(status().is(201));
    }

    @Test
    public void addOrder_NotFound() throws Exception {
        CreateOrderRequest request = Mockito.mock(CreateOrderRequest.class);

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(orderService).addOrder(Mockito.any(CreateOrderRequest.class));
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))).andExpect(status().is(404));
    }

    @Test
    public void addOrder_Conflict() throws Exception {
        CreateOrderRequest request = Mockito.mock(CreateOrderRequest.class);

        doThrow(new ResponseStatusException(HttpStatus.CONFLICT)).when(orderService).addOrder(Mockito.any(CreateOrderRequest.class));
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))).andExpect(status().is(409));
    }

    @Test
    public void deleteOrder_Success() throws Exception {

        doNothing().when(orderService).deleteOrder(1);
        mockMvc.perform(delete("/order/1")).andExpect(status().is(204));
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
