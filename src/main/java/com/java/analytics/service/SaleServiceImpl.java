package com.java.analytics.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;
import com.java.analytics.enums.Type;
import com.java.analytics.factory.SaleFactory;
import com.java.analytics.utils.Partition;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleServiceImpl implements SaleService<Sale, String,Salesman> {



    public List<Sale> create(List<String> stringList, List<Salesman> salesmanList) {

        List<List<String>> list = Partition.execute(stringList);
        List<Sale> saleList = new ArrayList<>();
        list.forEach(a -> {
            if (a.contains(Type.SALE.getValue())) {
                Sale sale = SaleFactory.create(a, salesmanList);
                saleList.add(sale);
            }
        });
        return saleList;

    }

    public Map<String, String> calculateSale(List<Sale> sales) {
        final Multimap<String,Double> valores = ArrayListMultimap.create();
        sales.forEach( a -> {

            a.getSaleItem().forEach(b -> {

                BigDecimal bd = BigDecimal.valueOf(b.getPrice());
                BigDecimal result = bd.multiply(new BigDecimal(b.getQuantityItem()));
                valores.put(a.getIdSale(),result.doubleValue());


            });
        });
        String maxResult = maxValue(valores);
        String minResult = minValue(valores);
        Map<String, String> valoresMap = new HashMap<>();
        valoresMap.put("High",maxResult);
        valoresMap.put("less",minResult);

        return valoresMap;

    }


    private String maxValue(Multimap<String,Double> valores) {
        return Collections.max(valores.entries(), Map.Entry.comparingByValue()).getKey();

    }

    private String minValue(Multimap<String,Double> valores) {
        return Collections.min(valores.entries(), Map.Entry.comparingByValue()).getKey();

    }



}
