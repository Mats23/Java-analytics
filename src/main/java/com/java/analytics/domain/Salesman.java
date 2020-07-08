package com.java.analytics.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Salesman {

    private String id;
    private String cpf;
    private String name;
    private Double salary;

    public Salesman(String name) {
        this.name = name;
    }
}
