package com.example.bmicalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationview = findViewById(R.id.nav_view);
        navigationview.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BMICalculatorFragment()).commit();
            navigationview.setCheckedItem(R.id.nav_share);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BMICalculatorFragment()).commit();
                break;

            case R.id.navigation_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BMIHistory()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserProfile()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "You share this app!", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_rate:
                Toast.makeText(this, "You appreciated this app!", Toast.LENGTH_LONG).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
