package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

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
}
