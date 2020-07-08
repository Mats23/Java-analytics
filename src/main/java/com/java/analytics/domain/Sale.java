package com.java.analytics.domain;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Sale {

    private String id;
    private String idSale;
    private List<SaleItem> saleItem;
    private Salesman salesman;





}
