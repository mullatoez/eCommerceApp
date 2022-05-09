package com.cs.ecommerceapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.ecommerceapp.R;
import com.cs.ecommerceapp.adapter.ProductAdapter;
import com.cs.ecommerceapp.apis.ApiInterface;
import com.cs.ecommerceapp.model.Product;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements ProductAdapter.onProductClickListener {

    private ApiInterface apiInterface;
    private RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String base_url = "http://192.168.0.104:9000/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Product>> call = apiInterface.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Product> products = response.body();

                getProductsFromApi(products);

              /* Toast.makeText(getContext(), "products size is: " + products.size(), Toast.LENGTH_LONG)
                        .show();*/

                /*for (Product product : products) {
                    Toast.makeText(
                            getContext(),
                            "Product name is: " + product.getName(),
                            Toast.LENGTH_LONG
                    ).show();
                }
*/
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        /*List<Product> products = getProductsFromArrayObject();

        ProductAdapter adapter = new ProductAdapter(products, getActivity(), this);
        recyclerView.setAdapter(adapter);*/

        return view;
    }

    private void getProductsFromApi(List<Product> products) {
        ProductAdapter adapter = new ProductAdapter(products,getActivity(),this);
        recyclerView.setAdapter(adapter);

    }

    @NonNull
    private List<Product> getProductsFromArrayObject() {

        Product[] products = new Product[]{
                new Product("Nike Shoe",
                        "https://images.yaoota.com/jfliQaIViUPkox1GreUaIcxqoN0=/trim/yaootaweb-production-ke/media/crawledproductimages/34fc49fbef4b2f91e8ee38c8207dd716cf16f1f2.jpg",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
                new Product("Nike Shoe",
                        "https://cdn.shopify.com/s/files/1/0502/5621/6213/products/Kaws_533x.jpg?v=1646917648",
                        599.0),
        };

        return Arrays.asList(products);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onProductClick(int position) {
     /* handle code here */
    }
}