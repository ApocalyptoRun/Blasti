package com.example.blasti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class InscriptionClient extends AppCompatActivity implements View.OnClickListener{
    EditText editTextNom, editTextPrenom, editTextCin, editTextNumeroTelephone, editTextEmail, editTextPassword, editTextConfirmPassword;
    Button btnSingUp;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_client);

        editTextNom = (EditText) findViewById(R.id.nom);
        editTextPrenom = (EditText) findViewById(R.id.prenom);
        editTextCin = (EditText) findViewById(R.id.cin);
        editTextNumeroTelephone = (EditText) findViewById(R.id.numeroTelephone);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        btnSingUp = (Button) findViewById(R.id.signUp);

        btnSingUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUp:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String cin = editTextCin.getText().toString().trim();
        String numeroTelephone = editTextNumeroTelephone.getText().toString();
        Role role = Role.CLIENT;
        System.out.println(role);

        if (nom.isEmpty()){
            editTextNom.setError("Lastname is required");
            editTextNom.requestFocus();
            return;
        }

        if (prenom.isEmpty()){
            editTextPrenom.setError("Firstname is required");
            editTextPrenom.requestFocus();
            return;
        }

        if (cin.isEmpty()){
            editTextCin.setError("CIN is required");
            editTextCin.requestFocus();
            return;
        }

        if (numeroTelephone.isEmpty()){
            editTextNumeroTelephone.setError("Phone number is required");
            editTextNumeroTelephone.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()){
            editTextConfirmPassword.setError("Confirm Password is required");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!confirmPassword.equals(password)){
            editTextConfirmPassword.setError("Password is incorrect");
            editTextConfirmPassword.requestFocus();
            return;
        }

    /*    auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                        //    User user = new User(cin, nom, prenom, numeroTelephone, email, password, role);
                        }
                    }
                }); */
    }
}