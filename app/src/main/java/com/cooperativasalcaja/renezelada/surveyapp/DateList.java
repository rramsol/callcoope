package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateList extends Activity {
    private ListView listView;
    List<String> tasks;
    ArrayAdapter<String> adapter;
    Button regresar, enviarRe,rangoFechas,btnlimpiar,btnBitacora,btnReporteCompleto;
    TextView tituloAgencia,txDe,textvie79,txvHasta,textView84;
    EditText inputSearch;
    String Email,lat,lon,reloj;
    Date de,hasta;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    String Agencia,Categoria, Sdesde, Shasta;
    android.widget.Button Ir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datelist);

        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat mdformat = new SimpleDateFormat("dd-M-yyyy");



        rangoFechas = (Button) findViewById(R.id.btnFechas);
        btnlimpiar = (Button) findViewById(R.id.button21);
        btnBitacora = (Button) findViewById(R.id.btnBitacora);
        btnReporteCompleto = (Button) findViewById(R.id.btnReporteCompleto);



        txDe = (TextView) findViewById(R.id.txvDe);
        textvie79 = (TextView) findViewById(R.id.textView79);
        txvHasta = (TextView) findViewById(R.id.txvHasta);
        textView84 = (TextView) findViewById(R.id.textView84);


        txDe.setVisibility(View.VISIBLE);
        textvie79.setVisibility(View.VISIBLE);
        //aqui los 3 son visibles
        txvHasta.setVisibility(View.VISIBLE);
        textView84.setVisibility(View.VISIBLE);





        regresar = (Button) findViewById(R.id.button17);
        tituloAgencia = (TextView) findViewById(R.id.textView69);

        tasks = new ArrayList<String>();

        Agencia = getIntent().getStringExtra("Agencia");
        Categoria = getIntent().getStringExtra("Categoria");
        Email = getIntent().getStringExtra("Email");

        listView = (ListView) findViewById(R.id.list_view);



        tituloAgencia.setText("Seleccione Fecha\n o \n Rango de Fechas");
        //tituloAgencia.setText("Seleccione Fecha: \n");



        progressDialog = new ProgressDialog(DateList.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference("Repuestas").child(Agencia);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                String value = (snapshot.getKey());

                Dates dates = snapshot.getValue(Dates.class);

                tasks.add(value);
                // Collections.sort(tasks);
                adapter = new ArrayAdapter<String>(DateList.this,R.layout.list_item, R.id.codigo_token,tasks);
                listView = (ListView) findViewById(R.id.list_view);
                listView.setDividerHeight(1);
                listView.setAdapter(adapter);

                progressDialog.dismiss();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        // Mensaje Toast del elemento seleccionado.

                        TextView  textview=(TextView)view.findViewById(R.id.codigo_token);
                        //textview.setTextColor(Color.RED);

                        String info = (String)parent.getItemAtPosition(position);

                        Log.d("informaFecha", info);


                        //textvie79 de
                        //textview84 hasta


                        String fechatemporal;

                        if(textvie79.getText().toString().length() > 0){

                            textView84.setText(info);


                            try {

                                de = mdformat.parse(textvie79.getText().toString());
                                hasta = mdformat.parse(textView84.getText().toString());

                                if(de.getTime() > hasta.getTime()){
                                    fechatemporal = textvie79.getText().toString();
                                    textvie79.setText(textView84.getText().toString());
                                    textView84.setText(fechatemporal);
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                           // de = mdformat.format();


                        }else if (textvie79.getText().toString().length() == 0){


                            textvie79.setText(info);
                            rangoFechas.setVisibility(View.VISIBLE);
                            btnlimpiar.setVisibility(View.VISIBLE);

                        }




                       /* Intent intent = new Intent(DateList.this,Category.class);
                        intent.putExtra("Agencia",Agencia);
                        intent.putExtra("Email",Email);
                        //intent.putExtra("Date",info);
                        startActivity(intent);*/


                    }

                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        rangoFechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(textvie79.getText().toString().length() > 0 && textView84.getText().toString().length() == 0){

                    Intent intent = new Intent(DateList.this,Category.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Date1",textvie79.getText().toString());
                    intent.putExtra("Date2","");
                    startActivity(intent);

                }else{
                    /* esto es para el rango*/
                    Intent intent = new Intent(DateList.this,AnalyticsList.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Date1",textvie79.getText().toString());
                    intent.putExtra("Date2",textView84.getText().toString());
                    intent.putExtra("Categoria","Todas las Areas");
                    startActivity(intent);

                }


            }
        });

        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textvie79.setText("");
                textView84.setText("");
                btnlimpiar.setVisibility(View.INVISIBLE);
                rangoFechas.setVisibility(View.INVISIBLE);


            }
        });

        btnBitacora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DateList.this,AnalyticsList.class);
                intent.putExtra("Agencia",Agencia);
                intent.putExtra("Email",Email);
                intent.putExtra("Date1","");
                intent.putExtra("Date2","");
                intent.putExtra("Categoria","todo");
                intent.putExtra("opcion",1);
                startActivity(intent);

            }
        });

        btnReporteCompleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DateList.this,AnalyticsList.class);
                intent.putExtra("Agencia",Agencia);
                intent.putExtra("Email",Email);
                intent.putExtra("Date1","");
                intent.putExtra("Date2","");
                intent.putExtra("Categoria","todo");
                intent.putExtra("opcion",2);
                startActivity(intent);
            }
        });



    }
}