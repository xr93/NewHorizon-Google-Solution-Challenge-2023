package com.ascent.newhorizon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth2;
    Button login;

    TextView reset;
    CheckBox check;
    private EditText emailText;
    private EditText passwordtext;

    private String email;
    private String password;

    private boolean done = false;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        emailText = findViewById(R.id.email);
        passwordtext = findViewById(R.id.password);
        check = findViewById(R.id.checkBox);
        reset = findViewById(R.id.forgotPass);

        auth2 = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            email = emailText.getText().toString().trim();
            password = passwordtext.getText().toString().trim();

            if (email.isEmpty()) {
                emailText.setError("Email is empty");
                emailText.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.setError("Enter the valid email");
                emailText.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                passwordtext.setError("Password is empty");
                passwordtext.requestFocus();
                return;
            }
            if (password.length() < 8) {
                passwordtext.setError("Length of password is more than 8");
                passwordtext.requestFocus();
                return;
            }


            auth2.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_SHORT).show();
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        // Email doesn't exist or has been disabled
                        Toast.makeText(Login.this, "Invalid email ", Toast.LENGTH_SHORT).show();
                    } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // Invalid password
                        Toast.makeText(Login.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    } else {
                        // Other error
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        check.setOnClickListener(v -> {
            if (check.isChecked()) {
                passwordtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                passwordtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordtext.setSelection(passwordtext.length());
        });

        reset.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),ResetPassword.class);
            startActivity(intent);
        });

    }
}
