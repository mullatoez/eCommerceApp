package com.cs.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cs.ecommerceapp.ProductDetailActivity;
import com.cs.ecommerceapp.R;
import com.cs.ecommerceapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> products;
    Context context;
    onProductClickListener listener;

    public ProductAdapter(List<Product> products, Context context, onProductClickListener listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Product productList = products.get(position);
        holder.productName.setText(productList.getName());
        holder.productPrice.setText(String.valueOf(productList.getPrice()));
        holder.description.setText(productList.getDescription());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(context).load(productList.getImageUrl()).apply(options).into(holder.productImage);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productName",productList.getName());
            intent.putExtra("productDescription",productList.getDescription());
            intent.putExtra("productImageUrl",productList.getImageUrl());
            intent.putExtra("productPrice",productList.getPrice());
            view.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView description;

        onProductClickListener productClickListener;

        public ViewHolder(@NonNull View itemView, onProductClickListener productClickListener) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.textView_productName);
            productPrice = itemView.findViewById(R.id.textView_productPrice);
            description = itemView.findViewById(R.id.textView_Description);

            this.productClickListener = productClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            productClickListener.onProductClick(getAdapterPosition());
        }
    }

    /* best way for performance optimisation */
    public interface onProductClickListener {
        void onProductClick(int position);
    }
}
