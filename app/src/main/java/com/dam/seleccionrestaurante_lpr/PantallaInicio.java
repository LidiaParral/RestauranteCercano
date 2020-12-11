package com.dam.seleccionrestaurante_lpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PantallaInicio extends AppCompatActivity {

    EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);

        etNombre = findViewById(R.id.etNombre);
    }

    public void acceder(View view) {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast t = Toast.makeText(this, R.string.no_data, Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        } else{
            ((MyApplication) getApplication()).setUserName(nombre);
            Intent i = new Intent(this, IndicacionesActivity.class);
            startActivity(i);
        }
    }
}