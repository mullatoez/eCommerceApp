package com.cs.ecommerceapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs.ecommerceapp.MainActivity;
import com.cs.ecommerceapp.R;
import com.cs.ecommerceapp.apis.ApiInterface;
import com.cs.ecommerceapp.model.Customer;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String url = "http://192.168.0.104:9000/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiInterface = retrofit.create(ApiInterface.class);

        Button registerBtn = findViewById(R.id.button_regster);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCustomers();
            }
        });

    }

    private void registerCustomers() {
        EditText usernameEt = findViewById(R.id.editText_Username_Register);
        EditText passwordEt = findViewById(R.id.editText_Password_Register);

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        Customer customer = new Customer(username, password);

        Call<Customer> call = apiInterface.createCustomer(customer);

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Sorry an error occurred. Try again",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                Toast.makeText(RegisterActivity.this, "Customer created successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Sorry. " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error", t.getLocalizedMessage());
            }
        });
    }
}