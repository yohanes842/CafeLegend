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
        foodVector.add(new Food("Asparagus Ribbon Crostini", "Serving ribbons of asparagus on top of baguette slices turns it from a side dish into a spring-ready finger food. Fresh mint helps make it even more colorful.", 35000, R.drawable.asparagus_ribbon_crostini));
        foodVector.add(new Food("Warm Cheese Box Bites", "Move over cheese straws! These cheesy bites aren't quite crackers, puffs, or straws. They are just as tasty though.", 21000, R.drawable.warm_cheese_box_bites));
        foodVector.add(new Food("Grilled Chicken With Fresh Cherry Salsa", "Juicy grilled chicken breasts, topped with a delicious ruby-red cherry salsa, made from the season’s best local cherries. Quick, easy and super yummy, this the ultimate summer plate!", 49000, R.drawable.grilled_chicken));
    }
    private void loadData2() {
        drinkVector = new Vector<>();
        drinkVector.add(new Food("Iced caramel Macchiato", "We combine our rich, full-bodied espresso with vanilla-flavored syrup, milk and ice, then top it off with a caramel drizzle for an oh-so-sweet finish.", 35000, R.drawable.iced_caramel_macchiato));
        drinkVector.add(new Food("Iced Latte", "Small-lot espresso combined with milk and served over ice creates perfectly handcrafted sips of cool.", 23000, R.drawable.iced_latte));
        drinkVector.add(new Food("Kiwi-StarFruit Refreshers", "Starfruit- and kiwi-flavored juice and real kiwi fruit pieces shaken with ice. Deliciously refreshing.", 27000, R.drawable.kiwi_starfruit));
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
        intent.putExtra("foodName", foodVector.get(position).getFoodName());
        intent.putExtra("foodPrice", foodVector.get(position).getFoodPrice());
        intent.putExtra("foodDesc", foodVector.get(position).getDescription());
        intent.putExtra("foodImage", foodVector.get(position).getFoodImage());
        startActivity(intent);
    }
}