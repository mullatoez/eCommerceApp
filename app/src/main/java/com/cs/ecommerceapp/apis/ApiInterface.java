package com.cs.ecommerceapp.apis;

import com.cs.ecommerceapp.model.Customer;
import com.cs.ecommerceapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("customer/create")
    Call<Customer> createCustomer(@Body Customer customer);

    @GET("products")
    Call<List<Product>> getProducts();

}
