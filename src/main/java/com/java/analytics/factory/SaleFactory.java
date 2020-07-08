package com.java.analytics.factory;


import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;


import java.util.List;
import java.util.Optional;

public class SaleFactory {


    public static Sale create(List<String> saleItem, List<Salesman> salesmanList) {
        Optional<Salesman> salesman =salesmanList.stream().filter(sm -> sm.getName().equals(saleItem.get(3))).findFirst();

        if (salesman.isPresent()) {
            return Sale.builder()
                    .id(saleItem.get(0))
                    .idSale(saleItem.get(1))
                    .saleItem(SaleItemFactory.create(saleItem.get(2)))
                    .salesman(salesman.get())
                    .build();
        } else {
            return  Sale.builder()
                    .id(saleItem.get(0))
                    .idSale(saleItem.get(1))
                    .saleItem(SaleItemFactory.create(saleItem.get(2)))
                    .build();
        }

    }
}
