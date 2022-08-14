package com.example.cafelegend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cafelegend.model.Food;
import java.math.BigDecimal;

public class DetailActivity extends AppCompatActivity {

    ImageView foodImage_IV;
    TextView foodName_TV, foodPrice_TV, foodDesc_TV;
    EditText quantity_ET;
    Button order_btn, back_btn;

    Bundle extras;
    String username, foodName, foodDesc, stringPrice;
    long foodPrice;
    BigDecimal formatPrice;
    int foodImage;

    void init(){
        extras = getIntent().getExtras();
        Food detail_food = extras.getParcelable("food");

        username = extras.getString("username");
        foodName =  detail_food.getFoodName();
        foodPrice =  detail_food.getFoodPrice();
        formatPrice = new BigDecimal(foodPrice).movePointLeft(3);
        stringPrice = "Rp. " + formatPrice + ",-";

        foodDesc =  detail_food.getDescription();
        foodImage = detail_food.getFoodImage();

        foodName_TV = findViewById(R.id.foodName_TV);
        foodPrice_TV = findViewById(R.id.foodPrice_TV);
        foodDesc_TV = findViewById(R.id.foodDesc_TV);
        foodImage_IV = findViewById(R.id.detail_image);
        quantity_ET = findViewById(R.id.quantity_ET);
        order_btn = findViewById(R.id.order_btn);
        back_btn = findViewById(R.id.back_btn);

        foodImage_IV.setImageDrawable(this.getDrawable(foodImage));
        foodName_TV.setText(foodName);
        foodPrice_TV.setText(stringPrice);
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
                createDialog("Quantity can not be empty");
                return;
            } else{
                int int_quantity = Integer.parseInt(quantity);
                if(int_quantity <= 0){
                    createDialog("Quantity must be greater than 0");
                    return;
                }
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
        back_btn.setOnClickListener(x -> onBackPressed());
    }

    void createDialog(String msg){
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message
        builder.setMessage(msg);

        // Set Cancelable false
        builder.setCancelable(false);

        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());

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
