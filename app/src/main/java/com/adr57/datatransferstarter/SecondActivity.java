package com.adr57.datatransferstarter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.adr57.datatransferstarter.fragments.SettingsFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_received_name, tv_received_email, tv_received_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        setupEdgePadding();
        mapping();
        // TODO: Nhận dữ liệu từ MainActivity qua Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");
        int age = intent.getIntExtra("age", 0);
        // và hiển thị lên TextView
        showToTextView(userName, userEmail, age);
        setupClickListeners();
    }

    private void showToTextView(String userName, String userEmail, int age){
        tv_received_name.setText(userName);
        tv_received_email.setText(userEmail);
        tv_received_age.setText(String.valueOf(age));
    }

    private void mapping(){
        tv_received_name = findViewById(R.id.tv_received_name);
        tv_received_email = findViewById(R.id.tv_received_email);
        tv_received_age = findViewById(R.id.tv_received_age);
    }

    private void setupClickListeners() {
        findViewById(R.id.btn_send_back).setOnClickListener(v -> {
            // TODO: Gửi dữ liệu trở lại MainActivity
            // Sử dụng setResult và finish()
            Intent intent = new Intent();
            TextInputEditText textInputEditText = findViewById(R.id.et_response_message);
            String messageResponse = Objects.requireNonNull(textInputEditText.getText()).toString();
            intent.putExtra("responseMessage", messageResponse);
            setResult(RESULT_OK, intent);
            finish();
        });

        findViewById(R.id.btn_open_settings_fragment).setOnClickListener(v -> {
            // TODO: Mở SettingsFragment và truyền dữ liệu qua Arguments
            SettingsFragment settingsFragment = new SettingsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("THEME", "Light");
            bundle.putBoolean("NOTIFICATIONS", true);
            settingsFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_second, settingsFragment)
                    .addToBackStack(null)
                    .commit();

        });

        findViewById(R.id.btn_send_back_cancel).setOnClickListener(v ->{
            finish();
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