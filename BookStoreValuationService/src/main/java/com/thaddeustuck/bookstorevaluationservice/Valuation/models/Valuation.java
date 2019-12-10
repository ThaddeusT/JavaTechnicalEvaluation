package com.thaddeustuck.bookstorevaluationservice.Valuation.models;

public class Valuation {

    private int totalQuantity;
    private double totalValue;
    private ValuationRecord[] valuations;

    public Valuation(ValuationRecord[] valuations, int totalQuantity, double totalValue)
    {
        this.valuations = valuations;
        this.totalQuantity=totalQuantity;
        this.totalValue = totalValue;
    }

    public int getTotalQuantity(){
        return totalQuantity;
    }

    public double getTotalValue(){
        return totalValue;
    }

    public ValuationRecord[] getValuations(){
        return valuations;
    }
}
