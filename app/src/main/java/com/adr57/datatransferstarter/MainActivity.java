package com.adr57.datatransferstarter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PROFILE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupFragments();
        setupClickListeners();
    }






    private void setupFragments() {
        // TODO: Khởi tạo và hiển thị UserListFragment
        // Sử dụng FragmentTransaction để add fragment
    }

    private void setupClickListeners() {
        findViewById(R.id.btn_go_to_second).setOnClickListener(v -> {
            // TODO: Chuyển sang SecondActivity và truyền dữ liệu qua Intent
            // Truyền: userName, userEmail, age
        });

        findViewById(R.id.btn_open_profile).setOnClickListener(v -> {
            // TODO: Mở ProfileActivity với startActivityForResult
            // Expect result: updated user name
        });

        findViewById(R.id.btn_launch_with_contract).setOnClickListener(v -> {
            // TODO: Sử dụng ActivityResultLauncher để mở SecondActivity
            // và nhận kết quả trả về
        });
    }

    // TODO: Implement onActivityResult để xử lý kết quả từ ProfileActivity

    // TODO: Tạo ActivityResultLauncher cho SecondActivity
}