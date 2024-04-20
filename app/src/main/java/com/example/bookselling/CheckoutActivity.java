package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookselling.Database.Db_Cart;
import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.Order;
import com.example.bookselling.Model.User;
import com.example.bookselling.Service.MyRetrofit;
import com.example.bookselling.Service.OrderService;
import com.example.bookselling.Service.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTongTien,txtSDT,txtName;
    EditText editDiachi;
    AppCompatButton btnDathang;
    private User user;
    private Db_Cart dbCart;
    private  OrderService orderService;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        orderService = MyRetrofit.getClient().create(OrderService.class);
        initView();
        getUserPreferences();
        initData();
        initControll();

    }

    private void initData() {
        double tongtien = getIntent().getDoubleExtra("tongtien",0);
        txtTongTien.setText(String.valueOf(tongtien));
        txtName.setText(user.getCustomerName());
        txtSDT.setText(user.getPhone());

    }

    private void initControll() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_diachi = editDiachi.getText().toString().trim();
                dbCart = new Db_Cart(getApplicationContext());
                Utils.arr_Cart = getCartFromUser(user);
                order = new Order();

                Log.d("Order","order is:"+order.toString());
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else {
                    for(Cart cart : Utils.arr_Cart){
                        order.setProductID(cart.getProductID());
                        order.setCustommerID(user.getCustomerID());
                        order.setCustomerName(user.getCustomerName());
                        order.setPhonenumb(user.getPhone());
                        order.setDiachi(editDiachi.getText().toString());
                        addOrderToServer(order);
                    }
                    dbCart.removeCartItemByUserID(user.getCustomerID());
                }
            }
        });
    }

    private void initView() {
        toolbar =findViewById(R.id.toolbar);
        txtTongTien = findViewById(R.id.txt_price_checkout);
        txtSDT = findViewById(R.id.txt_sdt_checkout);
        txtName = findViewById(R.id.txt_name_checkout);
        editDiachi = findViewById(R.id.inputlayout);
        btnDathang = findViewById(R.id.btn_dathang);
    }
    public User getUserPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userString= sharedPreferences.getString("user",null);
        user = gson.fromJson(userString,User.class);
        return user;
    }
    public List<Cart> getCartFromUser(User user){
        dbCart = new Db_Cart(getApplicationContext());
        List<Cart> listCart = new ArrayList<>();
        listCart = dbCart.getCartByUserID(user.getCustomerID());
        return listCart;
    }
    public void addOrderToServer(Order order){

        Call<Integer>call = orderService.addorder(order);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    int rows = response.body();
                    Log.d("rowupdate","row upodated: "+rows);

                    Intent finish = new Intent(getApplicationContext(),Finish_buy.class);
                    startActivity(finish);
                }
                else{
                    Log.e("API","fial to call API");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}