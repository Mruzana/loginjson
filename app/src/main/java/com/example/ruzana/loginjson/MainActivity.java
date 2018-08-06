package com.example.ruzana.loginjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView newuser;
    EditText username,password;
    ProgressDialog pDialog;
  //  String Username,Password;
    String loginurl = "http://fahidhassank.xyz/api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        username=(EditText)findViewById(R.id.edt_username);
        password=(EditText)findViewById(R.id.edt_password);
        newuser=(TextView)findViewById(R.id.txt_new_user);
        login=(Button)findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }
    public void login() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                try {
                    JSONObject object=new JSONObject(response);
                    String status=object.getString("status");

                    if(status.equals("1")){

                        JSONObject response_obj=object.getJSONObject("response");
                        String username=response_obj.getString("username");
                        String password=response_obj.getString("password");
                        String email=response_obj.getString("email");
                        String mobile =response_obj.getString("mobile");

                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(MainActivity.this,Userprofile.class);
                        intent.putExtra("username",username);
                        intent.putExtra("password",password);
                        intent.putExtra("email",email);
                        intent.putExtra("mobile",mobile);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid user..Click New user", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username.getText().toString());
                map.put("password", password.getText().toString());
                return map;
            }

        };
        requestQueue.add(stringRequest);
    }

}
