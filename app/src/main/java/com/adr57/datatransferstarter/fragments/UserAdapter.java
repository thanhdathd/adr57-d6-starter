package com.adr57.datatransferstarter.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.adr57.datatransferstarter.R;
import com.adr57.datatransferstarter.model.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAvatar, tvUserName, tvUserEmail, tvUserAge;
        private CardView cardUser;
        LinearLayout llRoot;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.tv_avatar);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvUserEmail = itemView.findViewById(R.id.tv_user_email);
            tvUserAge = itemView.findViewById(R.id.tv_user_age);
            cardUser = itemView.findViewById(R.id.card_user);
            llRoot = itemView.findViewById(R.id.llRoot);
        }

        public void bind(final User user, final OnItemClickListener listener) {
            // Set avatar text (first letter of name)
            if (user.getName() != null && !user.getName().isEmpty()) {
                tvAvatar.setText(String.valueOf(user.getName().charAt(0)));
            }

            tvUserName.setText(user.getName());
            tvUserEmail.setText(user.getEmail());
            tvUserAge.setText("Age: " + user.getAge());

            llRoot.setOnClickListener(v -> {
                Log.i("list_user", "click ok ");
                if (listener != null) {
                    listener.onItemClick(user);
                }
            });
        }
    }
}