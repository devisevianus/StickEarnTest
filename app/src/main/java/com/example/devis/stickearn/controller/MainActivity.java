package com.example.devis.stickearn.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devis.stickearn.R;
import com.example.devis.stickearn.adapter.ItemAdapter;
import com.example.devis.stickearn.api.Client;
import com.example.devis.stickearn.api.Service;
import com.example.devis.stickearn.model.Item;
import com.example.devis.stickearn.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    TextView Disconnected;
    EditText query;
    Button submit;
    ProgressDialog pd;

    public static final String PREF = "mypref";
    public static final String USERNAME = "usernameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        query = (EditText) findViewById(R.id.query);
        submit = (Button) findViewById(R.id.submit);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        sharedpreferences = getSharedPreferences(PREF,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(USERNAME)) {
            query.setText(sharedpreferences.getString(USERNAME, ""));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query1 = query.getText().toString();
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Searching Github Users...");
                pd.setCancelable(false);
                pd.show();
                loadJSON(query1);
                Save();
            }
        });
    }

    private void loadJSON(String query) {
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems(query);
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    pd.hide();

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Save() {
        String username = query.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USERNAME, username);
        editor.commit();
    }
}
