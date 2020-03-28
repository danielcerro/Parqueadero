package com.example.parqueadero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    Button ingresar;
    EditText usuario, contraseña;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//SIGN IN NORMAL
        usuario = (EditText) findViewById(R.id.edtusuario);
        contraseña = (EditText) findViewById(R.id.edtpassword);

        ingresar = findViewById(R.id.btningresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"Boton Activado", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(usuario.getText())){
                    usuario.setHint("Usuario Incorrecto");
                }
                else{
                    if(TextUtils.isEmpty(contraseña.getText())){
                        contraseña.setHint("Password Incorrecto");
                    }
                    else{
                        if(usuario.getText().toString().equals("admin") && contraseña.getText().toString().equals("12345")) {
                            //Toast.makeText(getApplicationContext(),"Usuario Correcto", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            i.putExtra("user",usuario.getText().toString());
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    }

