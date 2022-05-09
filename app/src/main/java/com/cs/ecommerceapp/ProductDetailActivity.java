package com.cs.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productImageUrl = intent.getStringExtra("productImageUrl");
        String productDescription = intent.getStringExtra("productDescription");
        Double productPrice = intent.getDoubleExtra("productPrice",0.0);

        TextView product_name = findViewById(R.id.textView_product_name);
        TextView product_description = findViewById(R.id.textView_product_description);
        TextView product_price = findViewById(R.id.textView_product_price);
        ImageView product_image = findViewById(R.id.imageView_product);

        product_name.setText(productName);
        product_description.setText(productDescription);
        product_price.setText(String.valueOf(productPrice));

        Glide.with(this).load(productImageUrl).into(product_image);

    }
}