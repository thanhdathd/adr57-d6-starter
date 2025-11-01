package com.adr57.datatransferstarter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.adr57.datatransferstarter.R;

public class SettingsFragment extends Fragment {

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

        // TODO: Nhận dữ liệu từ Arguments và hiển thị lên UI

        setupClickListeners(view);

        return view;
    }

    private void setupClickListeners(View view) {
        view.findViewById(R.id.btn_save_settings).setOnClickListener(v -> {
            // TODO: Lưu cài đặt và gửi kết quả về Activity
            // Sử dụng: Interface hoặc Fragment Result API
        });
    }
}