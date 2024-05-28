package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Order.IOrderService;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ClientController {
    private final IClientService clientService;

    @Autowired
    private IOrderService orderService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addClient(@RequestBody CreateClientRequest request) {
        clientService.createClient(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetClientDTO> getClientById(@PathVariable long id) {
        GetClientDTO client = clientService.getClientById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{id}/order")
    public ResponseEntity<List<Order>> getOrdersByClientId(@PathVariable long id) {
        List<Order> orders = orderService.getOrdersByClientId(id);

        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getClient(@RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "10", required = false) int pageSize){
        return ResponseEntity.ok(clientService.getClient(pageNo, pageSize));
    }
}
