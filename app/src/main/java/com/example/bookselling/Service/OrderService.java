package com.example.bookselling.Service;

import com.example.bookselling.Model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {
    @POST("rest/products/order")
    Call<Integer> addorder(@Body Order oder);
}
