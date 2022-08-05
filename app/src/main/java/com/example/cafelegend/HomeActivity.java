package com.example.cafelegend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafelegend.adapter.HomeAdapter;
import com.example.cafelegend.model.Food;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.Vector;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeAdapter.OnEventListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    TextView welcomeMessageTV;
    Bundle extras;
    String username;

    RecyclerView rvFood;
    RecyclerView rvDrink;
    Vector<Food> foodVector, drinkVector;
    HomeAdapter foodAdapter, drinkAdapter;

    //    carousel
    ImageView imageView;
    ImageButton next, previous;
    ViewFlipper imageFlipper;

    //card
    CardView cardItem;

//    int i=0;
//    private int[] imgArray = {R.drawable.menu1, R.drawable.menu2, R.drawable.menu3};

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

    void setupCarousel(){
        //        carousel
//        imageView = findViewById(R.id.iv_home);
        imageFlipper = findViewById(R.id.vf_home);
        previous = findViewById(R.id.ib_previous);
        next = findViewById(R.id.ib_next);

//        cardItem = findViewById(R.id.cv_item);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlipper.showPrevious();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlipper.showNext();
            }
        });

//        cardItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, ItemsActivity.class));
//            }
//        });
    }

    void initRecycler(){
        rvFood = findViewById(R.id.home_food);
        loadData();

        foodAdapter = new HomeAdapter(this, foodVector, this);
        rvFood.setAdapter(foodAdapter);
        rvFood.setLayoutManager(new GridLayoutManager(this, 3));

        rvDrink = findViewById(R.id.home_drink);
        loadData2();

        drinkAdapter = new HomeAdapter(this, drinkVector, this);
        rvDrink.setAdapter(drinkAdapter);
        rvDrink.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void loadData() {
        foodVector = new Vector<>();
        foodVector.add(new Food("Warm Cheese Box Bites", "Tangy goat cheese and crispy baguette slices are the ideal canvas for this comforting yet light take on bruschetta.", 12000, R.drawable.menu1));
        foodVector.add(new Food("Smoked Beef Quiche", "Ini desc", 12000, R.drawable.menu2));
        foodVector.add(new Food("Beef Sausage & Cheese Croissant", "Ini desc", 12000, R.drawable.menu3));
    }

    private void loadData2() {
        drinkVector = new Vector<>();
        drinkVector.add(new Food("Menu1", "Ini desc", 123, R.drawable.menu1));
        drinkVector.add(new Food("Menu2", "Ini desc", 333, R.drawable.menu2));
        drinkVector.add(new Food("Menu3", "Ini desc", 555, R.drawable.menu3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setWelcomeMessage();
        setupDrawer();
        setupCarousel();
        initRecycler();
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

    @Override
    public void onCardClick(int position) {
        Log.d("tag", "Clicked");
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("foodName", foodVector.get(position).getFoodName());
        intent.putExtra("foodPrice", foodVector.get(position).getFoodPrice());
        intent.putExtra("foodDesc", foodVector.get(position).getDescription());
        intent.putExtra("foodImage", foodVector.get(position).getFoodImage());
        startActivity(intent);
        finish();
    }
}