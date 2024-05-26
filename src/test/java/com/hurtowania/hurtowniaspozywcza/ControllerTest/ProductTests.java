package com.hurtowania.hurtowniaspozywcza.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurtowania.hurtowniaspozywcza.Product.IProductService;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class ProductTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;


    @Test
    public void AddProduct_Success() throws Exception {
        CreateProductRequest request = new CreateProductRequest(
                2d,
                "Milk",
                "Diary",
                50
        );
        doNothing().when(productService).addProduct(request);

        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(asJsonString(request))).andExpect(status().is(201));
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
