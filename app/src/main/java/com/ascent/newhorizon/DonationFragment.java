package com.ascent.newhorizon;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class DonationFragment extends Fragment implements DonateAdapter.OnItemClickListener{

    List<String> donateText;
    List<String> bank;
    List<String> acc;
    List<String> holderacc;
    List<String> ref;
    List<String> reference1;
    List<String> reference2;

    List<String> donateBtn;

    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView = view.findViewById(R.id.donateRv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        donateText = new ArrayList<>();
        bank = new ArrayList<>();
        acc = new ArrayList<>();
        holderacc = new ArrayList<>();
        ref = new ArrayList<>();
        reference1 = new ArrayList<>();
        reference2 = new ArrayList<>();
        donateBtn = new ArrayList<>();

        donateText.add("If you would like to contribute directly, kindly make the donation according to the following details:");

        bank.add("Bank: SpringBank");

        holderacc.add("Account Name: TgtWithRefugees");

        acc.add("Account Number: 12345678");

        reference1.add("Reference 1: Z001 (Zarya Ahmed)");

        reference2.add("Reference 2: Your Phone Number");

        ref.add("Note: Please include your phone number as a reference so that we may contact you as every donation comes with a token of appreciation");

        donateBtn.add("Back To Blog Page");

        DonateAdapter donateAdapter = new DonateAdapter(getContext(),donateText,bank,acc,holderacc,ref,reference1,reference2, donateBtn,this);
        recyclerView.setAdapter(donateAdapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0) {
            Fragment blogFragment = new BlogFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            blogFragment.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new BlogFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
