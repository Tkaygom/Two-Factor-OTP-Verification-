package com.assignment.twofactor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OTPVerificationActivity extends AppCompatActivity {

    private EditText etOTP;
    private Button btnVerifyOTP;

    private FirebaseAuth mAuth;
    private String verificationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        etOTP = findViewById(R.id.etOTP);
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP);

        mAuth = FirebaseAuth.getInstance();

        // Retrieve the verification ID from the intent
        verificationId = getIntent().getStringExtra("verificationId");

        btnVerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String otp = etOTP.getText().toString();

                // Validate input (add more validation as needed)
                if (otp.isEmpty()) {
                    Toast.makeText(OTPVerificationActivity.this, "Please enter the OTP", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verify the entered OTP
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login success
                        Intent intent = new Intent(OTPVerificationActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Close the OTPVerificationActivity
                    } else {
                        // If login fails, display a message to the user.
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(OTPVerificationActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OTPVerificationActivity.this, "Login failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
