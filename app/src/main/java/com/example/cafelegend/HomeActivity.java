package com.example.cafelegend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

        previous.setOnClickListener(v -> imageFlipper.showPrevious());

        next.setOnClickListener(v -> imageFlipper.showNext());

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
        foodVector.add(new Food("Warm Cheese Box Bites", "Move over cheese straws! These cheesy bites aren't quite crackers, puffs, or straws. They are just as tasty though.", 21000, R.drawable.warm_cheese_box_bites));
        foodVector.add(new Food("Smoked Beef Quiche", "A savory quiche pie made from Smoked Beef and Cheese", 38000, R.drawable.smoked_beef_quiche));
        foodVector.add(new Food("Beef Sausage & Cheese Croissant", " A New York style bagel topped with Asiago cheese, poppy and sesame seeds, onion and garlic.", 37000, R.drawable.beef_sausage_and_cheese_croissants));    }
    private void loadData2() {
        drinkVector = new Vector<>();
        drinkVector.add(new Food("Earl Grey Tea", "We take a strong black tea base and add the essence of bergamot, a citrus fruit with subtle lemon and floral lavender notes, to create this aromatically awesome tea flavor.", 18000, R.drawable.earl_grey_tea));
        drinkVector.add(new Food("Hot Chocolate", "Steamed milk and mocha sauce topped with sweetened whipped cream and a chocolate-flavored drizzle. A timeless classic made to sweeten your spirits.", 25000, R.drawable.hot_choco));
        drinkVector.add(new Food("Pumpkin Spice Latte", "Our signature espresso and steamed milk with the celebrated flavor combination of pumpkin, cinnamon, nutmeg and clove. Enjoy it topped with whipped cream and real pumpkin-pie spices.", 35000, R.drawable.pumpkin_spice_latte));    }

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId()){
            case R.id.nav_items:
                intent = new Intent(this, ItemsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.nav_find_us:
                intent = new Intent(this, FindUsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.nav_log_out:
                intent = new Intent(this, LoginActivity.class);
                Toast.makeText(getApplicationContext(),
                        "Log Out Successful",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            default:
        }

        return false;
    }

    @Override
    public void onCardClick(Vector<Food> foodVector, int position) {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("username", username);
        intent.putExtra("food", foodVector.get(position));
        startActivity(intent);
    }
}