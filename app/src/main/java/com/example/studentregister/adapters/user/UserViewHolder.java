package com.example.studentregister.adapters.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregister.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView txtUserName;
    public ImageView btnEdit;
    public ImageView btnDelete;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        txtUserName = itemView.findViewById(R.id.txtUserName);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
