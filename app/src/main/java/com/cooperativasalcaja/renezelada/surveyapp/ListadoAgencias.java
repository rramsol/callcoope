package com.cooperativasalcaja.renezelada.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class ListadoAgencias extends AppCompatActivity {
    android.widget.Button  Inicio, listado;
    Spinner  Agencias;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listadoagencias);

        Inicio= (Button)findViewById(R.id.button);
        email = getIntent().getStringExtra("Email");
        //listado = (Button)findViewById(R.id.button7);

        FirebaseApp.initializeApp(this);

        Agencias = (Spinner)findViewById(R.id.spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("Seleccione la agencia");
        categories.add("Central");
        categories.add("Democracia");
        categories.add("San Cristobal");
        categories.add("San Nicolas");
        categories.add("Momostenango");
        categories.add("El Carmen");
        categories.add("San Juan");
        categories.add("Coatepeque");
        categories.add("Malacatan");
        categories.add("Trinidad");
        categories.add("Cuatro Caminos");
        categories.add("Centro Historico");
        categories.add("Palestina");
        categories.add("San Andres");
        categories.add("Trigales");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Agencias.setAdapter(dataAdapter);


        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Agencias.getSelectedItem().toString().equals("Seleccione la agencia")){
                    Intent intent = new Intent(ListadoAgencias.this, Area.class);
                    intent.putExtra("Agencia",Agencias.getSelectedItem().toString());
                    intent.putExtra("Email",email);
                    intent.putExtra("Flag","0");
                    startActivity(intent);

                }else{
                    Toast.makeText(ListadoAgencias.this,"Por favor seleccione la agencia",Toast.LENGTH_LONG).show();
                }
            }
        });

       /* listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoAgencias.this, IniciarSesion.class);
                startActivity(intent);
            }
        });*/

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
        }
        return true;
    }

}
