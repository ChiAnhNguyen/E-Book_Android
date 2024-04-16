package com.example.bookselling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.EventBus.TinhTongEvent;
import com.example.bookselling.Model.Product;
import com.example.bookselling.MyAdapter.CartAdapter;
import com.example.bookselling.Service.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextView giohangtrong,tongtien;
    private Toolbar toolbar;
    private ListView list_cart;
    private Button btnMuahang;
    private  CartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        initView();
        initControl();
        tinhTongTien();
    }

    private void tinhTongTien() {
        double tontienSP = 0;
        for(int i = 0; i<Utils.arr_Cart.size(); i++){
            tontienSP = tontienSP + Utils.arr_Cart.get(i).getPrice()* Utils.arr_Cart.get(i).getQuantity();
        }
        tongtien.setText(tontienSP+"$");
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(Utils.arr_Cart.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else{
            adapter = new CartAdapter(this,Utils.arr_Cart);
            list_cart.setAdapter(adapter);
        }
    }

    private void initView() {
        giohangtrong = findViewById(R.id.emtyCart);
        tongtien = findViewById(R.id.txttontien);
        toolbar = findViewById(R.id.toolbarInCart);
        list_cart = findViewById(R.id.listcart);
        btnMuahang = findViewById(R.id.btnCheckout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void enventTinhTongTien(TinhTongEvent event){
        if(event!=null){
            tinhTongTien();
        }
    }
}
