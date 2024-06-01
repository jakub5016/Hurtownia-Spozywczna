package com.hurtowania.hurtowniaspozywcza.Client;

import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.AppUser.IAppUserService;
import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;
import com.hurtowania.hurtowniaspozywcza.Client.requests.CreateClientRequest;
import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;
import com.hurtowania.hurtowniaspozywcza.Order.IOrderService;
import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Product.Product;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    private final IAppUserService appUserService;
    private final IOrderService orderService;

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
        if (client != null) {
            // Delete orders associated with the client
            List<Order> orders = client.getOrders();
            for (Order order : orders) {
                orderService.deleteOrder(order.getId());
            }
            
            // Delete the associated AppUser
            appUserService.delete(client.getAppUser());
    
            // Finally, delete the client
            clientRepository.delete(client);
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

    @Override
    public Page<Client> getClient(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Client> page = clientRepository.findAll(pageable);
        return page;
    }

}
