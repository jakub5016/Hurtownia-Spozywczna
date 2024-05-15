package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;

public interface IClientService {
    void createClient(CreateClientRequest request);
    void deleteClient(long clientId);
    Client getClientById(long id);
}
