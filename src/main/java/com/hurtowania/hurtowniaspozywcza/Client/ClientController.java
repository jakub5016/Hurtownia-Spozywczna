package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Order.IOrderService;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
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
    public ResponseEntity<Client> getClientById(@PathVariable long id) {
        Client client = clientService.getClientById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{id}/order")
    public ResponseEntity<List<Order>> getOrdersByClientId(@PathVariable long id) {
        List<Order> orders = orderService.getOrdersByClientId(id);

        if (orders == null || orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
