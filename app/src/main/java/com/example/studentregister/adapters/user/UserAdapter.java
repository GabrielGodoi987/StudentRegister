package com.example.studentregister.adapters.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregister.R;
import com.example.studentregister.data.entities.Student;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<Student> students;
    private final OnUserActionListener listener;

    public interface OnUserActionListener {
        void onEdit(Student student);
        void onDelete(Student student);
    }

    public UserAdapter(List<Student> students, OnUserActionListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Student student = students.get(position);

        holder.txtUserName.setText(student.getName());

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(student);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateList(List<Student> newList) {
        this.students = newList;
        notifyDataSetChanged();
    }
}
