package com.java.analytics.service;

import com.java.analytics.domain.Client;
import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;
import com.java.analytics.dto.ProcessDTO;
import com.java.analytics.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class AnalyticService {

    @Autowired
    FileService fileService;

    public void run() {
        Arrays.asList(this.fileService.stackFiles(Constants.PATH)).forEach(file ->{
            String done = file.getName().replace(Constants.EXTENSION,"");
            List<String> stringList = null;
            try {
                stringList = this.fileService.processString(new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
            List<Salesman> salesmanList = this.fileService.getSalesman(stringList);
            List<Sale> saleList = this.fileService.getSaleList(stringList,salesmanList);
            List<Client> clients = this.fileService.getClientList(stringList);
            Map<String, String> bestSaleAndWorseSaleman = this.fileService.bestSaleAndWorseSaleman(saleList);
            this.fileService.writer(ProcessDTO.builder()
                    .done(done)
                    .clientList(clients)
                    .saleList(saleList)
                    .salesmanList(salesmanList)
                    .bestSaleAndWorseSaleman(bestSaleAndWorseSaleman)
                    .build());
        });
        }



}
