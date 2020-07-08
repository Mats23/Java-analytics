package com.java.analytics.factory;


import com.java.analytics.domain.Client;

import java.util.List;

public class ClientFactory {


    public static Client create(List<String> salesman) {

        return Client.builder()
                .id(salesman.get(0))
                .cnpj(salesman.get(1))
                .name(salesman.get(2))
                .businessArea(salesman.get(3))
                .build();
    }
}
