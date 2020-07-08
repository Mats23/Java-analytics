package com.java.analytics.service;


import com.java.analytics.domain.Salesman;
import com.java.analytics.enums.Type;
import com.java.analytics.factory.SalesmanFactory;
import com.java.analytics.utils.Partition;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class SalesmanService implements Process<Salesman, String> {


    @Override
    public List<Salesman> create(List<String> stringList) {
        List<List<String>> list = Partition.execute(stringList);
        List<Salesman> salesmanList = new ArrayList<>();
        list.forEach(a -> {
            if (a.contains(Type.SALEMAN.getValue())) {
                Salesman salesman = SalesmanFactory.create(a);
                salesmanList.add(salesman);
            }
        });
        return salesmanList;
    }





}
