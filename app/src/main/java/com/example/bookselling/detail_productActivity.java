package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.product_detail;
import com.example.bookselling.Service.Utils;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import okhttp3.internal.Util;

public class detail_productActivity extends AppCompatActivity {
    private TextView productName,categotyName,price,author,publisher,descript;
    private Button btnAddToCart;
    private ImageView imageProduct;
    private Spinner spinner;
    product_detail detail;
    NotificationBadge badge;
    private Toolbar toolbar;

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
                addToCart();
            }
        });
    }

    private void addToCart() {
        if(Utils.arr_Cart.size()>0){
            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
            boolean flag = false;
            for(int i = 0;i<Utils.arr_Cart.size(); i++){
                if(Utils.arr_Cart.get(i).getProductID()==detail.getProductID()){
                    Utils.arr_Cart.get(i).setQuantity(quantity + Utils.arr_Cart.get(i).getQuantity());
                    double price_cart = Utils.arr_Cart.get(i).getQuantity() * detail.getPrice();
                    Utils.arr_Cart.get(i).setPrice(price_cart);
                    flag = true;
                }
            }
            if(flag==false){
                double price_cart = quantity * detail.getPrice();
                Cart cart = new Cart();
                cart.setPrice(price_cart);
                cart.setQuantity(quantity);
                cart.setProductID(detail.getProductID());
                cart.setProductName(detail.getProductName());
                int productID = cart.getProductID();
//            Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(imageProduct);
                Utils.arr_Cart.add(cart);
            }

        }else{
            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
            double price_cart = quantity * detail.getPrice();
            Cart cart = new Cart();
            cart.setPrice(price_cart);
            cart.setQuantity(quantity);
            cart.setProductID(detail.getProductID());
            cart.setProductName(detail.getProductName());
            int productID = cart.getProductID();
//            Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(imageProduct);
            Utils.arr_Cart.add(cart);
        }
        int totalitem = 0;
        for(int i =0; i<Utils.arr_Cart.size();i++){
            totalitem = totalitem + Utils.arr_Cart.get(i).getQuantity();
        }
        badge.setText(String.valueOf(totalitem));
    }

    private void initData() {
    detail = (product_detail) getIntent().getSerializableExtra("PRODUCT_DETAIL");
    productName.setText(detail.getProductName());
    categotyName.setText(detail.getProductName());
    Double price_db = detail.getPrice();
    price.setText(price_db.toString()+"$");
    author.setText(detail.getAuthor());
    publisher.setText(detail.getPublisher());
    descript.setText(detail.getDescript());
    int productID= detail.getProductID();
        Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(imageProduct);
        Integer[]so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterSpin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterSpin);
    }

    private void initView() {
        productName =findViewById(R.id.detail_name);
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
                Intent cart = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(cart);
            }
        });
        if(Utils.arr_Cart != null){
            int totalitem = 0;
            for(int i =0; i<Utils.arr_Cart.size();i++){
                totalitem = totalitem + Utils.arr_Cart.get(i).getQuantity();
            }
            badge.setText(String.valueOf(totalitem));
        }

//        toolbar = findViewById(R.id.toolbar);
    }
    private void ActionToolbat() {
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