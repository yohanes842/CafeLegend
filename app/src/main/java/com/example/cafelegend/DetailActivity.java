package com.example.cafelegend;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafelegend.adapter.HomeAdapter;
import com.example.cafelegend.model.Food;
import com.google.android.material.navigation.NavigationView;

import java.util.Vector;

public class DetailActivity extends AppCompatActivity {

    ImageView foodImage_IV;
    TextView foodName_TV, foodPrice_TV, foodDesc_TV;

    Bundle extras;
    String foodName, foodDesc;
    int foodPrice, foodImage;

    void init(){
        extras = getIntent().getExtras();
        foodName =  extras.getString("foodName");
        foodPrice =  extras.getInt("foodPrice");
        foodDesc =  extras.getString("foodDesc");
        foodImage = extras.getInt("foodImage");

        foodName_TV = findViewById(R.id.foodName_TV);
        foodPrice_TV = findViewById(R.id.foodPrice_TV);
        foodDesc_TV = findViewById(R.id.foodDesc_TV);
        foodImage_IV = findViewById(R.id.detail_image);

        foodImage_IV.setImageDrawable(this.getDrawable(foodImage));
        foodName_TV.setText(foodName);
        foodPrice_TV.setText("Rp. " + String.valueOf(foodPrice) + ",-");
        foodDesc_TV.setText(foodDesc);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();

        System.out.println(foodName);
        System.out.println(foodPrice);
        System.out.println(foodDesc);
        System.out.println(foodImage);

    }

}
