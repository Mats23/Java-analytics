package com.java.analytics.factory;

import com.java.analytics.domain.SaleItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SaleItemFactory {


    public static List<SaleItem> create(String saleItem) {
        List<SaleItem> saleItemList = new ArrayList<>();
        Arrays.stream(saleItem.replace("[", "").replace("]", "").split(","))
                .map(a -> a.split("-")).collect(Collectors.toList())
                .forEach( b ->  saleItemList.add(new SaleItem(b[0], Long.parseLong(b[1]), Double.parseDouble(b[2]))));


        return saleItemList;
    }


}
