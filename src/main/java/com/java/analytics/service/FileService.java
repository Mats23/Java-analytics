package com.java.analytics.service;



import com.java.analytics.domain.Client;
import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;
import com.java.analytics.dto.ProcessDTO;
import com.java.analytics.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class FileService  {

    @Autowired
    SalesmanService SalesmanService;
    @Autowired
    ClientService clientService;
    @Autowired
    SaleServiceImpl saleService;


    public boolean save(byte[] file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(Constants.PATH)).getPath() + new Random().nextInt(10000)+Constants.EXTENSION)) {
            fos.write(file);
            return true;
        }

    }

    public File[] stackFiles(String path) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        FilenameFilter fileFilter = (dir,filename) -> filename.toLowerCase().endsWith(Constants.EXTENSION);
        return new File(Objects.requireNonNull(url).getPath()).listFiles(fileFilter);

    }


    public List<String> processString(BufferedReader br) {
        return separeteLines(br);

    }
    public List<Sale> getSaleList(List<String> stringList, List<Salesman> salesmanList ) {
        return saleService.create(stringList,salesmanList);
    }
    public List<Client> getClientList(List<String> stringList) {
        return clientService.create(stringList);
    }
    public List<Salesman> getSalesman(List<String> stringList) {
        return SalesmanService.create(stringList);
    }
    public Map<String, String>  bestSaleAndWorseSaleman(List<Sale> saleList) {
        return saleService.calculateSale(saleList);
    }
    public void writer(ProcessDTO processDTO) {
        String filePath = String.format("src/main/resources/data/out/%s.done.dat", processDTO.getDone()) ;
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            writer.append(String.format("Salesman: %s", processDTO.getSalesmanList().size()));
            writer.append(System.lineSeparator());
            writer.append(String.format("Clients: %s", processDTO.getClientList().size()));
            writer.append(System.lineSeparator());
            writer.append(String.format("ID High Salesman: %s", processDTO.getBestSaleAndWorseSaleman().get("High")));
            writer.append(System.lineSeparator());
            writer.append(String.format("ID Worse Salesman: %s", processDTO.getBestSaleAndWorseSaleman().get("less")));

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    private List<String> separeteLines(BufferedReader br) {
        final List<String> stringList = new ArrayList<>();
        br.lines().forEach(a -> stringList.addAll(Arrays.asList(a.split("ç"))));
        return stringList;
    }





}
