package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

public interface IClientService {
    void createClient(CreateClientRequest request);
    void deleteClient(long clientId);
    GetClientDTO getClientById(long id);
}
