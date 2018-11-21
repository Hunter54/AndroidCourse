package com.example.ionut.androidcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etUserName;
    private EditText etPassword;
    private String registeredUser = "";
    private String userPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String userNameFromForm = etUserName.getText().toString();
                String passwordFromForm = etPassword.getText().toString();
                Log.d("LoginActivity", "Login pressed");
                if (userNameFromForm.equals(registeredUser))
                {
                    if (passwordFromForm.equals(userPassword))
                    {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Wrong Username", Toast.LENGTH_SHORT).show();
                }


            }


        });
    }
}
