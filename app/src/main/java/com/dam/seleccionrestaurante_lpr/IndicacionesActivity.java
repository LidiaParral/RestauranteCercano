package com.dam.seleccionrestaurante_lpr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.seleccionrestaurante_lpr.javabean.OpcionRestaurante;

public class IndicacionesActivity extends AppCompatActivity {

    static final int COD_OBTENER_DATOS1 = 1;
    static final int COD_OBTENER_DATOS2 = 2;
    static Resources res;


    TextView tvIndicaciones;
    TextView tvNom;
    TextView tvWeb;
    TextView tvTelefono;
    TextView tvNom2;
    TextView tvWeb2;
    TextView tvTelefono2;
    Button btnRestaurante1;
    Button btnRestaurante2;
    String nombre;
    OpcionRestaurante or1;
    OpcionRestaurante or2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicaciones);

        tvIndicaciones = findViewById(R.id.tvIndicaciones);

        nombre = ((MyApplication) getApplication()).getUserName();

        res = getResources();
        String texto = String.format(res.getString(R.string.tv_indicaciones), nombre);
        tvIndicaciones.setText(texto);

        tvNom = findViewById(R.id.tvNombre1);
        tvWeb = findViewById(R.id.tvTelefono1);
        tvTelefono = findViewById(R.id.tvWeb1);

        tvNom2 = findViewById(R.id.tvNombre2);
        tvWeb2 = findViewById(R.id.tvTelefono2);
        tvTelefono2 = findViewById(R.id.tvWeb2);

        btnRestaurante1 = findViewById(R.id.btnRestaurante1);
        btnRestaurante2 = findViewById(R.id.btnRestaurante2);

    }

    public void restaurante1(View view) {
        Intent i = new Intent(this, DatosActivity.class);
        i.putExtra("REST_NUM", "primer");
        startActivityForResult(i, COD_OBTENER_DATOS1);
    }

    public void restaurante2(View view) {
        Intent i = new Intent(this, DatosActivity.class);
        i.putExtra("REST_NUM", "segundo");
        startActivityForResult(i, COD_OBTENER_DATOS2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_OBTENER_DATOS1 && resultCode == RESULT_OK) {
            or1 = data.getParcelableExtra("RESTAURANTE");
            if (or1 != null) {
                tvNom.setText(String.format(res.getString(R.string.tv_nombre_rest), or1.getNombre()));
                tvWeb.setText(String.format(res.getString(R.string.tv_web_rest), or1.getWeb()));
                tvTelefono.setText(String.format(res.getString(R.string.tv_tlf_rest), or1.getTelefono()));
                btnRestaurante1.setEnabled(false);
            }
        }

        if (requestCode == COD_OBTENER_DATOS2 && resultCode == RESULT_OK) {
            or2 = data.getParcelableExtra("RESTAURANTE");
            if (or2 != null) {
                tvNom2.setText(String.format(res.getString(R.string.tv_nom_rest2), or2.getNombre()));
                tvWeb2.setText(String.format(res.getString(R.string.tv_web_rest2), or2.getWeb()));
                tvTelefono2.setText(String.format(res.getString(R.string.tv_tlf_rest2), or2.getTelefono()));
                btnRestaurante2.setEnabled(false);
            }
        }
    }

    public OpcionRestaurante calcularRestaurante() {
        OpcionRestaurante restCercano = null;

        Double tiempoRest1 = or1.calcularDistanciaRestaurante();
        Double tiempoRest2 = or2.calcularDistanciaRestaurante();

        if (tiempoRest1 < tiempoRest2) {
            restCercano = or1;
        } else {
            restCercano = or2;
        }

        return restCercano;
    }

    public void mostrarResultado(View view) {
        if (or1 == null || or2 == null) {
            Toast t = Toast.makeText(this, R.string.no_datos, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        } else {
            OpcionRestaurante rest = calcularRestaurante();

            Intent i = new Intent(this, SeleccionActivity.class);
            i.putExtra("RESTAURANTE_CERCA", rest);
            startActivity(i);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("REST1", or1);
        outState.putParcelable("REST2", or2);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        or1 = savedInstanceState.getParcelable("REST1");
        if (or1 != null) {
            tvNom.setText(String.format(res.getString(R.string.tv_nombre_rest), or1.getNombre()));
            tvWeb.setText(String.format(res.getString(R.string.tv_web_rest), or1.getWeb()));
            tvTelefono.setText(String.format(res.getString(R.string.tv_tlf_rest), or1.getTelefono()));
            btnRestaurante1.setEnabled(false);
        }

        or2 = savedInstanceState.getParcelable("REST2");
        if (or2 != null) {
            tvNom2.setText(String.format(res.getString(R.string.tv_nom_rest2), or2.getNombre()));
            tvWeb2.setText(String.format(res.getString(R.string.tv_web_rest2), or2.getWeb()));
            tvTelefono2.setText(String.format(res.getString(R.string.tv_tlf_rest2), or2.getTelefono()));
            btnRestaurante2.setEnabled(false);
        }
    }
}