package com.adr57.datatransferstarter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.adr57.datatransferstarter.R;

public class SettingsFragment extends Fragment {

    private TextView tv_received_theme, tv_received_notifications;

    public SettingsFragment() {
        // Required empty public constructor
    }

    // Explain: newInstance là một factory method để tạo đối tượng SettingsFragment với dữ liệu được truyền vào.
    // thay đổi tham số của hàm newInstance để phù hợp với dữ liệu cần truyền vào.
    public static SettingsFragment newInstance(String theme, boolean notifications) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        // TODO: đẩy data vào bundle (theme và notifications)

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mapping(view);
        // TODO: Nhận dữ liệu từ Arguments và hiển thị lên UI
        Bundle bundle = getArguments();
        if (bundle != null){
            String theme = bundle.getString("THEME", "Auto");
            Boolean notifications = bundle.getBoolean("NOTIFICATIONS", false);
            tv_received_theme.setText(theme);
            tv_received_notifications.setText(notifications.toString());
        }

        setupClickListeners(view);

        return view;
    }

    private void setupClickListeners(View view) {
        view.findViewById(R.id.btn_save_settings).setOnClickListener(v -> {
            // TODO: Lưu cài đặt và gửi kết quả về Activity
            // Sử dụng: Interface hoặc Fragment Result API
        });
    }

    private void mapping(View view){
        tv_received_theme = view.findViewById(R.id.tv_received_theme);
        tv_received_notifications = view.findViewById(R.id.tv_received_notifications);
    }
}