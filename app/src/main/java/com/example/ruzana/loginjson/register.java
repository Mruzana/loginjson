package com.example.ruzana.loginjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class register extends AppCompatActivity {
    EditText username,password,mobile,email;
    Button register;
    ProgressDialog pDialog;
    String register_url="http://fahidhassank.xyz/api/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent=getIntent();
        register=(Button)findViewById(R.id.btn_register);
        username=(EditText)findViewById(R.id.edt_username);
        System.out.println("Username" + username.getText().toString());
        password=(EditText)findViewById(R.id.edt_password);
        mobile=(EditText)findViewById(R.id.edt_mobile);
        email=(EditText)findViewById(R.id.edt_email);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();
            }
        });
    }
    public void register(){

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(register.this);
      /**  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, register_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.hide();
                System.out.println(response);
                try {

                    //JSONObject object=new JSONObject(response);
                    String status=response.getString("status");
                    if (status.equals("1")){
                      //  JSONObject response_obj=object.getJSONObject("response");
                        String username=response.getString("username");
                        String password=response.getString("password");
                        String email=response.getString("email");
                        String mobile =response.getString("mobile");

                        Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                      /**  Intent intent =new Intent(register.this,Userprofile.class);
                        intent.putExtra("username",username);
                        intent.putExtra("password",password);
                        intent.putExtra("email",email);
                        intent.putExtra("mobile",mobile);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username.getText().toString());
                map.put("password", password.getText().toString());
                map.put("email", email.getText().toString());
                map.put("mobile", mobile.getText().toString());
                return map;
            }
        };
        requestQueue.add(jsonObjectRequest);*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                try {
                    JSONObject object=new JSONObject(response);
                    String status=object.getString("status");

                    if(status.equals("1")){

                      /** JSONObject response_obj=object.getJSONObject("response");
                        String username=response.getString("username");
                        String password=response_obj.getString("password");
                        String email=response_obj.getString("email");
                        String mobile =response_obj.getString("mobile");**/

                        Toast.makeText(register.this, "Success", Toast.LENGTH_SHORT).show();

            }
                    else {
                        Toast.makeText(register.this, "Invalid user..Click New user", Toast.LENGTH_SHORT).show();
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
        }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", username.getText().toString());
            map.put("password", password.getText().toString());
            map.put("email", email.getText().toString());
            map.put("mobile", mobile.getText().toString());


            return map;
        }

    };
        requestQueue.add(stringRequest);
}
}