package com.s.karpardaz.stock.data;

import com.s.karpardaz.stock.model.Stock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockService {

    @GET("Stocks")
    Call<List<Stock>> getAllStocks(@Query("userId") String userId);

}
