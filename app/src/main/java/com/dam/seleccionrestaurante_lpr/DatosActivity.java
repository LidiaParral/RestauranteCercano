package com.dam.seleccionrestaurante_lpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.seleccionrestaurante_lpr.javabean.OpcionRestaurante;

public class DatosActivity extends AppCompatActivity {
    EditText etNom;
    EditText etTlf;
    EditText etWeb;
    EditText etDistancia;
    RadioButton rdPoco;
    RadioButton rdNormal;
    RadioButton rdMucho;
    TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        etNom = findViewById(R.id.etNombre);
        etTlf = findViewById(R.id.etTelefono);
        etWeb = findViewById(R.id.etWeb);
        etDistancia = findViewById(R.id.etDistancia);
        rdPoco = findViewById(R.id.rbPoco);
        rdNormal = findViewById(R.id.rbNormal);
        rdMucho = findViewById(R.id.rbMucho);
        tvDatos = findViewById(R.id.tvDatos);

        rdPoco.setChecked(true);

        Bundle extras = getIntent().getExtras();
        String resNum = extras.getString("REST_NUM");

        String nombre = ((MyApplication) getApplication()).getUserName();
        Resources res = getResources();
        String texto = String.format(res.getString(R.string.tv_datos), nombre, resNum);
        tvDatos.setText(texto);

    }

    public void aceptar(View view) {
        Intent i = new Intent();
        OpcionRestaurante rest = obtenerDatos();
        if (rest != null) {
            i.putExtra("RESTAURANTE", rest);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public OpcionRestaurante obtenerDatos() {
        OpcionRestaurante or = null;

        String nombre = etNom.getText().toString().trim();
        String telefono = etTlf.getText().toString().trim();
        String web = etWeb.getText().toString().trim();
        String distancia = etDistancia.getText().toString().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || web.isEmpty() || distancia.isEmpty()) {
            Toast t = Toast.makeText(this, R.string.no_datos, Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        } else {
            try {
                double dist = Double.parseDouble(distancia);

                String indicadorTrafico = "";

                if (rdPoco.isChecked()) {
                    indicadorTrafico = "POCO";
                } else if (rdNormal.isChecked()) {
                    indicadorTrafico = "NORMAL";
                } else if (rdMucho.isChecked()) {
                    indicadorTrafico = "MUCHO";
                }

                or = new OpcionRestaurante(nombre, telefono, web, dist, indicadorTrafico);

            } catch (NumberFormatException e) {
                Toast t = Toast.makeText(this, R.string.msg_dist_incorrecta, Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        }

        return or;
    }

}