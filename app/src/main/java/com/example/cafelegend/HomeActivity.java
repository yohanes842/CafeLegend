package com.example.cafelegend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafelegend.adapter.HomeAdapter;
import com.example.cafelegend.model.Food;
import com.google.android.material.navigation.NavigationView;

import java.util.Vector;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    TextView welcomeMessageTV;
    Bundle extras;
    String username;

    RecyclerView rvFood;
    RecyclerView rvDrink;
    Vector<Food> foodVector;
    HomeAdapter adapter;

    void init(){
        welcomeMessageTV = findViewById(R.id.welcome_TV);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);

        extras = getIntent().getExtras();
        username = extras.getString("username");
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
        welcomeMessageTV.setText("Welcome, "+ username);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setWelcomeMessage();
        setupDrawer();

        rvFood = findViewById(R.id.home_food);
        loadData();

        adapter = new HomeAdapter(this);
        adapter.setFoodVector(foodVector);
        rvFood.setAdapter(adapter);
        rvFood.setLayoutManager(new GridLayoutManager(this, 3));

        rvDrink = findViewById(R.id.home_drink);
        loadData2();
        adapter = new HomeAdapter(this);
        adapter.setFoodVector(foodVector);
        rvDrink.setAdapter(adapter);
        rvDrink.setLayoutManager(new GridLayoutManager(this, 3));

    }

    private void loadData() {
        foodVector = new Vector<>();
        foodVector.add(new Food("Warm Cheese Box Bites", "Ini desc", 12000, R.drawable.menu1));
        foodVector.add(new Food("Smoked Beef Quiche", "Ini desc", 12000, R.drawable.menu2));
        foodVector.add(new Food("Beef Sausage & Cheese Croissant", "Ini desc", 12000, R.drawable.menu3));
    }

    private void loadData2() {
        foodVector = new Vector<>();
        foodVector.add(new Food("Menu1", "Ini desc", 123, R.drawable.menu1));
        foodVector.add(new Food("Menu2", "Ini desc", 333, R.drawable.menu2));
        foodVector.add(new Food("Menu3", "Ini desc", 555, R.drawable.menu3));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId()){
            case R.id.nav_items:
                intent = new Intent(this, ItemsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_find_us:
                intent = new Intent(this, FindUsActivity.class);
                intent.putExtra("username", username);
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