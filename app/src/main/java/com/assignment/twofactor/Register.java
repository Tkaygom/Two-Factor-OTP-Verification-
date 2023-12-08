// Register.java
package com.assignment.twofactor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                // Validate input (add more validation as needed)
                if (username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() ||
                        password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register user with Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, task1 -> {
                            if (task1.isSuccessful()) {
                                // Send Email Verification
                                Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(task2 -> {
                                    if(task2.isSuccessful()){
                                        // Registration success
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Store user data in Firebase Realtime Database
                                        if (user != null) {
                                            String userId = user.getUid();
                                            User userData = new User(username, email, phoneNumber);

                                            // Save user data under the user's UID in the "users" node
                                            databaseReference.child(userId).setValue(userData);

                                            // Add the following lines to navigate to the Login activity
                                            Intent intent = new Intent(Register.this, Login.class);
                                            startActivity(intent);
                                            finish(); // Close the Register activity
                                        }
                                        // Display a success message
                                        Toast.makeText(Register.this, "Registration successful. Please verify your email", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Register.this, Objects.requireNonNull(task2.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // If registration fails, display a message to the user.
                                Toast.makeText(Register.this, "Registration failed. " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
