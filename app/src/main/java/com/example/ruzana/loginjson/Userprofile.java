package com.example.ruzana.loginjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Userprofile extends AppCompatActivity {
    String ret_username,ret_password,ret_mobile,ret_email;
    TextView username,password,mobile,email;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        username=(TextView)findViewById(R.id.txt_username);
        password=(TextView)findViewById(R.id.txt_password);
        mobile=(TextView)findViewById(R.id.txt_mobile);
        email=(TextView)findViewById(R.id.txt_email);
        logout=(Button)findViewById(R.id.btn_logout);

        Intent intent=getIntent();
        ret_username=intent.getStringExtra("username");
        username.setText("Username : " + ret_username);

        ret_password=intent.getStringExtra("password");
        password.setText("Password : " + ret_password);

        ret_email=intent.getStringExtra("email");
        email.setText("email : " + ret_email);

        ret_mobile=intent.getStringExtra("mobile");
        mobile.setText("Mobile no : " + ret_mobile);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Userprofile.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
