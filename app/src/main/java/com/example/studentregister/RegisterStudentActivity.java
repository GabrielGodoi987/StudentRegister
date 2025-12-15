package com.example.studentregister;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentregister.data.entities.Address;
import com.example.studentregister.data.entities.ContactData;
import com.example.studentregister.data.entities.EmergencyContact;
import com.example.studentregister.data.entities.Student;
import com.example.studentregister.data.entities.UnitData;
import com.example.studentregister.data.entities.enums.SexEnum;
import com.example.studentregister.data.services.UsersService;
import com.example.studentregister.utils.AppNavigator;

public class RegisterStudentActivity extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtNacionalidade, edtNaturalidade, edtNumeroDocumento;

    private EditText edtCep, edtTipoLogradouro, edtLogradouro, edtNumero,
            edtComplemento, edtBairro, edtCidade, edtEstado, edtPais;

    private EditText edtTelefoneResidencial, edtTelefoneComercial, edtCelular,
            edtEmailInstitucional, edtEmailPessoal,
            edtEmergenciaNome, edtEmergenciaRelacionamento, edtEmergenciaTelefone;

    private EditText edtNumeroUnidade, edtNomeUnidade, edtRa, edtCurso,
            edtSemestreAtual, edtPeriodoCurso, edtIngresso;

    private RadioGroup rgSexo, rgDocumento;

    private Button btnSalvar, btnVoltar;

    private UsersService usersService;

    private Integer studentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        usersService = UsersService.getInstance();
        initViews();
        initActions();
        goBack();
        checkEditMode();
    }

    private void initViews() {
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtNacionalidade = findViewById(R.id.edtNacionalidade);
        edtNaturalidade = findViewById(R.id.edtNaturalidade);
        edtNumeroDocumento = findViewById(R.id.edtNumeroDocumento);

        edtCep = findViewById(R.id.edtCep);
        edtTipoLogradouro = findViewById(R.id.edtTipoLogradouro);
        edtLogradouro = findViewById(R.id.edtLogradouro);
        edtNumero = findViewById(R.id.edtNumero);
        edtComplemento = findViewById(R.id.edtComplemento);
        edtBairro = findViewById(R.id.edtBairro);
        edtCidade = findViewById(R.id.edtCidade);
        edtEstado = findViewById(R.id.edtEstado);
        edtPais = findViewById(R.id.edtPais);

        edtTelefoneResidencial = findViewById(R.id.edtTelefoneResidencial);
        edtTelefoneComercial = findViewById(R.id.edtTelefoneComercial);
        edtCelular = findViewById(R.id.edtCelular);
        edtEmailInstitucional = findViewById(R.id.edtEmailInstitucional);
        edtEmailPessoal = findViewById(R.id.edtEmailPessoal);
        edtEmergenciaNome = findViewById(R.id.edtEmergenciaNome);
        edtEmergenciaRelacionamento = findViewById(R.id.edtEmergenciaRelacionamento);
        edtEmergenciaTelefone = findViewById(R.id.edtEmergenciaTelefone);

        edtNumeroUnidade = findViewById(R.id.edtNumeroUnidade);
        edtNomeUnidade = findViewById(R.id.edtNomeUnidade);
        edtRa = findViewById(R.id.edtRa);
        edtCurso = findViewById(R.id.edtCurso);
        edtSemestreAtual = findViewById(R.id.edtSemestreAtual);
        edtPeriodoCurso = findViewById(R.id.edtPeriodoCurso);
        edtIngresso = findViewById(R.id.edtIngresso);

        rgSexo = findViewById(R.id.rgSexo);
        rgDocumento = findViewById(R.id.rgDocumento);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void initActions() {
        btnSalvar.setOnClickListener(v -> saveStudent());
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

        if (student.getSex() == SexEnum.MALE) rgSexo.check(R.id.rbMasculino);
        else rgSexo.check(R.id.rbFeminino);

        if ("RG".equalsIgnoreCase(student.getDocumentName())) rgDocumento.check(R.id.rbRG);
        else rgDocumento.check(R.id.rbCIN);

        Address a = student.getAddress();
        if (a != null) {
            edtCep.setText(a.getCep());
            edtTipoLogradouro.setText(a.getStreetType());
            edtLogradouro.setText(a.getStreet());
            edtNumero.setText(a.getNumber());
            edtComplemento.setText(a.getComplement());
            edtBairro.setText(a.getNeighborhood());
            edtCidade.setText(a.getCity());
            edtEstado.setText(a.getState());
            edtPais.setText(a.getCountry());
        }

        ContactData c = student.getContactData();
        if (c != null) {
            edtTelefoneResidencial.setText(c.getHomePhone());
            edtTelefoneComercial.setText(c.getWorkPhone());
            edtCelular.setText(c.getMobilePhone());
            edtEmailInstitucional.setText(c.getInstitutionalEmail());
            edtEmailPessoal.setText(c.getPersonalEmail());

            EmergencyContact e = c.getEmergencyContact();
            if (e != null) {
                edtEmergenciaNome.setText(e.getName());
                edtEmergenciaRelacionamento.setText(e.getRelationship());
                edtEmergenciaTelefone.setText(e.getMobilePhone());
            }
        }

        UnitData u = student.getUnitData();
        if (u != null) {
            edtNumeroUnidade.setText(u.getUnitNumber());
            edtNomeUnidade.setText(u.getUnitName());
            edtRa.setText(u.getRa());
            edtCurso.setText(u.getCourse());
            edtSemestreAtual.setText(u.getCurrentSemester());
            edtPeriodoCurso.setText(u.getCoursePeriod());
            edtIngresso.setText(u.getEntrySemesterYear());
        }
    }

    private void saveStudent() {
        if (!camposValidos()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        Address address = new Address(
                edtCep.getText().toString(),
                edtTipoLogradouro.getText().toString(),
                edtLogradouro.getText().toString(),
                edtNumero.getText().toString(),
                edtComplemento.getText().toString(),
                edtBairro.getText().toString(),
                edtCidade.getText().toString(),
                edtEstado.getText().toString(),
                edtPais.getText().toString()
        );

        EmergencyContact emergencyContact = new EmergencyContact(
                edtEmergenciaNome.getText().toString(),
                edtEmergenciaRelacionamento.getText().toString(),
                edtEmergenciaTelefone.getText().toString()
        );

        ContactData contactData = new ContactData(
                edtTelefoneResidencial.getText().toString(),
                edtTelefoneComercial.getText().toString(),
                edtCelular.getText().toString(),
                edtEmailInstitucional.getText().toString(),
                edtEmailPessoal.getText().toString(),
                emergencyContact
        );

        UnitData unitData = new UnitData(
                edtNumeroUnidade.getText().toString(),
                edtNomeUnidade.getText().toString(),
                edtRa.getText().toString(),
                edtCurso.getText().toString(),
                edtSemestreAtual.getText().toString(),
                edtPeriodoCurso.getText().toString(),
                edtIngresso.getText().toString()
        );

        Student student = new Student(
                studentId,
                edtNome.getText().toString(),
                edtCpf.getText().toString(),
                obterSexoSelecionado(),
                edtNacionalidade.getText().toString(),
                edtNaturalidade.getText().toString(),
                obterTipoDocumentoSelecionado(),
                edtNumeroDocumento.getText().toString(),
                address,
                contactData,
                unitData
        );

        if (studentId == null) {
            usersService.create(student);
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            usersService.update(student);
            Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private boolean camposValidos() {
        return !edtNome.getText().toString().isEmpty()
                && !edtCpf.getText().toString().isEmpty()
                && rgSexo.getCheckedRadioButtonId() != -1
                && rgDocumento.getCheckedRadioButtonId() != -1
                && !edtNumeroDocumento.getText().toString().isEmpty()
                && !edtCelular.getText().toString().isEmpty()
                && !edtRa.getText().toString().isEmpty();
    }

    private SexEnum obterSexoSelecionado() {
        RadioButton rb = findViewById(rgSexo.getCheckedRadioButtonId());
        return rb.getText().toString().equalsIgnoreCase("Masculino")
                ? SexEnum.MALE
                : SexEnum.FEMALE;
    }

    private String obterTipoDocumentoSelecionado() {
        RadioButton rb = findViewById(rgDocumento.getCheckedRadioButtonId());
        return rb.getText().toString();
    }

    private void goBack() {
        btnVoltar.setOnClickListener(v ->
                AppNavigator.goTo(RegisterStudentActivity.this, MainActivity.class)
        );
    }
}
