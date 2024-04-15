package com.example.bookselling.Service;

import com.example.bookselling.Model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("rest/products") // Địa chỉ API
    Call<List<Map<String, Object>>> getAllProducts();
}
