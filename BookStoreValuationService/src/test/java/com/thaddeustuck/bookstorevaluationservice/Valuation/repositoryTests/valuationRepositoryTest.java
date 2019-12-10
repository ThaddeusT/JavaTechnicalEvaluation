package com.thaddeustuck.bookstorevaluationservice.Valuation.repositoryTests;

import com.thaddeustuck.bookstorevaluationservice.Valuation.models.Book;
import com.thaddeustuck.bookstorevaluationservice.Valuation.models.InventoryRecord;
import com.thaddeustuck.bookstorevaluationservice.Valuation.models.Valuation;
import com.thaddeustuck.bookstorevaluationservice.Valuation.repository.ValuationRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class valuationRepositoryTest {
    private final ValuationRepository valuationRepository;

    public valuationRepositoryTest() {
        this.valuationRepository = new ValuationRepository();
    }

    @Test
    public void testCalculateValuation(){
        Map<Integer, Book> books = new HashMap<>();
        books.put(1, new Book(1,"Test Title","Test Author", "Test Description", 10.00));

        Map<Integer, InventoryRecord> inventoryRecordsByUPC = new HashMap<>();
        inventoryRecordsByUPC.put(1,new InventoryRecord(1, 10,0));

        Valuation valuation = valuationRepository.calculateValuation(books, inventoryRecordsByUPC);
        assertEquals(10, valuation.getTotalQuantity());
        assertEquals(100.00, valuation.getTotalValue());
        assertEquals(1,valuation.getValuations().length);
        assertEquals(100.00,valuation.getValuations()[0].getValuation());
    }
}
