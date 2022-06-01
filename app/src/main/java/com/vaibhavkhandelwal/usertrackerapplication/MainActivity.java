package com.vaibhavkhandelwal.usertrackerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameEt;
    EditText numberEt;
    Button loginBtn;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "pref";
    private static final String NAME_KEY = "name";
    private static final String NUMBER_KEY = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.name_et);
        numberEt = findViewById(R.id.number_et);
        loginBtn = findViewById(R.id.login_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(NAME_KEY,null);
        String number = sharedPreferences.getString(NUMBER_KEY,null);

        if(name != null && number != null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


        loginBtn.setOnClickListener(view -> {


            if(nameEt.getText().toString().length() != 0 && numberEt.getText().toString().length() == 10){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NAME_KEY, nameEt.getText().toString());
                editor.putString(NUMBER_KEY, "+91" + numberEt.getText().toString());
                editor.apply();
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "You are now logged in", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_SHORT).show();
            }
        });


    }
}