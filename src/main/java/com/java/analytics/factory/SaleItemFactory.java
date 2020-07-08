package com.java.analytics.factory;

import com.java.analytics.domain.SaleItem;

import java.util.ArrayList;
import java.util.List;


public class SaleItemFactory {


    public static List<SaleItem> create(String saleItem) {
        String[] saleItems = saleItem.replace("[","").replace("]","").split(",");
        List<SaleItem> saleItemList = new ArrayList<>();
        for (String item : saleItems) {
            String[] i = item.split("-");
            saleItemList.add(new SaleItem(i[0],Long.parseLong(i[1]),Double.parseDouble(i[2])));

        }

        return saleItemList;
    }



}
