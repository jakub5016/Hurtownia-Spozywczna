package com.hurtowania.hurtowniaspozywcza.AppUser.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthAppUserResponseDTO {
    @NonNull
    private long id;
    @NonNull
    private String username;
    @NonNull
    private String role;
}
