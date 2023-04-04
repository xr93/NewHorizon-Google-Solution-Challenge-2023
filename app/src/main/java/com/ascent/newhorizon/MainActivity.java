package com.ascent.newhorizon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProductFragment productFragment = new ProductFragment();
    CommisionFragment commisionFragment = new CommisionFragment();
    BlogFragment blogFragment = new BlogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.product:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,productFragment).commit();
                        return true;
                    case R.id.commision:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,commisionFragment).commit();
                        return true;
                    case R.id.blog:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,blogFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}