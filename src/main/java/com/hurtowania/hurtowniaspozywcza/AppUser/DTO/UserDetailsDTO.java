package com.hurtowania.hurtowniaspozywcza.AppUser.DTO;

import java.util.List;

import org.hibernate.usertype.UserType;

import com.hurtowania.hurtowniaspozywcza.Client.requests.GetClientDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserDetailsDTO {
    private GetClientDTO clientInfo;
    private List<String> roles;
    private String username;
}
