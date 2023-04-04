package com.ascent.newhorizon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OptionPage extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;

    private Button guestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        guestButton = findViewById(R.id.guest_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle login button click
                Intent intent = new Intent(OptionPage.this, Login.class);
                startActivity(intent);

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle register button click
                Intent intent = new Intent(OptionPage.this, Register.class);
                startActivity(intent);
            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(OptionPage.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Logging in as guest",Toast.LENGTH_SHORT).show();

            }
        });


    }
}