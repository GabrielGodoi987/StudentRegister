package com.example.studentregister;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studentregister.utils.AppNavigator;

public class MainActivity extends AppCompatActivity {

    Button btnCadastro, btnListagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnListagem = findViewById(R.id.btnListagem);

        btnCadastro.setOnClickListener(action -> {
            AppNavigator.goTo(MainActivity.this, RegisterStudentActivity.class);
        });

        btnListagem.setOnClickListener(action -> {
            AppNavigator.goTo(MainActivity.this, ListUsersActivity.class);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}