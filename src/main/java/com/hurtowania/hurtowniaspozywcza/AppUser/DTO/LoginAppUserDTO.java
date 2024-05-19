package com.hurtowania.hurtowniaspozywcza.AppUser.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginAppUserDTO {
    private String userName;
    private String password;
}
