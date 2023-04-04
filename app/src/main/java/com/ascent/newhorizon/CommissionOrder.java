package com.ascent.newhorizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

// Code adapted from GFG tutorial
// How to Create and Add Data to Firebase Firestore in Android?

public class CommissionOrder extends AppCompatActivity {

    private EditText orderName, orderPhoneNum, orderEmail;

    private Button submitForm;

    private String orderComName, orderComPhoneNum, orderComEmail;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_commission_form);

        db = FirebaseFirestore.getInstance();

        orderName = findViewById(R.id.orderName);
        orderPhoneNum = findViewById(R.id.orderPhoneNumber);
        orderEmail = findViewById(R.id.orderEmail);
        submitForm = findViewById(R.id.btnSubmit);

        submitForm.setOnClickListener(v -> {

            orderComName = orderName.getText().toString();
            orderComPhoneNum = orderPhoneNum.getText().toString();
            orderComEmail = orderEmail.getText().toString();

            if (TextUtils.isEmpty(orderComName)) {
                orderName.setError("Please enter your name");
            } else if (TextUtils.isEmpty(orderComPhoneNum)) {
                orderPhoneNum.setError("Please enter your phone number");
            } else if (TextUtils.isEmpty(orderComEmail)) {
                orderEmail.setError("Please enter your email");
            } else {
                addDataToFirestore(orderComName, orderComPhoneNum, orderComEmail);
            }
        });
    }

    private void addDataToFirestore(String orderName, String orderPhoneNum, String orderEmail) {
        CollectionReference dbCourses = db.collection("Comission");

       CommissionForm commissionForm = new CommissionForm(orderComName, orderComPhoneNum, orderComEmail);

        dbCourses.add(commissionForm).addOnSuccessListener(documentReference -> {

            Toast.makeText(CommissionOrder.this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(CommissionOrder.this, "Form Submission Failed \n" + e, Toast.LENGTH_SHORT).show();
        });
    }
}