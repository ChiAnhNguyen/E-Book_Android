package com.example.bookselling.MyAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.bookselling.CartActivity;
import com.example.bookselling.Database.Db_Cart;
import com.example.bookselling.MainActivity;
import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.EventBus.TinhTongEvent;
import com.example.bookselling.Model.User;
import com.example.bookselling.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    Context context;
    List<Cart> cartList;
    LayoutInflater inflater;
    Db_Cart dbCart;
    User user;
    public CartAdapter(Context context, List<Cart> cartList,User user) {
        this.context = context;
        this.cartList = cartList;
        this.user = user;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_giohang,null);
            holder.productName =convertView.findViewById(R.id.cart_product_name);
            holder.soluong_cart =convertView.findViewById(R.id.soluong_cart);
            holder.item_cart_price =convertView.findViewById(R.id.item_cart_price);
            holder.txt_giohang_tongtien = convertView.findViewById(R.id.txt_giohang_tongtien);
            holder.productImage = convertView.findViewById(R.id.cart_product_image);
            holder.item_tru = convertView.findViewById(R.id.item_tru);
            holder.item_cong = convertView.findViewById(R.id.item_cong);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Cart cart = cartList.get(position);
        holder.productName.setText(cart.getProductName());
        holder.item_cart_price.setText(cart.getPrice()+"$");
        holder.soluong_cart.setText(cart.getQuantity()+"");
        int productID = cart.getProductID();
        double tongtien =cart.getPrice() * cart.getQuantity();
        holder.txt_giohang_tongtien.setText(tongtien+"$");
        Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(holder.productImage);
        ViewHolder finalHolder = holder;
        holder.item_cong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbCart = new Db_Cart(context.getApplicationContext());
                if(cartList.get(position).getQuantity()<100){
                    cart.setQuantity(cartList.get(position).getQuantity()+1);
                    dbCart.updateCartItem(cart);
                }
                finalHolder.soluong_cart.setText(cartList.get(position).getQuantity()+"");
                double giatien_update = cartList.get(position).getPrice() * cartList.get(position).getQuantity();
                finalHolder.txt_giohang_tongtien.setText(giatien_update+"$");
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }

        });

        holder.item_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbCart = new Db_Cart(context.getApplicationContext());
                if(cartList.get(position).getQuantity()>1){
                    cart.setQuantity(cartList.get(position).getQuantity()-1);
                    dbCart.updateCartItem(cart);
                }else if(cart.getQuantity()==1) {
                    cart.setQuantity(cartList.get(position).getQuantity()-1);
                   dbCart.removeCartItem(cart);

                   cartList=dbCart.getCartByUserID(user.getCustomerID());
                   notifyDataSetChanged();
                }if(position >= 0 && position < cartList.size()){
                    finalHolder.soluong_cart.setText(cartList.get(position).getQuantity()+"");
                    double giatien_update = cartList.get(position).getPrice() * cartList.get(position).getQuantity();
                    finalHolder.txt_giohang_tongtien.setText(giatien_update+"$");
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }

            }
        });

        return convertView;
    }
    public class ViewHolder{
        ImageView productImage;
        TextView productName,item_cart_price,soluong_cart,txt_giohang_tongtien;
        ImageView item_tru,item_cong;
    }
}
