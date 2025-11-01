package com.adr57.datatransferstarter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.adr57.datatransferstarter.R;
import com.adr57.datatransferstarter.model.User;

public class UserDetailFragment extends Fragment {

    private static final String ARG_USER = "user";

    private TextView tvName, tvEmail, tvAge;

    public UserDetailFragment() {
        // Required empty public constructor
    }

    public static UserDetailFragment newInstance(User user) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        // TODO: Put user object to arguments
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvAge = view.findViewById(R.id.tv_age);

        // TODO: Nhận dữ liệu user từ Arguments và hiển thị

        setupClickListeners(view);

        return view;
    }

    private void setupClickListeners(View view) {
        view.findViewById(R.id.btn_update_user).setOnClickListener(v -> {
            // TODO: Cập nhật thông tin user và thông báo cho UserListFragment
            // Sử dụng: ViewModel hoặc Fragment Result API
        });
    }

    public void onUserReceived(User user) {
        if ( user != null) {
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvAge.setText(String.valueOf(user.getAge()));
        }
    }
}