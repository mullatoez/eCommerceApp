package com.cs.ecommerceapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cs.ecommerceapp.fragment.OrdersFragment;
import com.cs.ecommerceapp.fragment.CartFragment;
import com.cs.ecommerceapp.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                .commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_orders:
                        selectedFragment = new OrdersFragment();
                        break;
                    case R.id.nav_cart:
                        selectedFragment = new CartFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container,
                        selectedFragment
                ).commit();

                return true;
            };
}