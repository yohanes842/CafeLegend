package com.example.cafelegend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;
    TextView usernameErr, passwordErr;
    Button login;
    RelativeLayout landing_page;
    Fade fade;
    ViewGroup rootView;

    void init(){
        usernameET = findViewById(R.id.username_ET);
        passwordET = findViewById(R.id.password_ET);
        usernameErr = findViewById(R.id.username_errorMsg);
        passwordErr = findViewById(R.id.password_errorMsg);
        login = findViewById(R.id.login_btn);

        //landing_page set time out with transitions
        rootView = findViewById(R.id.login_page);
        landing_page = findViewById(R.id.landing_page);
        fade = new Fade(Fade.OUT);
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = () -> {
            TransitionManager.beginDelayedTransition(rootView, fade);
            rootView.removeView(landing_page);
        };
        handler.postDelayed(runnable, 1500);
    }

    @SuppressLint("SetTextI18n")
    void setLoginEvent(){
        login.setOnClickListener(x -> {
            boolean errors = false;
            String username = usernameET.getText().toString();
            int username_length = username.length();
            String password = passwordET.getText().toString();

            if(username.isEmpty()){
                usernameErr.setText("Username must be filled");
                usernameErr.setVisibility(View.VISIBLE);
                errors = true;
            } else if(username_length < 5 || username_length > 20){
                usernameErr.setText("Username's length must between 5-20 characters");
                usernameErr.setVisibility(View.VISIBLE);
                errors = true;
            } else{
                usernameErr.setVisibility(View.GONE);
            }

            if(password.isEmpty()){
                passwordErr.setText("Password must be filled");
                passwordErr.setVisibility(View.VISIBLE);
                errors = true;
            } else if(!password.matches("^[a-zA-Z0-9]*$")){
                passwordErr.setText("Password must only contains letter or number");
                passwordErr.setVisibility(View.VISIBLE);
                errors = true;
            } else{
                passwordErr.setVisibility(View.GONE);
            }

            if(!errors){
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("username", username);
                Toast.makeText(getApplicationContext(),
                        "Login Successful",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        setLoginEvent();
    }
}