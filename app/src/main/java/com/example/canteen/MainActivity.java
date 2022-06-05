package com.example.canteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login,signup;
    String s1,s2;
    TextView textView;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login =findViewById(R.id.button);
        signup =findViewById(R.id.button1);
        textView=findViewById(R.id.textView);
        db=new DB(MainActivity.this);
    }

    public void check(View view) {
        s1 = email.getText().toString();
        s2 = password.getText().toString();
        int loc=db.checkUserExist(s1,s2);
        if (loc == 10){
            textView.setText("Invalid Credentials");
        }
        else if(loc  == 0){
            textView.setText("Logged In");
            Intent i = new Intent(getApplicationContext(),Homepage.class);
            startActivity(i);
        }
        else if(loc == 1){
            textView.setText("Logged In as Admin");
            Intent i = new Intent(getApplicationContext(),Adminpage.class);
            startActivity(i);
        }
    }

    public void sav(View view) {
        s1 = email.getText().toString();
        s2 = password.getText().toString();
        String loc=db.createUser(s1,s2);
        textView.setText(loc);
    }

}