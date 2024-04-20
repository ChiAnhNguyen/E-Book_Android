package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookselling.Database.Db_Cart;
import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.User;
import com.example.bookselling.Model.product_detail;
import com.example.bookselling.Service.Utils;
import com.google.gson.Gson;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class detail_productActivity extends AppCompatActivity {
    private TextView productName,categotyName,price,author,publisher,descript;
    private Button btnAddToCart;
    private ImageView imageProduct;
    private Spinner spinner;
    product_detail detail;
    NotificationBadge badge;
    private Toolbar toolbar;
    private User user;
    private  Db_Cart dbCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        initView();
        ActionToolbat();
        initData();
        initControl();
    }

    private void initControl() {
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(user==null){

                    Toast.makeText(getApplicationContext(), "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                    Intent Login = new Intent(getApplicationContext(),loginActivity.class);
                    startActivity(Login);
                    }else {
                        detail.setQuantity(Integer.parseInt(spinner.getSelectedItem().toString()));
                        Log.d("User","User ID:"+user.getCustomerID());
                        Log.d("product detail","product Id: "+detail.getProductID());
                        Log.d("product detail","product detail: "+detail);
                        addToCart();

                    }
            }
        });
    }

    private void addToCart() {
        dbCart = new Db_Cart(getApplicationContext());
//        dbCart.addCartItem(detail, user);
        Utils.arr_Cart = dbCart.getCartByUserID(user.getCustomerID());
        if (Utils.arr_Cart.size() > 0) {
            //nếu có sản phẩm trong giỏ hàng thì
            boolean productExistsInCart = false;
            for (Cart cart : Utils.arr_Cart) {
                if (cart.getProductID() == detail.getProductID()) {
                    // Sản phẩm đã tồn tại trong giỏ hàng
                    int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
                    cart.setQuantity(cart.getQuantity() + quantity); // Tăng số lượng sản phẩm trong giỏ hàng
                    dbCart.updateCartItem(cart);
                    double price_cart = cart.getQuantity() * detail.getPrice(); // Tính lại giá mới
                    cart.setPrice(price_cart); // Cập nhật giá trong giỏ hàng
                    productExistsInCart = true;
                    break;
                }
            }if (!productExistsInCart) {
                // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thì thêm sản phẩm mới vào Utils.arr_Cart
                dbCart.addCartItem(detail, user);
                Utils.arr_Cart=dbCart.getCartByUserID(user.getCustomerID());// Hoặc bạn có thể thêm sản phẩm mới vào giỏ hàng ở đây nếu cần thiết
            }

        }else{
            dbCart.addCartItem(detail, user);
            Utils.arr_Cart=dbCart.getCartByUserID(user.getCustomerID());
        }
        badge.setText(String.valueOf(dbCart.getTotalItemInCart(user.getCustomerID())));

//            if(flag==false){
////                double price_cart = quantity * detail.getPrice();
////                Cart cart = new Cart();
////                cart.setPrice(price_cart);
////                cart.setQuantity(quantity);
////                cart.setProductID(detail.getProductID());
////                cart.setProductName(detail.getProductName());
////                int productID = cart.getProductID();
//            //Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(imageProduct);
//                dbCart.addCartItem(detail,user);
//            }
//                else{
//////            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
//////            double price_cart = quantity * detail.getPrice();
//////            Cart cart = new Cart();
//////            cart.setPrice(price_cart);
//////            cart.setQuantity(quantity);
//////            cart.setProductID(detail.getProductID());
//////            cart.setProductName(detail.getProductName());
//////            int productID = cart.getProductID();
//////            Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(imageProduct);
//                dbCart.addCartItem(detail,user);
//           }



        }

        private void initData () {
            detail = (product_detail) getIntent().getSerializableExtra("PRODUCT_DETAIL");
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String userString = sharedPreferences.getString("user", null);
            user = gson.fromJson(userString, User.class);
            productName.setText(detail.getProductName());
            categotyName.setText(detail.getProductName());
            Double price_db = detail.getPrice();
            price.setText(price_db.toString() + "$");
            author.setText(detail.getAuthor());
            publisher.setText(detail.getPublisher());
            descript.setText(detail.getDescript());
            int productID = detail.getProductID();
            Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/" + productID).into(imageProduct);
            Integer[] so = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ArrayAdapter<Integer> adapterSpin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
            spinner.setAdapter(adapterSpin);
            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
            Log.d("quantity", "quantity: " + quantity);
            detail.setQuantity(quantity);
            dbCart = new Db_Cart(getApplicationContext());
            badge.setText(String.valueOf(dbCart.getTotalItemInCart(user.getCustomerID())));
        }

        private void initView () {
            productName = findViewById(R.id.detail_name);
            categotyName = findViewById(R.id.detail_categoryname);
            price = findViewById(R.id.detail_price);
            author = findViewById(R.id.detail_author);
            publisher = findViewById(R.id.detail_publisher);
            descript = findViewById(R.id.detail_descript);
            btnAddToCart = findViewById(R.id.btnaddtocart);
            spinner = findViewById(R.id.spinner);
            imageProduct = findViewById(R.id.imgdetail);
            badge = findViewById(R.id.menu_sl);
            toolbar = findViewById(R.id.toolbar);
            FrameLayout frameCart = findViewById(R.id.framecart);
            frameCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(cart);
                }
            });


//        toolbar = findViewById(R.id.toolbar);
        }
        private void ActionToolbat () {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


    }