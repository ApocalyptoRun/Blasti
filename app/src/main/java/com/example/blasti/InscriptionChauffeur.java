package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blasti.Model.Role;
import com.example.blasti.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class InscriptionChauffeur extends AppCompatActivity {

    EditText editTextNom, editTextPrenom, editTextCin, editTextNumeroTelephone, editTextEmail, editTextPassword, editTextConfirmPassword;
    Button btnSingUp;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_chauffeur);

        editTextNom = (EditText) findViewById(R.id.editTextTextPersonName6);
        editTextPrenom = (EditText) findViewById(R.id.editTextTextPersonName7);
        editTextCin = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextNumeroTelephone = (EditText) findViewById(R.id.editTextTextPersonName3);
        editTextEmail = (EditText) findViewById(R.id.editTextTextPersonName4);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPersonName5);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextTextPersonName8);
        btnSingUp = (Button) findViewById(R.id.button2);


        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerChauffeur();
            }
        });

    }


    private void registerChauffeur() {
      //  Toast.makeText(this, "registerChauffeur", Toast.LENGTH_SHORT).show();
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String cin = editTextCin.getText().toString().trim();
        String numeroTelephone = editTextNumeroTelephone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        Role role = Role.CHAUFFEUR;

        if (nom.isEmpty()) {
            editTextNom.setError("Nom est requis");
            editTextNom.requestFocus();
            return;
        }
        if (prenom.isEmpty()) {
            editTextPrenom.setError("Prenom est requis");
            editTextPrenom.requestFocus();
            return;
        }
        if (cin.isEmpty()) {
            editTextCin.setError("Cin est requis");
            editTextCin.requestFocus();
            return;
        }
        if (numeroTelephone.isEmpty()) {
            editTextNumeroTelephone.setError("Numero de telephone est requis");
            editTextNumeroTelephone.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email est requis");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Mot de passe est requis");
            editTextPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Confirmer le mot de passe est requis");
            editTextConfirmPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Les mots de passe ne sont pas identiques");
            editTextConfirmPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Le mot de passe doit contenir au moins 6 caractères");
            editTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Veuillez entrer un email valide");
            editTextEmail.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nom + " " + prenom)
                                .build();
                        currentUser.updateProfile(profileUpdates);

                        User user = new User(nom, prenom, cin, numeroTelephone, email, role);

                        firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid())
                                .set(user);

                        Toast.makeText(InscriptionChauffeur.this, "Utilisateur créer avec succées ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InscriptionChauffeur.this, SignIn.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(InscriptionChauffeur.this, "Erreur ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(InscriptionChauffeur.this, SignIn.class);
        startActivity(intent);
    }
}