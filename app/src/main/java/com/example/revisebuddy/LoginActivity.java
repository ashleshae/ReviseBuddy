package com.example.revisebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn1=findViewById(R.id.dontHaveAccount);
        class MyOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LoginActivity.this,RegisterActivity.class));
            }
        }
        MyOnClickListener listener1 = new MyOnClickListener();
        btn1.setOnClickListener(listener1);

        TextView btn2=findViewById(R.id.buttonLogin);
        class MyOnClickListener2 implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LoginActivity.this,Monthlypage.class));
            }
        }
        MyOnClickListener2 listener2 = new MyOnClickListener2();
        btn2.setOnClickListener(listener2);


    }
}