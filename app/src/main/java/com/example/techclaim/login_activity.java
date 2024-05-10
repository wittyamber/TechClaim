package com.example.techclaim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class login_activity extends AppCompatActivity {
    private boolean passwordshowing = false;
    private EditText login_email, login_password;
    private String email, password;
    private String URL = "http://192.168.101.73/techclaim/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        final ImageView pass_icon = findViewById(R.id.pass_icon);
        final TextView signup_btn = findViewById(R.id.signup_btn);


        pass_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if password is showing or not
                if (passwordshowing){
                    passwordshowing = false;

                    login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass_icon.setImageResource(R.drawable.show_pass);
                }else{
                    passwordshowing = true;

                    login_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_icon.setImageResource(R.drawable.hide_pass);
                }
                //move cursor at last of the text
                login_password.setSelection(login_password.length());

            }
        });

        //sign up button or registration page
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });
    }

    public void login(View view) {
        email = login_email.getText().toString().trim();
        password = login_password.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            // Perform login
            performLogin();
        } else {
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void performLogin() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Intent intent = new Intent(login_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failed")) {
                    Toast.makeText(login_activity.this, "Invalid Login ID/Password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login_activity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void signup(View view) {
        Intent intent = new Intent(this, signup_activity.class);
        startActivity(intent);
        finish();
    }
}