package com.example.bookselling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookselling.Model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartProductList;
    private LayoutInflater mInflater;

    public CartAdapter(Context context, List<Product> cartProductList) {
        mInflater = LayoutInflater.from(context);
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_cart, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product currentProduct = cartProductList.get(position);
        // Miễn là cartProductList đã đúng và có items trong đó,
        // hãy gán giá trị cho các view trong holder ở đây
        holder.cartProductName.setText(currentProduct.getProductName());
        holder.cartProductPrice.setText(String.valueOf(currentProduct.getPrice()));
        // Các xử lý khác nếu có
    }

    @Override
    public int getItemCount() {
        return cartProductList.size(); // Trả về số lượng items trong giỏ hàng
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartProductName, cartProductPrice;

        private CartViewHolder(View itemView) {
            super(itemView);
            cartProductName = itemView.findViewById(R.id.cart_product_name);
            cartProductPrice = itemView.findViewById(R.id.cart_product_price);
            // Khởi tạo các view khác nếu cần
        }
    }
}