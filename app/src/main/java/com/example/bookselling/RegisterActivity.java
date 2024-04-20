package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookselling.Model.User;
import com.example.bookselling.Service.MyRetrofit;
import com.example.bookselling.Service.ProductService;
import com.example.bookselling.Service.UserService;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    Button btnConfirmRegister;
    Button btnback;

    EditText nUsername, nPassword, nConfiPass,phone,email;
    TextInputLayout textInputLayout;
//    ProductService productService;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Lấy tham chiếu đến các EditText
        nUsername = findViewById(R.id.rgtUsername);
        nPassword = findViewById(R.id.rgtPassword);
        nConfiPass = findViewById(R.id.rgtConfirmPass);
        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        textInputLayout = findViewById(R.id.email_text);
        btnConfirmRegister = findViewById(R.id.rgtRegister);
        userService = MyRetrofit.getClient().create(UserService.class);
        // Thiết lập sự kiện onClick cho nút Register

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if(!isValidEmail(email)){
                    textInputLayout.setError("vui lòng nhập đúng định dạng email");
                }else{
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Thực hiện xử lý khi người dùng nhấn Đăng ký
                // Lấy dữ liệu từ EditText
//                String username = nUsername.getText().toString().trim();
//                Log.d("usename",username);
//                String password = nPassword.getText().toString().trim();
//                Log.d("pass",password);
//                String confirmPassword = nConfiPass.getText().toString().trim();
//                Log.d("confirmPass",confirmPassword);
//                String phone_num = phone.getText().toString().trim();
//                Log.d("phone",phone_num);
//                String email_regis = email.getText().toString().trim();
//                Log.d("email",email_regis);
//                User user = new User(username,password,phone_num,email_regis);
//                Log.d("use object",user.toString());
                // Kiểm tra xác nhận mật khẩu
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
        String phone_num = phone.getText().toString().trim();
        String email_regis = email.getText().toString().trim();
        User user = new User(username,password,email_regis,phone_num);
        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            // Hiển thị thông báo lỗi nếu mật khẩu không khớp
            Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(confirmPassword)){
            Call<Void>call = userService.addCustommer(user);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(getApplicationContext(),loginActivity.class);
                        startActivity(login);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Register Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.contains("@gmail.com");
    }


}
