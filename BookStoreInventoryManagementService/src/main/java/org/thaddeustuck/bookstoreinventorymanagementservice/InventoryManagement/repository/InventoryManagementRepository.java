package org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.repository;


import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.models.Book;
import org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.models.InventoryRecord;
import org.thaddeustuck.bookstoreinventorymanagementservice.InventoryManagement.models.Valuation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagementRepository {

    public InventoryManagementRepository(){
    }

    public Map<Integer,Book> getBooks(){
        Map<Integer,Book> booksByUPC = new HashMap<>();

        String bookURL= "http://localhost:8080/books";
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

    public Map<Integer, InventoryRecord> getInventoryRecords() {
        Map<Integer,InventoryRecord> inventoryRecordsByUPC = new HashMap<>();

        String inventoryURL= "http://localhost:8081/inventory";
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

    public Valuation getValuation(){
        Valuation valuation = null;
        String inventoryURL= "http://localhost:8082/api/v1/valuation";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(inventoryURL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            Gson gson = new Gson();
            valuation = gson.fromJson(json, Valuation.class);
        }
        catch (IOException ex) {
        }
        return valuation;
    }


}
