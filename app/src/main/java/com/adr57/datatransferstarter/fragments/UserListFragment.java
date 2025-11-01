package com.adr57.datatransferstarter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adr57.datatransferstarter.R;
import com.adr57.datatransferstarter.interfaces.FragmentCommunication;
import com.adr57.datatransferstarter.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private TextView tvSelectedUser, tvUpdateCount, tvCurrentMethod;
    private View currentMethodIndicator;

    private Button btnUseInterface, btnUseViewModel, btnUseResultApi;

    // TODO: Khai báo Interface cho Fragment communication
    private FragmentCommunication communication;
    // TODO: Khai báo ViewModel nếu sử dụng
    // TODO: Khai báo FragmentResultListener nếu sử dụng

    private String currentMethod = "None";
    private int updateCount = 0;

    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        initViews(view);
        setupUserList();
        setupRecyclerView();
        setupMethodButtons();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        tvSelectedUser = view.findViewById(R.id.tv_selected_user);
        tvUpdateCount = view.findViewById(R.id.tv_update_count);
        tvCurrentMethod = view.findViewById(R.id.tv_current_method);
        currentMethodIndicator = view.findViewById(R.id.current_method_indicator);

        btnUseInterface = view.findViewById(R.id.btn_use_interface);
        btnUseViewModel = view.findViewById(R.id.btn_use_viewmodel);
        btnUseResultApi = view.findViewById(R.id.btn_use_result_api);
    }

    private void setupUserList() {
        userList = new ArrayList<>();
        userList.add(new User("John Doe", "john@example.com", 25));
        userList.add(new User("Jane Smith", "jane@example.com", 30));
        userList.add(new User("Bob Johnson", "bob@example.com", 35));
        userList.add(new User("Alice Brown", "alice@example.com", 28));
        userList.add(new User("Charlie Wilson", "charlie@example.com", 32));
    }

    private void setupRecyclerView() {
        userAdapter = new UserAdapter(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(userAdapter);

        // TODO: Xử lý click item - Chọn 1 trong các phương pháp dưới đây:
        // 1. Sử dụng Interface
        // 2. Sử dụng ViewModel
        // 3. Sử dụng Fragment Result API
        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                handleUserItemClick(user);
            }
        });
    }

    private void setupMethodButtons() {
        btnUseInterface.setOnClickListener(v -> setCurrentMethod("Interface"));
        btnUseViewModel.setOnClickListener(v -> setCurrentMethod("ViewModel"));
        btnUseResultApi.setOnClickListener(v -> setCurrentMethod("Fragment Result API"));
    }

    private void setCurrentMethod(String method) {
        currentMethod = method;
        tvCurrentMethod.setText(method);
        currentMethodIndicator.setVisibility(View.VISIBLE);

        // TODO: Thực hiện các thiết lập cần thiết cho từng phương pháp
        switch (method) {
            case "Interface":
                // Thiết lập cho Interface
                break;
            case "ViewModel":
                // Thiết lập cho ViewModel
                break;
            case "Fragment Result API":
                // Thiết lập cho Fragment Result API
                break;
        }
    }

    private void handleUserItemClick(User user) {
        // TODO: IMPLEMENT PHẦN NÀY - Chọn 1 trong 3 phương pháp
        Log.i("user_list:", "handleUserItemClick:  "+user.getName());
        switch (currentMethod) {
            case "Interface":
                // TODO: Gửi user data sang UserDetailFragment qua Interface
                if(communication != null) {
                    communication.onUserSelected(user);
                }
                break;
            case "ViewModel":
                // TODO: Gửi user data sang UserDetailFragment qua ViewModel
                break;
            case "Fragment Result API":
                // TODO: Gửi user data sang UserDetailFragment qua Fragment Result API
                break;
            default:
                // Hiển thị thông báo chọn phương pháp
                tvSelectedUser.setText("Please select a transfer method first!");
                return;
        }

        // Cập nhật UI
        tvSelectedUser.setText(user.getName() + " - " + user.getEmail());
    }

    public void onUserUpdated(User updatedUser) {
        // TODO: Phương thức này được gọi khi UserDetailFragment cập nhật user
        updateCount++;
        tvUpdateCount.setText(String.valueOf(updateCount));
        tvSelectedUser.setText(updatedUser.getName() + " (Updated)");

        // Cập nhật trong danh sách
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(updatedUser.getEmail())) {
                userList.set(i, updatedUser);
                userAdapter.notifyItemChanged(i);
                break;
            }
        }
    }

    // TODO: Khai báo Interface cho communication với Activity, hoặc có thể khai báo interface ra một file riêng
    // public interface OnUserSelectedListener {
    //     void onUserSelected(User user);
    // }

    // TODO: Override onAttach và thiết lập listener nếu sử dụng Interface

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            communication = (FragmentCommunication) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCommunication");
        }
    }


    // TODO: Thiết lập ViewModel nếu sử dụng

    // TODO: Thiết lập Fragment Result API nếu sử dụng
}