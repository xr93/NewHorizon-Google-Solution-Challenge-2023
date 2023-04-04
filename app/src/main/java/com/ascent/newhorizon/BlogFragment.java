package com.ascent.newhorizon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment implements AdapterOne.OnItemClickListener {
    List<String> blogName;
    List<String> blogLoc;
    List<String> blogDesc;

    List<String> blogBtn;
    List<Integer> blogImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.blogRv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        blogName = new ArrayList<>();
        blogLoc = new ArrayList<>();
        blogImg = new ArrayList<>();
        blogDesc = new ArrayList<>();
        blogBtn = new ArrayList<>();

        blogName.add("Zarya Ahmed (@Z001)");

        blogLoc.add("Country of Origin: Myanmar");

        blogDesc.add("Hi, I am Zarya. I was on born in a small village in the Rakhine State of Myanmar. " +
                "I left my home in December 2009 and arrived in Malaysia...");

        blogBtn.add("Make a donation");

        blogImg.add(R.drawable.refugee);

        AdapterOne adapterOne = new AdapterOne(getContext(), blogName, blogLoc, blogDesc, blogBtn, blogImg, this);
        recyclerView.setAdapter(adapterOne);

        return view;
    }


    @Override
    public void onItemClick(int adapterPosition) {
        switch (adapterPosition){
            case 0:
                Fragment donationFragment = new DonationFragment();
                Bundle args = new Bundle();
                args.putInt("position",adapterPosition);
                donationFragment.setArguments(args);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,new DonationFragment()).addToBackStack(null).commit();

                break;
        }
    }

    @Override
    public void OnItemClick(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getContext(),CommissionOrder.class);
                startActivity(intent);
                break;
        }
    }
}
