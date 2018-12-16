package com.example.norskhaxor.coffeeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.norskhaxor.coffeeshop.CustomViews.AuthenticationDialog;
import com.example.norskhaxor.coffeeshop.Interface.AuthenticationListener;
import com.example.norskhaxor.coffeeshop.ViewHolder.Constants;

public class MainActivity extends AppCompatActivity implements AuthenticationListener {

    SharedPreferences prefs = null;

    Button btnSignIn, btnSignUp;
    TextView txtSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        txtSlogan = findViewById(R.id.txtSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Pacifico.ttf");
        txtSlogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });



    }

    public void process(View view) {
        Intent intent;
        if(view.getId()==R.id.Maps){
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCodeReceive(String auth_token) {
        if(auth_token == null)
            return;

        SharedPreferences.Editor editor = (SharedPreferences.Editor) getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        editor.putString("token",auth_token);
        editor.apply();


    }

    public void after_click_login(View view) {
        AuthenticationDialog auth_dialog = new AuthenticationDialog(this, this);
        auth_dialog.setCancelable(true);
        auth_dialog.show();
    }

 }


