package com.example.studentregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregister.adapters.user.UserAdapter;
import com.example.studentregister.data.entities.Student;
import com.example.studentregister.data.services.UsersService;
import com.example.studentregister.utils.AppNavigator;

import java.util.List;

public class ListUsersActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private UserAdapter adapter;
    private UsersService usersService;

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_users);

        rvUsers = findViewById(R.id.rvUsers);
        usersService = UsersService.getInstance();

        goBack();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<Student> students = usersService.findAll();

        adapter = new UserAdapter(students, new UserAdapter.OnUserActionListener() {
            @Override
            public void onEdit(Student student) {
                Intent intent = new Intent(ListUsersActivity.this, RegisterStudentActivity.class);
                intent.putExtra("STUDENT_ID", student.getId());
                startActivity(intent);
            }

            @Override
            public void onDelete(Student student) {
                usersService.delete(student.getId());
                adapter.updateList(usersService.findAll());
                Toast.makeText(ListUsersActivity.this, "Aluno removido", Toast.LENGTH_SHORT).show();
            }
        });

        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(usersService.findAll());
    }

    private void goBack(){
        btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(action -> {
            AppNavigator.goTo(ListUsersActivity.this, MainActivity.class);
        });
    }
}
