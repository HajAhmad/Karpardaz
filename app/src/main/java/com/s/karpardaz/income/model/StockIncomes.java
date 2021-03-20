package com.s.karpardaz.income.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.s.karpardaz.stock.model.Stock;

import java.util.List;

public class StockIncomes {
    @Embedded
    private final Stock stock;
    @Relation(parentColumn = "uuid", entityColumn = "stockId")
    private final List<Income> incomes;

    public StockIncomes(Stock stock, List<Income> incomes) {
        this.stock = stock;
        this.incomes = incomes;
    }

    public Stock getStock() {
        return stock;
    }

    public List<Income> getIncomes() {
        return incomes;
    }
}
