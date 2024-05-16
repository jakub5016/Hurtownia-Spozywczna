package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.IAppUserService;
import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    private final IAppUserService appUserService;


    @Override
    public void createClient(CreateClientRequest request) {
        AppUser appUser = AppUser.builder()
                .type(UserType.CLIENT)
                .userName(request.login())
                .password(request.password())
                .build();

        appUser = appUserService.save(appUser);

        Client client = Client.builder()
                .name(request.name())
                .address(request.address())
                .appUser(appUser)
                .build();

        clientRepository.save(client);
    }

    @Override
    public void deleteClient(long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null){
            clientRepository.delete(client);
            appUserService.delete(client.getAppUser());

        }

    }

    @Override
    public GetClientDTO getClientById(long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client clientGot = optionalClient.orElse(null);
        if (clientGot == null){
            return null;
        }
        GetClientDTO dto = GetClientDTO.builder()
                                        .id(clientGot.getId())
                                        .name(clientGot.getName())
                                        .address(clientGot.getAddress())
                                        .build();
                                        
        return dto;
    }
}
