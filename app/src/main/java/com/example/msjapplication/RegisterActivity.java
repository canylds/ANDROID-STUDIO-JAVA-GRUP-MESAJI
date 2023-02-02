package com.example.msjapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText register_email, register_password;
    Button register_login, register_signup;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);

        register_login = findViewById(R.id.register_login);
        register_signup = findViewById(R.id.register_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class
                ));
            }
        });

        register_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = register_email.getText().toString();
                String password = register_password.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Lütfen gerekli alanları doldurunuz", Toast.LENGTH_SHORT).show();
                }

                else if (password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Şifre 6 karakterden az olamaz", Toast.LENGTH_SHORT).show();
                }

                else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Kayıt başarısız", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}