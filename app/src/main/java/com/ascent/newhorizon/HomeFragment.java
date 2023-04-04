package com.ascent.newhorizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClickListener {

    List<String> featureTitle;
    List<String> featureBtn;
    List<String> featureDesc;

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        featureTitle = new ArrayList<>();
        featureBtn = new ArrayList<>();
        featureDesc = new ArrayList<>();

        featureTitle.add("Product Catalogue");
        featureTitle.add("Commission Space");
        featureTitle.add("Journey Story Time");

        featureBtn.add("Browse Products");
        featureBtn.add("Find out More");
        featureBtn.add("Read More");

        featureDesc.add("Go through a wide range of handcrafted products, " +
                "specially made by the community :)");
        featureDesc.add("Interested in getting a unique piece? \nHave a look at what the community has to offer!");
        featureDesc.add("Gain insights on the life of a refugee,\nfrom their journey to their daily lives.");

        HomeAdapter homeAdapter =  new HomeAdapter(getContext(),featureTitle, featureDesc, featureBtn,this);
        recyclerView.setAdapter(homeAdapter);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        TextView userEmail = view.findViewById(R.id.userEmail);


       if (currentUser != null) {
            String email = currentUser.getEmail();
            userEmail.setText("Currently logged in: " + email);
        } else {
            userEmail.setText("You are currently logged in as guest");
          //  userEmail.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                Fragment productFragment = new ProductFragment();
                Bundle args = new Bundle();
                args.putInt("position", position);
                productFragment.setArguments(args);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ProductFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case 1:
                Fragment commisionFragment = new CommisionFragment();
                Bundle args1 = new Bundle();
                args1.putInt("position", position);
                commisionFragment.setArguments(args1);
                FragmentManager fragmentManager1 = requireActivity().getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.container, new CommisionFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case 2:
                Fragment blogFragment = new BlogFragment();
                Bundle args2 = new Bundle();
                args2.putInt("position", position);
                blogFragment.setArguments(args2);
                FragmentManager fragmentManager2 = requireActivity().getSupportFragmentManager();
                fragmentManager2.beginTransaction()
                        .replace(R.id.container, new BlogFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}


