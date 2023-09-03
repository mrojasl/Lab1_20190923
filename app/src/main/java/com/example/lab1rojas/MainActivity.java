package com.example.lab1rojas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu((TextView) findViewById(R.id.textView2));

        Button buttonJugar =  findViewById(R.id.buttonjugar);
        buttonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AhorcadoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menucontext,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        TextView textView = findViewById(R.id.textView2);
        String menuItemTitle = item.getTitle().toString();

        switch(menuItemTitle){
            case "Azul":
                textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
                return true;
            case "Verde":
                textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
                return true;
            case "Rojo":
                textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}

