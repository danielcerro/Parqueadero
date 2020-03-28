package com.example.parqueadero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ListaActivity extends AppCompatActivity {
    ArrayList<Auto> arrayAuto;
    RecyclerView recycler;
    AdapterAuto adapter;
    TextView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        arrayAuto =new ArrayList<>();
        recycler=(RecyclerView) findViewById(R.id.idRecycler);
        lista=findViewById(R.id.txtlistado);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Intent i = getIntent();
        //arrayAutosmecanicos= i.getParcelableArrayListExtra("AutosArray");
       // arrayAutosautomaticos= i.getParcelableArrayListExtra("AutosArray");
        arrayAuto = i.getParcelableArrayListExtra("AutosArray");
        adapter=new AdapterAuto(arrayAuto);
        recycler.setAdapter(adapter);

    }
    private ArrayList<Auto> listarmecanicos(ArrayList<Auto> a){
        ArrayList<Auto> armec=new ArrayList<>();
        for(Auto auto:a){
            if(auto.getTipo_de_caja().equalsIgnoreCase("Mecanico")){
                armec.add(auto);
            }
        }
        return armec;
    }
    private ArrayList<Auto> listarautomatic(ArrayList<Auto> a){
        ArrayList<Auto> armec=new ArrayList<>();
        for(Auto auto:a){
            if(auto.getTipo_de_caja().equalsIgnoreCase("Automatico")){
                armec.add(auto);
            }
        }
        return armec;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuMAM:
                AdapterAuto Adaptadorauto = new AdapterAuto(listarmecanicos(arrayAuto));
                recycler.setAdapter(Adaptadorauto);
                lista.setText("Lista de autos mecanicos");
                Toast.makeText(getApplicationContext(),"Mostrar solo Mecanicos",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnuMAA:
                AdapterAuto Adaptadorautos = new AdapterAuto(listarautomatic(arrayAuto));
                recycler.setAdapter(Adaptadorautos);
                lista.setText("Lista de autos automaticos");
                Toast.makeText(getApplicationContext(),"Mostrar solo Automaticos",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnuEAA:
                new AlertDialog.Builder(this).setTitle("Confirmación")
                        .setMessage("Desea eliminar un auto aleatorio?")
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int tamaño=arrayAuto.size();
                                int Random = (int)(Math.random()*tamaño);
                                arrayAuto.remove(Random);

                                AdapterAuto AdaptadorA = new AdapterAuto(arrayAuto);
                                recycler.setAdapter(AdaptadorA);
                                Toast.makeText(getApplicationContext(),"Auto eliminado",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Auto no eliminado",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.mnuTA:
                AdapterAuto Adaptadort = new AdapterAuto(arrayAuto);
                recycler.setAdapter(Adaptadort);
                Toast.makeText(getApplicationContext(),"Todos los Autos",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
