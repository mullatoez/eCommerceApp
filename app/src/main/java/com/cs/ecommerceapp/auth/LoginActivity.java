package com.cs.ecommerceapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs.ecommerceapp.MainActivity;
import com.cs.ecommerceapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.button_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLoginProcess();
            }
        });
    }

    private void performLoginProcess() {
        EditText usernameEt = findViewById(R.id.editText_Username_Login);
        EditText passwordEt = findViewById(R.id.editText_Password_Login);

        /* Username & Password */
        String usernameL = "admin";
        String passwordL = "admin";

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        if (username.equals(usernameL) && password.equals(passwordL)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect...Try again..", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}