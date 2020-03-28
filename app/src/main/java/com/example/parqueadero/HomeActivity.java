package com.example.parqueadero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
//
public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    GoogleSignInClient mGoogleSignInClient;
    DatePickerDialog picker;
    TextView dateText;
    ArrayList<Auto> autos;
    FloatingActionButton add,listado;
    EditText edplaca;
    EditText edmarca;
    int año;
    RadioButton rbtipo;
    RadioGroup rbg;
    String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        autos=new ArrayList<>();
        edplaca=findViewById(R.id.edPlaca);
        edmarca=findViewById(R.id.edMarca);
        dateText =findViewById(R.id.edAÑO);

        rbg= findViewById(R.id.rgb);
        rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbnAutomatic){
                    tipo="Automatico";
                }else if (checkedId==R.id.rbnMecanic){
                    tipo="Mecanico";
                }
            }
        });

        dateText.setInputType(InputType.TYPE_NULL);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDatePickerDailog();
               // createDialogWithoutDateField().show();
            }
        });
        add=findViewById(R.id.fbtnadd);
        listado=findViewById(R.id.fbtnlistado);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });
    }
    public void Guardar() {
                AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("Confirmación");
                builder.setMessage("Desea agregar este auto?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        autos.add(new Auto(edplaca.getText().toString(),edmarca.getText().toString(),año,tipo));
                                        edplaca.setText("");edmarca.setText("");dateText.setText("Seleccione año");
                                        Toast.makeText(getApplicationContext(),"Auto agregada exitosamente",Toast.LENGTH_LONG).show();
                                    }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        edplaca.setText("");edmarca.setText("");dateText.setText("Seleccione año");
                                        Toast.makeText(getApplicationContext(),"Auto no agregada",Toast.LENGTH_LONG).show();}
                        }).show();
        }

    private void listar(){
        Intent i = new Intent(getApplicationContext(),ListaActivity.class);
        i.putParcelableArrayListExtra("AutosArray", autos);
        startActivity(i);
    }
   /* private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }*/
    private void showDatePickerDailog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR), 0, 0);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateText.setText(String.valueOf(year));
        año=year;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
