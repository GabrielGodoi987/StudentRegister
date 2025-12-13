package com.example.studentregister;

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

        usersService = UsersService.getInstance();

        rvUsers = findViewById(R.id.rvUsers);
        btnVoltar = findViewById(R.id.btnVoltar);

        setupRecyclerView();
        setupActions();
    }

    private void setupRecyclerView() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(
                usersService.findAll(),
                new UserAdapter.OnUserActionListener() {

                    @Override
                    public void onEdit(Student student) {

                        if (student.getId() == null) {
                            Toast.makeText(
                                    ListUsersActivity.this,
                                    "Aluno sem ID, não é possível editar",
                                    Toast.LENGTH_SHORT
                            ).show();
                            return;
                        }

                        AppNavigator.goToWithParams(
                                ListUsersActivity.this,
                                RegisterStudentActivity.class,
                                "STUDENT_ID",
                                student.getId()
                        );
                    }

                    @Override
                    public void onDelete(Student student) {
                        usersService.delete(student.getId());
                        adapter.updateList(usersService.findAll());

                        Toast.makeText(
                                ListUsersActivity.this,
                                "Aluno removido",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        rvUsers.setAdapter(adapter);
    }

    private void setupActions() {
        btnVoltar.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.updateList(usersService.findAll());
        }
    }
}
