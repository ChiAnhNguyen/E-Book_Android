package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button loginButton;
    Button backButton;
    private EditText usernameEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Ánh xạ các thành phần giao diện
        loginButton = findViewById(R.id.lgLogin);
        backButton = findViewById(R.id.lgBack);
        usernameEditText = findViewById(R.id.lgUsername);
        passwordEditText = findViewById(R.id.lgPassword);

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
    }
    private void loginUser() {
        // Lấy thông tin người dùng nhập vào
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Kiểm tra xem thông tin đăng nhập có hợp lệ không (ở đây là một ví dụ đơn giản)
        if (username.equals("admin") && password.equals("password")) {
            // Đăng nhập thành công, hiển thị thông báo và đóng Activity
            Toast.makeText(loginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Đăng nhập không thành công, hiển thị thông báo
            Toast.makeText(loginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}