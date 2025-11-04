package com.adr57.datatransferstarter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity {

    TextInputEditText et_response_message;
    RatingBar rating_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        setupEdgePadding();

        // TODO: Nhận dữ liệu từ MainActivity qua Intent
        // và hiển thị lên TextView
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        String email = intent.getStringExtra("userEmail");
        int age = intent.getIntExtra("age", 0);

        TextView tv_received_name = findViewById(R.id.tv_received_name);
        TextView tv_received_email = findViewById(R.id.tv_received_email);
        TextView tv_received_age = findViewById(R.id.tv_received_age);

        tv_received_name.setText(name);
        tv_received_email.setText(email);
        tv_received_age.setText(String.valueOf(age));

        et_response_message = findViewById(R.id.et_response_message);
        rating_bar = findViewById(R.id.rating_bar);
        setupClickListeners();
    }

    private void setupClickListeners() {
        findViewById(R.id.btn_send_back).setOnClickListener(v -> {
            // TODO: Gửi dữ liệu trở lại MainActivity
            // Sử dụng setResult và finish()
            Intent intent = new Intent();
            String response_message = et_response_message.getText().toString();
            float rating = rating_bar.getRating();
            intent.putExtra("response_message", response_message);
            intent.putExtra("rating", rating);
            setResult(RESULT_OK, intent);
            finish();
        });

        findViewById(R.id.btn_send_back_cancel).setOnClickListener(v -> {
            finish();
        });
        findViewById(R.id.btn_open_settings_fragment).setOnClickListener(v -> {
            // TODO: Mở SettingsFragment và truyền dữ liệu qua Arguments
        });
    }

    private void setupEdgePadding() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}