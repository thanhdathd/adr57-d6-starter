package com.adr57.datatransferstarter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        setupEdgePadding();

        // TODO: Nhận dữ liệu từ MainActivity qua Intent
        // và hiển thị lên TextView

        setupClickListeners();
    }

    private void setupClickListeners() {
        findViewById(R.id.btn_send_back).setOnClickListener(v -> {
            // TODO: Gửi dữ liệu trở lại MainActivity
            // Sử dụng setResult và finish()
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