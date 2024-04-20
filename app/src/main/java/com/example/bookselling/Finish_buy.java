package com.example.bookselling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finish_buy extends AppCompatActivity {
    private TextView loiCamON;
    private Button btnmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_buy);
        loiCamON = findViewById(R.id.Thanks);
        btnmain = findViewById(R.id.backToMain);

        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main);
            }
        });

    }
}