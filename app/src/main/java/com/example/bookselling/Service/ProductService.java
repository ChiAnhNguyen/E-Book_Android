package com.example.bookselling.Service;

import com.example.bookselling.Model.Product;
import com.example.bookselling.Model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductService {
    @GET("rest/products") // Địa chỉ API
    Call<List<Map<String, Object>>> getAllProducts();


}
