package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btnConfirmRegister;
    Button btnback;

    EditText nUsername, nPassword, nConfiPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Lấy tham chiếu đến các EditText
        nUsername = findViewById(R.id.rgtUsername);
        nPassword = findViewById(R.id.rgtPassword);
        nConfiPass = findViewById(R.id.rgtConfirmPass);

        // Thiết lập sự kiện onClick cho nút Register
        btnConfirmRegister = findViewById(R.id.rgtRegister);
        btnConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện xử lý khi người dùng nhấn Đăng ký
                registerUser();
            }
        });

        // Thiết lập sự kiện cho nút Back
        btnback = findViewById(R.id.rgtBack);
        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        //thiết lập sự kiện cho nút login
        Button btnLogin = findViewById(R.id.rgtLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Intent = new Intent(RegisterActivity.this, loginActivity.class);
                startActivity(Intent);
            }
        });
    }
    private void registerUser() {
        // Lấy dữ liệu từ EditText
        String username = nUsername.getText().toString().trim();
        String password = nPassword.getText().toString().trim();
        String confirmPassword = nConfiPass.getText().toString().trim();

        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            // Hiển thị thông báo lỗi nếu mật khẩu không khớp
            Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thực hiện xử lý đăng ký người dùng ở đây
        // Ví dụ: Lưu thông tin người dùng vào cơ sở dữ liệu hoặc gửi yêu cầu đăng ký đến máy chủ

        // Hiển thị thông báo xác nhận
        Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        // Tiếp tục xử lý kết nối với cơ sở dữ liệu hoặc API, hoặc chuyển hướng người dùng.
    }
}