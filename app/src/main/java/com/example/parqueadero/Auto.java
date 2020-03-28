package com.example.parqueadero;

import android.os.Parcel;
import android.os.Parcelable;

public class Auto implements Parcelable {
    private String placa;
    private String marca;
    private int modelo;
    private String tipo_de_caja;


    public Auto(String placa, String marca, int modelo, String tipo_de_caja) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_de_caja = tipo_de_caja;
    }

    protected Auto(Parcel in) {
        placa = in.readString();
        marca = in.readString();
        modelo = in.readInt();
        tipo_de_caja = in.readString();
    }

    public static final Creator<Auto> CREATOR = new Creator<Auto>() {
        @Override
        public Auto createFromParcel(Parcel in) {
            return new Auto(in);
        }

        @Override
        public Auto[] newArray(int size) {
            return new Auto[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placa);
        dest.writeString(marca);
        dest.writeInt(modelo);
        dest.writeString(tipo_de_caja);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getTipo_de_caja() {
        return tipo_de_caja;
    }

    public void setTipo_de_caja(String tipo_de_caja) {
        this.tipo_de_caja = tipo_de_caja;
    }



}
