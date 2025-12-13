package com.example.studentregister;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentregister.data.entities.Address;
import com.example.studentregister.data.entities.Student;
import com.example.studentregister.data.entities.enums.SexEnum;
import com.example.studentregister.data.services.UsersService;
import com.example.studentregister.utils.AppNavigator;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtNacionalidade, edtNaturalidade, edtNumeroDocumento;

    private RadioGroup rgSexo, rgDocumento;

    private Button btnSalvar, btnVoltar;

    private UsersService usersService;

    private Integer studentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        this.usersService = UsersService.getInstance();
        goBack();
        initViews();
        initActions();
        checkEditMode();
    }

    private void initViews() {
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtNacionalidade = findViewById(R.id.edtNacionalidade);
        edtNaturalidade = findViewById(R.id.edtNaturalidade);
        edtNumeroDocumento = findViewById(R.id.edtNumeroDocumento);

        rgSexo = findViewById(R.id.rgSexo);
        rgDocumento = findViewById(R.id.rgDocumento);

        btnSalvar = findViewById(R.id.btnSalvar);
    }

    private void checkEditMode() {
        if (getIntent().hasExtra("STUDENT_ID")) {
            studentId = getIntent().getIntExtra("STUDENT_ID", -1);

            Student student = usersService.findById(studentId);

            if (student != null) {
                fillForm(student);
            }
        }
    }

    private void fillForm(Student student) {
        edtNome.setText(student.getName());
        edtCpf.setText(student.getCpf());
        edtNacionalidade.setText(student.getNationality());
        edtNaturalidade.setText(student.getNaturality());
        edtNumeroDocumento.setText(student.getDocumentNumber());

        if (student.getSex() == SexEnum.MALE) {
            rgSexo.check(R.id.rbMasculino);
        } else if (student.getSex() == SexEnum.FEMALE) {
            rgSexo.check(R.id.rbFeminino);
        }

        if ("RG".equalsIgnoreCase(student.getDocumentName())) {
            rgDocumento.check(R.id.rbRG);
        } else if ("CIN".equalsIgnoreCase(student.getDocumentName())) {
            rgDocumento.check(R.id.rbCIN);
        }
    }

    private void initActions() {
        btnSalvar.setOnClickListener(v -> sveStudent());
    }

    private void sveStudent() {

        if (!camposValidos()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        String nome = edtNome.getText().toString();
        String cpf = edtCpf.getText().toString();
        String nacionalidade = edtNacionalidade.getText().toString();
        String naturalidade = edtNaturalidade.getText().toString();
        String numeroDocumento = edtNumeroDocumento.getText().toString();

        SexEnum sexo = obterSexoSelecionado();
        String tipoDocumento = obterTipoDocumentoSelecionado();

        Address address = new Address("", "", "");

        Student student = new Student(
                studentId,
                nome,
                cpf,
                sexo,
                nacionalidade,
                naturalidade,
                tipoDocumento,
                numeroDocumento,
                address
        );

        if (studentId == null) {
            UsersService.getInstance().create(student);
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            UsersService.getInstance().update(student);
            Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private boolean camposValidos() {
        return !edtNome.getText().toString().isEmpty()
                && !edtCpf.getText().toString().isEmpty()
                && rgSexo.getCheckedRadioButtonId() != -1
                && rgDocumento.getCheckedRadioButtonId() != -1
                && !edtNumeroDocumento.getText().toString().isEmpty();
    }

    private SexEnum obterSexoSelecionado() {
        int selectedId = rgSexo.getCheckedRadioButtonId();
        RadioButton selected = findViewById(selectedId);

        if (selected.getText().toString().equalsIgnoreCase("Masculino")) {
            return SexEnum.MALE;
        } else {
            return SexEnum.FEMALE;
        }
    }

    private String obterTipoDocumentoSelecionado() {
        int selectedId = rgDocumento.getCheckedRadioButtonId();
        RadioButton selected = findViewById(selectedId);
        return selected.getText().toString();
    }

    private void goBack(){
        btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(action -> {
            AppNavigator.goTo(RegisterStudentActivity.this, MainActivity.class);
        });
    }
}
