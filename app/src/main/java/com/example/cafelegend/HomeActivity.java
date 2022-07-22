package com.example.cafelegend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    TextView welcomeMessageTV;
    Bundle extras;
    String username;

    void init(){
        welcomeMessageTV = findViewById(R.id.welcome_TV);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);
    }

    void setupDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
    }

    @SuppressLint("SetTextI18n")
    void setWelcomeMessage(){
        extras = getIntent().getExtras();
        username = extras.getString("username");
        welcomeMessageTV.setText("Welcome, "+ username);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setWelcomeMessage();
        setupDrawer();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_home, menu);
//        return true;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId()){
            case R.id.nav_home:
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_items:
                intent = new Intent(this, ItemsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_find_us:
                intent = new Intent(this, FindUsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_log_out:
                intent = new Intent(this, LoginActivity.class);
                Toast.makeText(getApplicationContext(),
                        "Log Out Successful",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                break;
            default:
        }
        return false;
    }
}