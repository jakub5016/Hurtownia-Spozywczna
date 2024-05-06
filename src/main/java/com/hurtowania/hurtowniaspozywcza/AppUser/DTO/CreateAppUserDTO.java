package com.hurtowania.hurtowniaspozywcza.AppUser.DTO;

import com.hurtowania.hurtowniaspozywcza.AppUser.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAppUserDTO {
    private String userName;
    private String firstName;
    private String secondName;
    private String password;
    private UserType type;
    private String address;

}
