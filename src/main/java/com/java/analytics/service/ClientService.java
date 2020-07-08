package com.java.analytics.service;

import com.java.analytics.domain.Client;
import com.java.analytics.enums.TypeEnum;
import com.java.analytics.factory.ClientFactory;
import com.java.analytics.utils.Partition;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements Process<Client, String> {


    public List<Client> create(List<String> stringList) {

        List<List<String>> list = Partition.execute(stringList);
        List<Client> clientList = new ArrayList<>();
        list.forEach(a -> {
            if (a.contains(TypeEnum.CLIENT.getValue())) {
                Client client = ClientFactory.create(a);
                clientList.add(client);
            }
        });
        return clientList;


    }
}
