package com.java.analytics.dto;

import com.java.analytics.domain.Client;
import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ProcessDTO {

    private String done;
    private List<Salesman> salesmanList;
    private List<Client> clientList;
    private List<Sale> saleList;
    private Map<String, String> bestSaleAndWorseSaleman;
}
