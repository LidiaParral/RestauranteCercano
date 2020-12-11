package com.dam.seleccionrestaurante_lpr.javabean;


import android.os.Parcel;
import android.os.Parcelable;

public class OpcionRestaurante implements Parcelable {

    private String nombre;
    private String telefono;
    private String web;
    private double distancia;
    private String indicadorTrafico;

    public OpcionRestaurante(String nombre, String telefono, String web, double distancia, String indicadorTrafico) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.distancia = distancia;
        this.indicadorTrafico = indicadorTrafico;
    }

    protected OpcionRestaurante(Parcel in) {
        nombre = in.readString();
        telefono = in.readString();
        web = in.readString();
        distancia = in.readDouble();
        indicadorTrafico = in.readString();
    }

    public double calcularDistanciaRestaurante() {
        int velocidad = 0;

        if (indicadorTrafico.equalsIgnoreCase("POCO")) {
            velocidad = 80;
        } else if (indicadorTrafico.equalsIgnoreCase("NORMAL")) {
            velocidad = 60;
        } else {
            velocidad = 40;
        }
        return distancia / velocidad * 60;
    }

    public static final Creator<OpcionRestaurante> CREATOR = new Creator<OpcionRestaurante>() {
        @Override
        public OpcionRestaurante createFromParcel(Parcel in) {
            return new OpcionRestaurante(in);
        }

        @Override
        public OpcionRestaurante[] newArray(int size) {
            return new OpcionRestaurante[size];
        }
    };

    public String getNombre() {
        return nombre;
    }


    public String getTelefono() {
        return telefono;
    }


    public String getWeb() {
        return web;
    }



    public double getDistancia() {
        return distancia;
    }


    public String getIndicadorTrafico() {
        return indicadorTrafico;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(telefono);
        dest.writeString(web);
        dest.writeDouble(distancia);
        dest.writeString(indicadorTrafico);
    }
}
