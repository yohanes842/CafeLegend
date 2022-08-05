package com.example.cafelegend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    EditText quantity_ET;
    Button order_btn, back_btn;

    Bundle extras;
    String username, foodName, foodDesc;
    int foodPrice, foodImage;

    void init(){
        extras = getIntent().getExtras();
        username = extras.getString("username");
        foodName =  extras.getString("foodName");
        foodPrice =  extras.getInt("foodPrice");
        foodDesc =  extras.getString("foodDesc");
        foodImage = extras.getInt("foodImage");

        foodName_TV = findViewById(R.id.foodName_TV);
        foodPrice_TV = findViewById(R.id.foodPrice_TV);
        foodDesc_TV = findViewById(R.id.foodDesc_TV);
        foodImage_IV = findViewById(R.id.detail_image);
        quantity_ET = findViewById(R.id.quantity_ET);
        order_btn = findViewById(R.id.order_btn);
        back_btn = findViewById(R.id.back_btn);

        foodImage_IV.setImageDrawable(this.getDrawable(foodImage));
        foodName_TV.setText(foodName);
        foodPrice_TV.setText("Rp. " + String.valueOf(foodPrice) + ",-");
        foodDesc_TV.setText(foodDesc);

        setEventListener();
    }

    void setEventListener(){
        setOrderEvent();
        setBackEvent();
    }

    void setOrderEvent(){
        order_btn.setOnClickListener(x -> {
            String quantity = quantity_ET.getText().toString();
            if(quantity.isEmpty()){
                createDialog();
                return;
            }

            Intent intent = new Intent(this, ItemsActivity.class);
            intent.putExtra("username", username);
            Toast.makeText(getApplicationContext(),
                    "Order Successful",
                    Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }

    void setBackEvent(){
        back_btn.setOnClickListener(x -> {
            onBackPressed();
        });
    }

    void createDialog(){
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message
        builder.setMessage("Quantity can not be empty");

        // Set Cancelable false
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

}
