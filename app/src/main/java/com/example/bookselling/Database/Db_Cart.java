package com.example.bookselling.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.bookselling.CartActivity;
import com.example.bookselling.Model.Cart;
import com.example.bookselling.Model.User;
import com.example.bookselling.Model.product_detail;

import java.util.ArrayList;
import java.util.List;

public class Db_Cart extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cart_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CART = "cart";
    private static final String COLUMN_CART_ID = "cartId";
    private static final String COLUMN_USER_ID = "iduser";
    private static final String COLUMN_PRODUCT_ID = "productID";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";
    private static final int MAX_QUANTITY = 100;
    private static final String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
            + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_PRODUCT_ID + " INTEGER,"
            + COLUMN_PRODUCT_NAME + " TEXT,"
            + COLUMN_QUANTITY + " INTEGER,"
            + COLUMN_PRICE + " REAL"
            + ")";


    public Db_Cart( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void addCartItem(product_detail detail, User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getCustomerID());
        values.put(COLUMN_PRODUCT_ID, detail.getProductID());
        values.put(COLUMN_PRODUCT_NAME, detail.getProductName());
        values.put(COLUMN_QUANTITY, detail.getQuantity());
        values.put(COLUMN_PRICE, detail.getPrice());
        Log.d("add to cart","add to cart successfully");
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public List<Cart> getCartByUserID(int userID){
        List<Cart> cartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_CART_ID,
                COLUMN_USER_ID,
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_QUANTITY,
                COLUMN_PRICE
        };
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userID)};
        Cursor cursor = db.query(
                TABLE_CART,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") int cartId = cursor.getInt(cursor.getColumnIndex(COLUMN_CART_ID));
                @SuppressLint("Range") int productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                Cart cart = new Cart(cartId, productId, productName, price, quantity);
                cartList.add(cart);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return cartList;
    }
    public int getTotalItemInCart(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_QUANTITY + ") FROM " + TABLE_CART + " WHERE " + COLUMN_USER_ID + " = " + userID, null);
        int totalItem = 0;
        if (cursor.moveToFirst()) {
            totalItem = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return totalItem;
    }
    public void updateCartItem(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, cart.getQuantity());
        db.update(TABLE_CART, values, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cart.getCartID())});
        db.close();
        Log.d("Update quantity","Update complete at Cart ID: "+cart.getCartID());
    }
    public void removeCartItem(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_CART_ID + " = ?",
                new String[]{String.valueOf(cart.getCartID())});
        db.close();
        Log.d("Remove Cart","remove successfully at Cart ID: "+cart.getCartID());
    }
    public void removeCartItemByUserID(int userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_USER_ID + "=?";
        String[] whereArgs = { String.valueOf(userID) };
        db.delete(TABLE_CART, whereClause, whereArgs);
        db.close();
        Log.d("Remove Cart", "Remove successfully for User ID: " + userID);
    }


}
