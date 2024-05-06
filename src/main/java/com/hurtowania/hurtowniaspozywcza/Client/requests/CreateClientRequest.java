package com.hurtowania.hurtowniaspozywcza.Client.requests;

public record CreateClientRequest(String login, String password, String name, String address) {
}
