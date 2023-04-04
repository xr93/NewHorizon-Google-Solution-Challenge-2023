package com.ascent.newhorizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    RecyclerView productList;
    List<String> title;
    List<String> maker;
    List<Integer> image;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        productList = view.findViewById(R.id.productRv);
        title = new ArrayList<>();
        maker = new ArrayList<>();
        image = new ArrayList<>();

        title.add("Scrunchie");
        title.add("Tote Bag");
        title.add("Cloth hat");
        title.add("Cloth face mask");
        title.add("Scarf");

        image.add(R.drawable.image1);
        image.add(R.drawable.totebag);
        image.add(R.drawable.hat);
        image.add(R.drawable.facemask);
        image.add(R.drawable.scarf);

        maker.add("Made by Zarya");
        maker.add("Made by Mariyam");
        maker.add("Made by Saleha");
        maker.add("Made by Bunga");
        maker.add("Made by Raya");

        adapter = new Adapter(getContext(), title, maker, image);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        productList.setLayoutManager(gridLayoutManager);
        productList.setAdapter(adapter);

        return view;
    }
}
