package com.s.karpardaz.cost.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.s.karpardaz.cost.model.Cost;
import com.s.karpardaz.stock.model.Stock;

import java.util.List;

public class StockCosts {
    @Embedded
    private final Stock stock;
    @Relation(parentColumn = "uuid", entityColumn = "stockId")
    private final List<Cost> costs;

    public StockCosts(Stock stock, List<Cost> costs) {
        this.stock = stock;
        this.costs = costs;
    }

    public Stock getStock() {
        return stock;
    }

    public List<Cost> getCosts() {
        return costs;
    }
}
