package com.example.parqueadero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAuto extends RecyclerView.Adapter<AdapterAuto.ViewHolderAutos> {
    ArrayList<Auto> autos;

    public AdapterAuto(ArrayList<Auto> autos) {
        this.autos = autos;
    }

    @NonNull
    @Override
    public AdapterAuto.ViewHolderAutos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,null,false);
        return new ViewHolderAutos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAutos holder, int position) {
        holder.placa.setText(autos.get(position).getPlaca());
        holder.marca.setText(autos.get(position).getMarca());
        holder.modelo.setText(String.valueOf(autos.get(position).getModelo()));
        holder.tipo.setText(autos.get(position).getTipo_de_caja());
    }

    @Override
    public int getItemCount() {
        return autos.size();
    }

    public class ViewHolderAutos extends RecyclerView.ViewHolder {
            TextView placa,marca,modelo,tipo;
        public ViewHolderAutos(@NonNull View itemView) {
            super(itemView);
            placa=itemView.findViewById(R.id.idPlaca);
            marca=itemView.findViewById(R.id.idMarca);
            modelo=itemView.findViewById(R.id.idModelo);
            tipo=itemView.findViewById(R.id.idTipo);
        }
    }
}
