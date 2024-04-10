package com.example.techclaim;

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

public class signup_activity extends AppCompatActivity {

    private boolean passwordShowing = false;
    private boolean conpasswordShowing = false;
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

                Intent intent = new Intent();

            }
        });

        logintxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}