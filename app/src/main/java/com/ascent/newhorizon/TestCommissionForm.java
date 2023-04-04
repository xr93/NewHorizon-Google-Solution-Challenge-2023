package com.ascent.newhorizon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nullable;

public class TestCommissionForm extends Fragment {

    private EditText orderName, orderPhoneNum, orderEmail;
    private Button submitForm;
    private String orderComName, orderComPhoneNum, orderComEmail;
    private FirebaseFirestore commission;

    public TestCommissionForm() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_commission_form, container, false);

        commission = FirebaseFirestore.getInstance();

        orderName = view.findViewById(R.id.orderName);
        orderPhoneNum = view.findViewById(R.id.orderPhoneNumber);
        orderEmail = view.findViewById(R.id.orderEmail);
        submitForm = view.findViewById(R.id.btnSubmit);

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

        return view;
    }

    private void addDataToFirestore(String orderName, String orderPhoneNum, String orderEmail) {
        CollectionReference commissionInterest = commission.collection("Comission");

        CommissionForm commissionForm = new CommissionForm(orderComName, orderComPhoneNum, orderComEmail);

        commissionInterest.add(commissionForm).addOnSuccessListener(documentReference -> {

            Toast.makeText(getActivity(), "Form Submitted Successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(getActivity(), "Form Submission Failed \n" + e, Toast.LENGTH_SHORT).show();
        });
    }
}
