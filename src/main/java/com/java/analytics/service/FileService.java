package com.java.analytics.service;



import com.java.analytics.domain.Client;
import com.java.analytics.domain.Sale;
import com.java.analytics.domain.Salesman;
import com.java.analytics.dto.ProcessDTO;
import com.java.analytics.utils.Constants;
import org.springframework.batch.core.ExitStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileService  {

    SalesmanService SalesmanService = new SalesmanService();
    ClientService clientService = new ClientService();
    SaleServiceImpl saleService = new SaleServiceImpl();


    public File[] stackFiles(String path) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        FilenameFilter fileFilter = (dir,filename) -> filename.toLowerCase().endsWith(Constants.EXT);
        return new File(url.getPath()).listFiles(fileFilter);

    }


    public List<String> processString(BufferedReader br) throws IOException {
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
    public ExitStatus writer(ProcessDTO processDTO) {
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

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return ExitStatus.FAILED;

        }

        return ExitStatus.COMPLETED;

    }

    private List<String> separeteLines(BufferedReader br) {
        final List<String> stringList = new ArrayList<>();
        br.lines().forEach(a -> {

            String[] lines = a.split("ç");
            for (String line : lines) {
                stringList.add(line);
            }
        });


        return stringList;
    }





}
