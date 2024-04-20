package com.example.bookselling.Service;

import com.example.bookselling.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("rest/products")
    Call<Void> addCustommer(@Body User user);

    @GET("rest/products/user/{customername}")
    Call<User> findCustomerByName(@Path("customername") String username);
}
