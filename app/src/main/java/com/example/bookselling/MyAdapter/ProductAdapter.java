package com.example.bookselling.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Model.Category;
import com.example.bookselling.Model.Product;
import com.example.bookselling.Model.productImage;
import com.example.bookselling.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;

public class ProductAdapter extends BaseAdapter {
    private List<Map<String,Object>> list_product ;
    private Context context;
    private LayoutInflater inflater;

    public ProductAdapter(List<Map<String, Object>> list_product, Context context) {
        this.list_product = list_product;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void setList_product(List<Map<String, Object>> list_product) {
        this.list_product = list_product;
    }

    @Override
    public int getCount() {
        return list_product.size();
    }

    @Override
    public Object getItem(int position) {

        return list_product.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        holder = new ViewHolder();
        if(convertView == null){

            convertView = inflater.inflate(R.layout.list_item_product,null);
            holder.productName = convertView.findViewById(R.id.textViewProductName);
            holder.categoryName = convertView.findViewById(R.id.textViewCategoryName);
            holder.author = convertView.findViewById(R.id.textViewAuthor);
            holder.price = convertView.findViewById(R.id.textViewProductPrice);
            holder.imageproduct =convertView.findViewById(R.id.imageViewProduct);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();// lưu lại viewholder để tái sử dụng
        }
        Map<String, Object> productMap = this.list_product.get(position);// Lấy phần tử từ danh sách ứng với vị trí position
        Log.d("Map","List Map: "+productMap);
        Product product = new Product();
        Category category = new Category();
        Map<String, Object> productInnerMap = (Map<String, Object>) productMap.get("product");
        Map<String, Object> categorynnerMap = (Map<String, Object>) productMap.get("category");
        Log.d("category","category: "+categorynnerMap);
//        Map<String, Object> imageDataInnerMap = (Map<String, Object>) productMap.get("imageData");
        String productName = (String)productInnerMap.get("productName") ;
        String author = (String)productInnerMap.get("author");
        String categoryName = (String)categorynnerMap.get("categoryName");
        Double price = (Double)productInnerMap.get("price");
        Log.d("category name","category name: "+categoryName);
        Double productID_db = (Double)productInnerMap.get("productID");
        String Descript = (String) productInnerMap.get("Descript");
        int productID = productID_db.intValue();
        Double categoryID_db = (Double)categorynnerMap.get("categoryID");
        int categoryID = categoryID_db.intValue();
        product.setDescript(Descript);
        product.setProductID(productID);
        product.setProductName(productName);
        product.setAuthor(author);
        product.setPrice(price);
        String price_str = price.toString();
        category.setCategoryName(categoryName);
        category.setCategoryID(categoryID);
        Log.d("product name","Name : "+productName);
        try {
            holder.productName.setText(product.getProductName());
            holder.author.setText(product.getAuthor());
            holder.categoryName.setText(category.getCategoryName());
            holder.price.setText(price_str+"$");
            Picasso.get().load("http://10.0.2.2:8080/Ebook-web-app/rest/products/image/"+productID).into(holder.imageproduct);
        }catch (Exception e){

        }
        return convertView;
    }

    public class  ViewHolder{
        ImageView imageproduct;
        TextView productName;
        TextView author;
        TextView categoryName;
        TextView price;
    }
}