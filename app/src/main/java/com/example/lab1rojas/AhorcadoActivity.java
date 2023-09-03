package com.example.lab1rojas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;



public class AhorcadoActivity extends AppCompatActivity {

    private String[] palabras = {"REDES", "PROPA", "PUCP", "TELITO", "TELECO", "BATI"};
    private String palabraSeleccionada;
    private TextView barrasTextView;
    private int letrasIncorrectas = 0;
    private ImageView[] partesDelCuerpo;
    private String letrasAdivinadas;
    private boolean juegoTerminado = false;
    private long tiempoInicio;
    private long tiempoFin;
    private TextView ganasteTextView;
    private TextView perdisteTextView;
    private List<Partida> listaDePartidas;
    private int numeroPartida = 1;
    List<String> resultados = new ArrayList<>();
    List<String> tiempos = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);

        palabraSeleccionada = seleccionarPalabraAleatoria(palabras);
        letrasAdivinadas = new String(new char[palabraSeleccionada.length()]).replace('\0', '_');
        ganasteTextView = findViewById(R.id.ganasteTextView);
        perdisteTextView = findViewById(R.id.perdisteTextView);
        barrasTextView = findViewById(R.id.barrasTextView);
        generarBarras();


        Button nuevoJuegoButton = findViewById(R.id.button28);
        nuevoJuegoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarActividad();
            }
        });

        partesDelCuerpo = new ImageView[]{
                findViewById(R.id.imageView4), // Cabeza
                findViewById(R.id.imageView5), // Torso
                findViewById(R.id.imageView6), // Brazo derecho
                findViewById(R.id.imageView7), // Brazo izquierdo
                findViewById(R.id.imageView8), // Pierna izquierda
                findViewById(R.id.imageView9)  // Pierna derecha
        };









        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("TeleAhorcado");
            actionBar.setDisplayHomeAsUpEnabled(true);


            Button customButton = new Button(this);
            customButton.setText("Estadisticas");

            actionBar.setCustomView(customButton);
            actionBar.setDisplayShowCustomEnabled(true);

            customButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), StatsActivity.class);
                    intent.putStringArrayListExtra("resultados", new ArrayList<>(resultados));
                    intent.putStringArrayListExtra("tiempos", new ArrayList<>(tiempos));
                    startActivity(intent);
                }
            });
        }
















        tiempoInicio = SystemClock.elapsedRealtime();

    }

    private String seleccionarPalabraAleatoria(String[] palabras) {
        Random random = new Random();
        int indicePalabraAleatoria = random.nextInt(palabras.length);
        return palabras[indicePalabraAleatoria];
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void generarBarras() {
        StringBuilder barras = new StringBuilder();
        for (int i = 0; i < palabraSeleccionada.length(); i++) {
            barras.append("_ ");
        }
        barrasTextView.setText(barras.toString());
    }

    public void letraPresionada(View view) {
        if (!juegoTerminado) {

            Button boton = (Button) view;
            String letraPresionada = boton.getText().toString();

            if (palabraSeleccionada.contains(letraPresionada)) {
                //caso en que acierta la letra
                boton.setVisibility(View.GONE);
                actualizarLetrasAdivinadas(letraPresionada);

                if (todasLetrasAdivinadas()) {
                    finalizarJuego(true);

                    juegoTerminado = true;
                    Toast.makeText(this, "GANASTE SUELTA TU GAAAAA", Toast.LENGTH_SHORT).show();
                }

            } else {
                //caso en que se equivoca de letra
                boton.setVisibility(View.GONE);
                mostrarSiguienteParteDelCuerpo();
                if (letrasIncorrectas >= partesDelCuerpo.length) {
                    finalizarJuego(false);
                    juegoTerminado = true;
                    Toast.makeText(this, "perdiste, la palabra era: " + palabraSeleccionada, Toast.LENGTH_SHORT).show();

                }
            }
        } else {
            Toast.makeText(this, "ya para pe el juego acabó", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarSiguienteParteDelCuerpo() {
        if (letrasIncorrectas < partesDelCuerpo.length) {
            Log.d("Ahorcado", "Mostrando parte del cuerpo");
            letrasIncorrectas++;
            partesDelCuerpo[letrasIncorrectas - 1].setVisibility(View.VISIBLE);
        }
    }

    private void actualizarLetrasAdivinadas(String letra) {
        for (int i = 0; i < palabraSeleccionada.length(); i++) {
            if (palabraSeleccionada.charAt(i) == letra.charAt(0)) {
                letrasAdivinadas = letrasAdivinadas.substring(0, i) + letra + letrasAdivinadas.substring(i + 1);
            }
        }
        barrasTextView.setText(letrasAdivinadas);
    }

    private boolean todasLetrasAdivinadas() {
        return letrasAdivinadas.equals(palabraSeleccionada);
    }

    private void finalizarJuego(boolean ganaste) {
        if (juegoTerminado) {
            return;
        }
        tiempoFin = SystemClock.elapsedRealtime();
        long tiempoTranscurrido = tiempoFin - tiempoInicio;




        if (ganaste) {
            ganasteTextView.setVisibility(View.VISIBLE);
            ganasteTextView.setText("Ganó / Terminó en " + tiempoTranscurrido / 1000 + " segundos");
            resultados.add("Ganó");
            tiempos.add(tiempoTranscurrido + " segundos");

        } else {
            perdisteTextView.setVisibility(View.VISIBLE);
            perdisteTextView.setText("Perdió / Terminó en " + tiempoTranscurrido / 1000 + " segundos");
            resultados.add("Perdió");
            tiempos.add(tiempoTranscurrido + " segundos");
        }

        juegoTerminado = true;
    }

    private void reiniciarActividad() {
        Intent intent = getIntent();
        finish();
        Toast.makeText(this, "reiniciando...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}
