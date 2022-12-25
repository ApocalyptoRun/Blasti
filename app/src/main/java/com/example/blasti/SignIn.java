package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button btnSignIn;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextNumberPassword);
        btnSignIn = (Button) findViewById(R.id.button3);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
        Toast.makeText(this, "signIn", Toast.LENGTH_SHORT).show();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email obligatoire");
            editTextEmail.requestFocus();
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

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                
                firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        String role = task1.getResult().getString("role");
                        System.out.println(role);
                        if (role.equals("CHAUFFEUR")) {
                            Intent intent = new Intent(SignIn.this, AccueilChauffeur.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else if (role.equals("CLIENT")) {
                            Intent intent = new Intent(SignIn.this, AccueilClient.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                });
                
            } else {
                Toast.makeText(SignIn.this, " Vérifier tes corrdonnées !", Toast.LENGTH_LONG).show();
            }
        });
    }




    public void GoToMain(View view) {
        Intent intent = new Intent(this, Signupchoix.class);
        startActivity(intent);
    }
}