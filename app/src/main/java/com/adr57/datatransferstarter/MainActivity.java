package com.adr57.datatransferstarter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.adr57.datatransferstarter.fragments.UserAdapter;
import com.adr57.datatransferstarter.fragments.UserDetailFragment;
import com.adr57.datatransferstarter.fragments.UserListFragment;
import com.adr57.datatransferstarter.interfaces.FragmentCommunication;
import com.adr57.datatransferstarter.model.User;

public class MainActivity extends AppCompatActivity implements FragmentCommunication{
    private static final int REQUEST_CODE_PROFILE = 100;

    private ActivityResultLauncher<Intent> activityResultLauncher;

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

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String response_message = data.getStringExtra("response_message");
                            float rating = data.getFloatExtra("rating", 0.0f);

                            String toastString = "Rated " + rating + " stars";
                            if (response_message != null && !response_message.isEmpty()) {
                                toastString += " with message: " + response_message;
                            }

                            Log.d("MainActivity", "Response message: " + response_message);
                            Log.d("MainActivity", "Rating: " + rating);
                            Toast.makeText(this, toastString,
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        setupFragments();
        setupClickListeners();
    }






    private void setupFragments() {
        // TODO: Khởi tạo và hiển thị UserListFragment
        // Sử dụng FragmentTransaction để add fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, UserListFragment.newInstance())
                .addToBackStack("user_list")
                .commit();
    }

    private void setupClickListeners() {
        findViewById(R.id.btn_go_to_second).setOnClickListener(v -> {
            // TODO: Chuyển sang SecondActivity và truyền dữ liệu qua Intent
            // Truyền: userName, userEmail, age
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("userName", "John Doe");
            intent.putExtra("userEmail", "johndoe@email.com");
            intent.putExtra("age", 20);
            startActivity(intent);
        });

        findViewById(R.id.btn_open_profile).setOnClickListener(v -> {
            // TODO: Mở ProfileActivity với startActivityForResult
            // Expect result: updated user name
        });

        findViewById(R.id.btn_launch_with_contract).setOnClickListener(v -> {
            // TODO: Sử dụng ActivityResultLauncher để mở SecondActivity
            // và nhận kết quả trả về

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            activityResultLauncher.launch(intent);
        });
    }

    @Override
    public void onMessageSent(String message) {

    }

    @Override
    public void onUserSelected(User user) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        UserDetailFragment userDetailFragment = new UserDetailFragment();
        transaction.replace(R.id.fragment_container, userDetailFragment);
        transaction.addToBackStack("user_detail");
        transaction.commit();

        // post delay
        new Handler(getMainLooper()).postDelayed(() -> {
            userDetailFragment.onUserReceived(user);
        }, 500);
    }

    @Override
    public String onRequestData() {
        return "";
    }

    // TODO: Implement onActivityResult để xử lý kết quả từ ProfileActivity

    // TODO: Tạo ActivityResultLauncher cho SecondActivity
}