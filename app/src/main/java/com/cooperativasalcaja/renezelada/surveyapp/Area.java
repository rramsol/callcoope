package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Area extends Activity {
    Spinner  Area;
    Button Inicio,Cuentas,Receptoria;
    String Agencia,Flag,Are,email;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);

        //Bitacora

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        final String strDate =  mdformat.format(calendar.getTime());

        FirebaseApp.initializeApp(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();




        Area = (Spinner)findViewById(R.id.spinner);
        Inicio = (Button)findViewById(R.id.button);
        Cuentas = (Button)findViewById(R.id.button14);
        Receptoria = (Button)findViewById(R.id.button15);

        Agencia = getIntent().getStringExtra("Agencia");
        email = getIntent().getStringExtra("Email");
        Flag = getIntent().getStringExtra("Flag");

        List<String> categories = new ArrayList<String>();
        categories.add("Seleccione el area que visito");
        categories.add("Cuentas Nuevas");
        categories.add("Creditos");
        categories.add("Receptoria");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Area.setAdapter(dataAdapter);


        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Are = "Creditos";


                mDatabase.child("Bitacora").child(Agencia).child(Are).child(strDate).setValue("");

                  Intent intent = new Intent(Area.this, InicioPreguntas.class);
                  intent.putExtra("Categoria", Are);
                  intent.putExtra("Agencia", Agencia);
                  intent.putExtra("Email", email);
                  intent.putExtra("Flag", Flag);
                  startActivity(intent);

            }
        });
        Cuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Are = "CuentasNuevas";

                mDatabase.child("Bitacora").child(Agencia).child(Are).child(strDate).setValue("");
                Intent intent = new Intent(Area.this, InicioPreguntas.class);
                intent.putExtra("Categoria", Are);

                intent.putExtra("Agencia", Agencia);
                intent.putExtra("Email", email);
                intent.putExtra("Flag", Flag);

                startActivity(intent);
            }
        });
        Receptoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Are = "Receptoria";

                mDatabase.child("Bitacora").child(Agencia).child(Are).child(strDate).setValue("");
                Intent intent = new Intent(Area.this, InicioPreguntas.class);
                intent.putExtra("Categoria", Are);
                intent.putExtra("Agencia", Agencia);
                intent.putExtra("Email", email);
                intent.putExtra("Flag", Flag);

                startActivity(intent);
            }
        });

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
        }
        return true;
    }

    }
