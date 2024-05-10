package com.example.techclaim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

import java.util.HashMap;
import java.util.Map;

public class signup_activity extends AppCompatActivity {

    private boolean passwordShowing = false;
    private boolean conpasswordShowing = false;
    private EditText signup_studID, signup_name, signup_email, signup_password, signup_password_con;
    private AppCompatButton signup_btn;
    private String URL = "http://192.168.101.73/techclaim/signup.php";
    private String studID, name, email, password, passwordcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        final EditText signup_email = findViewById(R.id.signup_email);
        final EditText signup_pass = findViewById(R.id.signup_password);
        final EditText signup_passcon = findViewById(R.id.signup_password_con);
        final ImageView pass_icon = findViewById(R.id.pass_icon_signup);
        final ImageView pass_iconcon = findViewById(R.id.pass_icon_con);
        final AppCompatButton signup_btn = findViewById(R.id.reg_btn);
        final TextView logintxt_btn = findViewById(R.id.logintxt_btn);

        signup_name = findViewById(R.id.signup_name);
        signup_studID = findViewById(R.id.signup_studID);
        studID = name = password = passwordcon = "";

        pass_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordShowing){
                    passwordShowing = false;

                    signup_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass_icon.setImageResource(R.drawable.show_pass);
                }else{
                    passwordShowing = true;

                    signup_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_icon.setImageResource(R.drawable.hide_pass);
                }

                //move cursor at last of the text
                signup_pass.setSelection(signup_pass.length());

            }
        });

        pass_iconcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conpasswordShowing){
                    conpasswordShowing = false;

                    signup_passcon.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass_iconcon.setImageResource(R.drawable.show_pass);
                }else{
                    conpasswordShowing = true;

                    signup_passcon.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass_iconcon.setImageResource(R.drawable.hide_pass);
                }

                //move cursor at last of the text
                signup_passcon.setSelection(signup_passcon.length());

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save(v);
            }
        });


        logintxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void save(View view) {
        studID = signup_studID.getText().toString().trim();
        name = signup_name.getText().toString().trim();
        email = signup_email.getText().toString().trim();
        password = signup_password.getText().toString().trim();
        passwordcon = signup_password_con.getText().toString().trim();

        if(!password.equals(passwordcon)) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if(!studID.equals("") && !name.equals("") && !email.equals("") && !password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(signup_activity.this, MainActivity.class);
                        Toast.makeText(signup_activity.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                        signup_btn.setClickable(false);
                        finish();
                    } else if (response.equals("failed")) {
                        Toast.makeText(signup_activity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("studID", studID);
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, login_activity.class);
        startActivity(intent);
    }
}