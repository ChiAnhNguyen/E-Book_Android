package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.bookselling.Model.product_detail;
import com.squareup.picasso.Picasso;

public class detail_productActivity extends AppCompatActivity {
    private TextView productName,categotyName,price,author,publisher,descript;
    private Button btnAddToCart;
    private ImageView imageProduct;
    private Spinner spinner;
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);
        initView();
//        ActionToolbat();
        initData();
    }

    private void initData() {
    product_detail detail = (product_detail) getIntent().getSerializableExtra("PRODUCT_DETAIL");
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

//        toolbar = findViewById(R.id.toolbar);
    }
//    private void ActionToolbat() {
//        setSupportActionBar(toolbar.findViewById(R.id.toolbar));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }


}