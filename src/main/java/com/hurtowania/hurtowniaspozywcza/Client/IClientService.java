package com.hurtowania.hurtowniaspozywcza.Client;

import org.springframework.data.domain.Page;

import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

public interface IClientService {
    void createClient(CreateClientRequest request);
    void deleteClient(long clientId);
    GetClientDTO getClientById(long id);
    Page<Client> getClient(int pageNo, int pageSize);
}
