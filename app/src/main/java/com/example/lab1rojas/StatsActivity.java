package com.example.lab1rojas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class StatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        LinearLayout linearLayoutStats = findViewById(R.id.linearLayoutStats);


        List<String> resultados = getIntent().getStringArrayListExtra("resultados");
        List<String> tiempos = getIntent().getStringArrayListExtra("tiempos");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("TeleAhorcado");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        for (int i = 0; i < resultados.size(); i++) {
            Log.d("asdas", "asdasdasd");

            String resultado = resultados.get(i);
            String tiempo = tiempos.get(i);

            TextView textView = new TextView(this);
            textView.setText("Partida " + (i + 1) + ": " + resultado + " en " + tiempo + " segundos");

            linearLayoutStats.addView(textView);
        }











    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}