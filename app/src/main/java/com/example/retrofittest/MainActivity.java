package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CustomListener {

    TextInputLayout til_title;
    Button btn_search;
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        til_title = findViewById(R.id.til_search_title);
        btn_search = findViewById(R.id.btn_search_book);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRetroFit(til_title.getEditText().getText().toString());
            }
        });
    }

    private void initRetroFit(String title) {
        if (title.isEmpty()){
            return;
        } else {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.googleapis.com/books/").addConverterFactory(GsonConverterFactory.create()).build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            apiInterface.getBooks(title,"5","books").enqueue(new Callback<BookPojo>() {
                @Override
                public void onResponse(Call<BookPojo> call, Response<BookPojo> response) {
                    if(response.isSuccessful()){
                        adapter = new CustomAdapter();
                        adapter.setDataSet(response.body());
                        adapter.setListener(MainActivity.this);
                        recyclerView.setAdapter(adapter);;
                    }
                }
                @Override
                public void onFailure(Call<BookPojo> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void itemClicked(BookItem item) {
        Intent intent = new Intent(this, BookDetail.class);
        intent.putExtra("Book",item);
        startActivity(intent);
    }
}