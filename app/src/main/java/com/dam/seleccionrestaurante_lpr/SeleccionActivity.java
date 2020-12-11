package com.dam.seleccionrestaurante_lpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.seleccionrestaurante_lpr.javabean.OpcionRestaurante;

public class SeleccionActivity extends AppCompatActivity {

    TextView tvNombre;
    TextView tvTelf;
    TextView tvWeb;
    TextView tvDistancia;
    TextView tvTiempo;
    TextView tvSeleccion;
    OpcionRestaurante rest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        tvNombre = findViewById(R.id.tvNomRest);
        tvTelf = findViewById(R.id.tvTelefono);
        tvWeb = findViewById(R.id.tvWeb);
        tvDistancia = findViewById(R.id.tvDistancia);
        tvTiempo = findViewById(R.id.tvTiempo);
        tvSeleccion = findViewById(R.id.tvSeleccion);

        rest = getIntent().getParcelableExtra("RESTAURANTE_CERCA");

        Resources res = getResources();
        tvNombre.setText(String.format(res.getString(R.string.tv_nom_tarda_menos), rest.getNombre()));
        tvTelf.setText(String.format(res.getString(R.string.tv_tlf_tarda_menos), rest.getTelefono()));
        tvWeb.setText(String.format(res.getString(R.string.tv_web_tarda_menos), rest.getWeb()));
        tvDistancia.setText(String.format(res.getString(R.string.tv_dist_tarda_menos), String.valueOf(rest.getDistancia())));
        tvTiempo.setText(String.format(res.getString(R.string.tv_tiempo), String.valueOf(rest.calcularDistanciaRestaurante())));

        String nombre = ((MyApplication)getApplication()).getUserName();
        String texto = String.format(res.getString(R.string.tv_seleccion), nombre);
        tvSeleccion.setText(texto);



    }

    public void llamar(View view) {
        Uri telf = Uri.parse("tel:" + rest.getTelefono());
        Intent llamada = new Intent(Intent.ACTION_DIAL, telf);
        if (llamada.resolveActivity(getPackageManager()) != null) {
            startActivity(llamada);
        } else {
            Toast.makeText(this, R.string.no_activity, Toast.LENGTH_LONG).show();
        }
    }

    public void abrirWeb(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + rest.getWeb() + "/"));

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);

        } else {
            Toast.makeText(this, R.string.no_activity, Toast.LENGTH_LONG).show();
        }
    }

    public void volver(View view) {
        Intent intent = new Intent(getApplicationContext(), PantallaInicio.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }
}