package com.example.revisebuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Month;
import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    FirebaseDatabase database;
    FirebaseAuth auth;
    EditText email, password;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        auth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");

        email= findViewById(R.id.Email);
        password= findViewById(R.id.Password);
        TextView btn=findViewById(R.id.hasAccount);
        class MyOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (RegisterActivity.this,LoginActivity.class));
            }
        }
        MyOnClickListener listener1 = new MyOnClickListener();
        btn.setOnClickListener(listener1);

        TextView btn1=findViewById(R.id.buttonLogin);
        class MyOnClickListener2 implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                registerUser();//function call on button press
            }
        }
        MyOnClickListener2 listener2 = new MyOnClickListener2();
        btn1.setOnClickListener(listener2);
    }

    private void registerUser() {

        if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Cannot be empty");
        }

        if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Cannot be empty");
        }

        else{
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();//here you get the current user's section from the database

                        assert user != null;
                        String uid=user.getUid(); //Uis is the unique id allocated to everyuser in the authentication section of firebase
                        HashMap<String, Object> hashMap= new HashMap<>(); //creating hashmap for storing key value
                        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");//created a child node called Students in our realtime database

                        hashMap.put("email", email.getText().toString());
                        hashMap.put("password", password.getText().toString());

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Data pushed into the database", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, Monthlypage.class));
                                }else {
                                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                }
            });
        }
    }
}


