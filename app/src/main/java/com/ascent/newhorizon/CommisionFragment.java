package com.ascent.newhorizon;

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


public class CommisionFragment extends Fragment implements ComAdapter.OnItemClickListener{

    List<String> comName;
    List<String> comBuddy;
    List<Integer> comImage;
    List<String> comRate;
    List<String> comDesc;
    List<String> comBtn;

    LayoutInflater comInflater;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View comView = inflater.inflate(R.layout.fragment_commision, container, false);

        RecyclerView ComRecyclerView = comView.findViewById(R.id.commissionRv);
        ComRecyclerView.setHasFixedSize(true);
        ComRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        comName = new ArrayList<>();
        comBuddy = new ArrayList<>();
        comImage = new ArrayList<>();
        comRate = new ArrayList<>();
        comDesc = new ArrayList<>();
        comBtn = new ArrayList<>();


        comName.add("Zarya Ahmed (@Z001)");

        comBuddy.add("Buddy: Skye Lee (@S001)");

        comImage.add(R.drawable.tailor);

        comRate.add("Current Rate: RM10/piece");

        comDesc.add("Hi there! I am Zarya. I specialize in hand sewn products such as scrunchies.");

        comBtn.add("Find out more");

        ComAdapter comAdapter =  new ComAdapter(getContext(),comName, comBuddy, comRate, comDesc, comBtn,comImage,this) ;
        ComRecyclerView.setAdapter(comAdapter);

        return comView;
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0) {
            Fragment commissionForm = new TestCommissionForm();
            Bundle args = new Bundle();
            args.putInt("position", position);
            commissionForm.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new TestCommissionForm())
                    .addToBackStack(null)
                    .commit();
        }
    }
}