package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blasti.Model.Role;
import com.example.blasti.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class InscriptionClient extends AppCompatActivity implements View.OnClickListener {
    EditText editTextNom, editTextPrenom, editTextCin, editTextNumeroTelephone, editTextEmail, editTextPassword, editTextConfirmPassword;
    Button btnSingUp;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

        if (nom.isEmpty()) {
            editTextNom.setError("Nom obligatoire");
            editTextNom.requestFocus();
            return;
        }

        if (prenom.isEmpty()) {
            editTextPrenom.setError("Prenom obligatoire");
            editTextPrenom.requestFocus();
            return;
        }

        if (cin.isEmpty()) {
            editTextCin.setError("Cin obligatoire");
            editTextCin.requestFocus();
            return;
        }

        if (numeroTelephone.isEmpty()) {
            editTextNumeroTelephone.setError("Numero de telephone obligatoire");
            editTextNumeroTelephone.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email invalide");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Mot de passe obligatoire");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Confirmer le mot de passe");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!confirmPassword.equals(password)) {
            editTextConfirmPassword.setError("Mot de passe non identique");
            editTextConfirmPassword.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(cin, nom, prenom, numeroTelephone, email, role);
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(user);
                    Toast.makeText(InscriptionClient.this, "Utilisateur créer avec succées", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InscriptionClient.this, SignIn.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionClient.this);
                    builder.setMessage(task.getException().getMessage())
                            .setTitle("Error")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

    public void goToLogin(View view) {
        Intent intent = new Intent(InscriptionClient.this, SignIn.class);
        startActivity(intent);
    }
}