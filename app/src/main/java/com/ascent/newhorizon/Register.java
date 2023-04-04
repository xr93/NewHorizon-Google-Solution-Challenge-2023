package com.ascent.newhorizon;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signup;
    private EditText textFirstName;
    private EditText textLastName;
    private EditText textUsername;
    private EditText textPassword;
    private EditText textEmail;
    private boolean submitted = false, validUser = false;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    private String firstName;

    private String lastName;

    private String username;

    private String password;
    private String email;




    private FirebaseFirestore db;

    private boolean validateFirstName(){
        String fname = textFirstName.getText().toString().trim();

        if (fname.isEmpty()){
            textFirstName.setError("Field can't be empty");
        } else{
            textFirstName.setError(null);
            return true;
        }

        return false;
    }

    private boolean validateLastName(){
        String lname = textLastName.getText().toString().trim();

        if (lname.isEmpty()){
            textLastName.setError("Field can't be empty");
        } else{
            textLastName.setError(null);
            return true;
        }

        return false;
    }

    private boolean validateEmail(){

        String email = textEmail.getText().toString().trim();

        if (email.isEmpty()){
            textEmail.setError("Field can't be empty");
        } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()){
            textEmail.setError("Please enter a valid email");
        } else {
            textEmail.setError(null);
            return true;
        }
        return false;
    }

    private boolean validatePassword(){
        String password = textPassword.getText().toString().trim();
        if (password.isEmpty()){
            textPassword.setError("Field can't be empty");
        } else if (password.length() < 8){
            textPassword.setError("Password must have at least 8 characters");
        } else {
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textFirstName = findViewById(R.id.first_name_edittext);
        textLastName = findViewById(R.id.last_name_edittext);
        textUsername = findViewById(R.id.username_edittext);
        textEmail = findViewById(R.id.email_edittext);
        textPassword = findViewById(R.id.password_edittext);
        signup = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        signup.setOnClickListener(v -> {
            firstName = textFirstName.getText().toString();
            lastName = textLastName.getText().toString();
            username = textUsername.getText().toString();
            password = textPassword.getText().toString().trim();
            email = textEmail.getText().toString().trim();

            submitted = true;
            if (!validateFirstName() || !validateLastName() || !validateEmail() || !validatePassword()) {
                Toast.makeText(Register.this, "Cannot create account", Toast.LENGTH_SHORT).show();
                return;
            }


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) //successfully created the user
                    {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        addDataToFirestore(firstName, lastName, username, password, email);

                        //Toast.makeText(Register.this, "Account created!", Toast.LENGTH_SHORT).show();
                    }  // Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

                private void updateUI(FirebaseUser user) {
                    if (user != null) {
                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    }
                }
            });
        });
    }

    private void addDataToFirestore(String fN, String lN, String uN, String pW, String email) {
        CollectionReference account = db.collection("Account");

        SignUpForm accountInfo = new SignUpForm(fN, lN, uN, pW, email);

        account.add(accountInfo).addOnSuccessListener(documentReference -> {

            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(Register.this, "Registration Failed\n" + e, Toast.LENGTH_SHORT).show();
        });
    }
}