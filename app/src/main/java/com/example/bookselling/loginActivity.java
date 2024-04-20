package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.bookselling.Model.User;
import com.example.bookselling.Service.MyRetrofit;
import com.example.bookselling.Service.UserService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    Button loginButton;
    Button backButton, register;
    private EditText usernameEditText, passwordEditText;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Ánh xạ các thành phần giao diện
        loginButton = findViewById(R.id.lgLogin);
        backButton = findViewById(R.id.lgBack);
        register = findViewById(R.id.btnregister);
        usernameEditText = findViewById(R.id.lgUsername);
        passwordEditText = findViewById(R.id.lgPassword);
        userService = MyRetrofit.getClient().create(UserService.class);

        // Thiết lập sự kiện click cho nút "Login"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng nhập ở đây
                loginUser();
            }
        });

        //Sự kiện click nút back
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(register);
            }
        });
    }
    private void loginUser() {
        // Lấy thông tin người dùng nhập vào
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(loginActivity.this, "Vui lòng nhập tên người dùng và mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            Call<User>call = userService.findCustomerByName(username);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        User user = response.body();

                        Log.d("User","User: "+user);
                        if(username.equals(user.getCustomerName()) &&password.equals(user.getCustommerpass())){
                            Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String userJSON = gson.toJson(user);
                            editor.putString("user",userJSON);
                            editor.apply();
                            Intent main = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(main);
                        }else {
                            Toast.makeText(getApplicationContext(),"sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("API","fail to call API");
                }
            });
            }
    }
}
