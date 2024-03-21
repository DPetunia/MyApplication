package com.irdhina.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput, tvWelcome;
    Button btnCount, btnSubmit, btnLogout;
    Integer counter;
    EditText etName;
    SharedPreferences spUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = findViewById(R.id.tvOutput);
        btnCount = findViewById(R.id.btnCount);
        tvWelcome = findViewById(R.id.tvWelcome);
        etName = findViewById(R.id.etName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogout = findViewById(R.id.btnLogout);
        counter = 0;

        spUser = getSharedPreferences("prefUser", Context.MODE_PRIVATE);

        String nama = spUser.getString("name", "-");
        Boolean isloggedin = spUser.getBoolean("isloggedin", false);

        if(isloggedin) {
            tvWelcome.setText("Welcome " + nama);
        }

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvOutput.setText(counter.toString());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = spUser.edit();
                editor.putString("name", etName.getText().toString());
                editor.putBoolean("isloggedin", true);
                editor.apply();

                tvWelcome.setText("Welcome " + etName.getText().toString());
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = spUser.edit();
                editor.clear();
                editor.apply();
                tvWelcome.setText("Welcome ");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key_counter", counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onRestoreInstanceState(outState);
        counter = outState.getInt("key_counter", 0);
        tvOutput.setText(counter.toString());
    }

}
