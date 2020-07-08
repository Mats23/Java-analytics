package com.java.analytics.factory;


import com.java.analytics.domain.Salesman;

import java.util.List;

public class SalesmanFactory {


    public static Salesman create(List<String> client) {

        return Salesman.builder()
                .id(client.get(0))
                .cpf(client.get(1))
                .name(client.get(2))
                .salary(Double.parseDouble(client.get(3)))
                .build();
    }
}
