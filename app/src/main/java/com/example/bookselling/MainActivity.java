package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Model.Category;
import com.example.bookselling.Model.Product;
import com.example.bookselling.Model.productImage;
import com.example.bookselling.MyAdapter.ProductAdapter;
import com.example.bookselling.Service.MyRetrofit;
import com.example.bookselling.Service.ProductService;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProductService productService;
    private ListView list_product;
    private ProductAdapter productAdapter;

    private EditText etSearch;
    private ImageButton btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_product = findViewById(R.id.list_product);
        // Khởi tạo Retrofit Service
        productService = MyRetrofit.getClient().create(ProductService.class);
        loadData();

//    private void performSearch(String keyword) {
//
//    }

}
    private void loadData(){
        // Thực hiện cuộc gọi API và xử lý kết quả
        Call<List<Map<String, Object>>> call = productService.getAllProducts();
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> productList = response.body();
//                    int sizeOflist = productList.size();
                    Log.d("productList","List of product:"+productList);
                    // Xử lý danh sách sản phẩm ở đây
                    productAdapter = new ProductAdapter(productList,MainActivity.this);
                    productAdapter.setList_product(productList);
//                    for (Map<String, Object> productMap : productList) {
////                     Product product = (Product) productMap.get("product");
////                        Category category = (Category)productMap.get("category");
////                        productImage image = (productImage)productMap.get("imageData");
//                        Map<String, Object> bookMap = (Map<String, Object>) productMap.get("product");
//                         if (bookMap != null) {
//                // Truy cập các trường của đối tượng Book từ Map con
//                String productName = (String) bookMap.get("productName");
//                String descript = (String) bookMap.get("Descript");
//                Double productID_db =  (Double) bookMap.get("productID");
//                int productID = productID_db.intValue();
//                String author = (String) bookMap.get("author");
//                String publisher = (String) bookMap.get("publisher");
//                double price = (double) bookMap.get("price");
//                // Và các xử lý khác...
//
//                // Hiển thị thông tin của đối tượng Book hoặc thực hiện xử lý khác tại đây
//                Log.d("ProductAdapter", "Product Name: " + productName);
//                Log.d("ProductAdapter", "Description: " + descript);
//                Log.d("ProductAdapter", "Product ID: " + productID);
//                Log.d("ProductAdapter", "Author: " + author);
//                Log.d("ProductAdapter", "Publisher: " + publisher);
//                Log.d("ProductAdapter", "Price: " + price);
//            }
//                    }
                    list_product.setAdapter(productAdapter);

//                    Toast.makeText(getApplicationContext(), "call API success", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("ProductAdapter", "Error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // Xử lý khi gặp lỗi kết nối
                Log.e("ProductAdapter", "Error: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "khong call duoc api", Toast.LENGTH_SHORT).show();
            }
        });

//        Category category = new Category();
//        productList = new ArrayList<>();
//        productList.add(new Product("Kiến Trúc Máy Tính", "Description 1","FUROFUSHI","Kim Dong", 10.99,category));


        // Khởi tạo RecyclerView và ProductAdapter

//        productAdapter = new ProductAdapter(productList, this);

        // Thiết lập LayoutManager cho RecyclerView

    }

}
