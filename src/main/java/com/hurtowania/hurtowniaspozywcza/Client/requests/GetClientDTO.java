package com.hurtowania.hurtowniaspozywcza.Client.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientDTO {
    private long id;
    private String name;
    private String address;

}
