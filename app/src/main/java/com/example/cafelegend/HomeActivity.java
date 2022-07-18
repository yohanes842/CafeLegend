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
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeMessageTV;
    Bundle extras;

    void init(){
        welcomeMessageTV = findViewById(R.id.welcome_TV);
    }

    @SuppressLint("SetTextI18n")
    void setWelcomeMessage(){
        extras = getIntent().getExtras();
        welcomeMessageTV.setText("Welcome, "+ extras.getString("username"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setWelcomeMessage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId()){
            case R.id.nav_home:
                intent = new Intent(this, HomeActivity.class);
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

        return super.onOptionsItemSelected(item);
    }
}