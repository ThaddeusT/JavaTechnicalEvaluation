package com.thaddeustuck.bookstorevaluationservice.Valuation.repository;

import ch.qos.logback.core.net.server.Client;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.thaddeustuck.bookstorevaluationservice.Valuation.config.ConfigProperties;
import com.thaddeustuck.bookstorevaluationservice.Valuation.models.Book;
import com.thaddeustuck.bookstorevaluationservice.Valuation.models.InventoryRecord;
import com.thaddeustuck.bookstorevaluationservice.Valuation.models.Valuation;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thaddeustuck.bookstorevaluationservice.Valuation.models.ValuationRecord;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ArrayUtils;


@Repository
public class ValuationRepository {
    @Autowired
    private ConfigProperties configProp;

    public ValuationRepository(){
    }


    public Valuation calculateAllItemValuation(){
        Map<Integer,Book> books = getBooks();
        Map<Integer,InventoryRecord> inventoryRecords = getInventoryRecords();

        return calculateValuation(books,inventoryRecords);
    }

    private Map<Integer,Book> getBooks(){
        Map<Integer,Book> booksByUPC = new HashMap<>();

        String bookURL= configProp.getConfigValue("books-service-url");
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(bookURL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            Gson gson = new Gson();
            Book[] books = gson.fromJson(json, Book[].class);

            for(Book book : books){
                booksByUPC.put(book.getUpc(), book);
            }
        }
        catch (IOException ex) {
        }
        return booksByUPC;
    }

    private Map<Integer, InventoryRecord> getInventoryRecords() {
        Map<Integer,InventoryRecord> inventoryRecordsByUPC = new HashMap<>();

        String inventoryURL= configProp.getConfigValue("inventory-service-url");
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(inventoryURL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            Gson gson = new Gson();
            InventoryRecord[] inventoryRecords = gson.fromJson(json, InventoryRecord[].class);

            for(InventoryRecord inventoryRecord : inventoryRecords){
                inventoryRecordsByUPC.put(inventoryRecord.getUpc(), inventoryRecord);
            }
        }
        catch (IOException ex) {
        }
        return inventoryRecordsByUPC;
    }

    public Valuation calculateValuation(Map<Integer,Book> books, Map<Integer,InventoryRecord> inventoryRecords){
        Integer totalQuantity = 0;
        Double totalValue = 0.0;
        List<ValuationRecord> valuationRecords = new ArrayList<>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            ValuationRecord valuationRecord = new ValuationRecord(book.getUpc(),book.getPrice());
            InventoryRecord inventoryRecord = inventoryRecords.getOrDefault(entry.getKey(),null);
            if(inventoryRecord ==  null){
                valuationRecord.setQuantity(0);
                valuationRecord.setValuation(0.0);
            }
            else{
                valuationRecord.setQuantity(inventoryRecord.getQuantity());
                totalQuantity += inventoryRecord.getQuantity();

                valuationRecord.setValuation(valuationRecord.getPrice()*valuationRecord.getQuantity());
                totalValue += valuationRecord.getValuation();

                valuationRecords.add(valuationRecord);
            }
        }
        ValuationRecord[] valuations = new ValuationRecord[valuationRecords.size()];
        Valuation valuation = new Valuation(valuationRecords.toArray(valuations),totalQuantity,totalValue);
        return valuation;
    }
}
