package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnerQuestions extends Activity {
    Spinner Area;
    Button Inicio,Cuentas,Receptoria;
    String Agencia,Flag,Are;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);


        Area = (Spinner)findViewById(R.id.spinner);
        Inicio = (Button)findViewById(R.id.button);

        Agencia = getIntent().getStringExtra("Agencia");
        Flag = getIntent().getStringExtra("Flag");

        List<String> categories = new ArrayList<String>();
        categories.add("Seleccione area que visito");
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


                Intent intent = new Intent(SpinnerQuestions.this, Quetions.class);
                intent.putExtra("Categoria",Are);

                startActivity(intent);
            }
        });

        Cuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Are = "CuentasNuevas";


                Intent intent = new Intent(SpinnerQuestions.this, Quetions.class);
                intent.putExtra("Categoria", Are);


                startActivity(intent);
            }
        });
        Receptoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Are = "Receptoria";


                Intent intent = new Intent(SpinnerQuestions.this, Quetions.class);
                intent.putExtra("Categoria", Are);


                startActivity(intent);
            }
        });

    }
}