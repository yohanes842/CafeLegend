package com.example.cafelegend;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cafelegend.adapter.FragmentAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class ItemsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    Bundle extras;
    String username;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter adapter;

    void init(){

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Appetizer"));
        tabLayout.addTab(tabLayout.newTab().setText("Main Course"));
        tabLayout.addTab(tabLayout.newTab().setText("Beverage"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        init();
        setupDrawer();
    }

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
