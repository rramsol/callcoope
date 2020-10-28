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
import java.util.List;

public class Category extends Activity {
    private ListView listView;
    List<String> tasks;
    ArrayAdapter<String> adapter;
    TextView tituloAgencia,txDe,textvie79,txvHasta,textView84;
    EditText inputSearch;
    Button regresar, enviarRe, rangoFechas, btnBitacora, btnReporteCompleto;
    String Email,lat,lon,reloj;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    String Agencia,DateDesde,DateHasta;
    android.widget.Button Ir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datelist);

        rangoFechas = (Button) findViewById(R.id.btnFechas);
        btnBitacora = (Button) findViewById(R.id.btnBitacora);
        txDe = (TextView) findViewById(R.id.txvDe);
        textvie79 = (TextView) findViewById(R.id.textView79);
        txvHasta = (TextView) findViewById(R.id.txvHasta);
        textView84 = (TextView) findViewById(R.id.textView84);
        btnReporteCompleto = (Button) findViewById(R.id.btnReporteCompleto);


        rangoFechas.setVisibility(View.INVISIBLE);
        txDe.setVisibility(View.INVISIBLE);
        textvie79.setVisibility(View.INVISIBLE);
        txvHasta.setVisibility(View.INVISIBLE);
        textView84.setVisibility(View.INVISIBLE);
        btnBitacora.setVisibility(View.INVISIBLE);
        btnReporteCompleto.setVisibility(View.INVISIBLE);



        regresar = (Button) findViewById(R.id.button17);
        tasks = new ArrayList<String>();
        Agencia = getIntent().getStringExtra("Agencia");
        DateDesde  = getIntent().getStringExtra("Date1");
        DateHasta  = getIntent().getStringExtra("Date2");
        Email = getIntent().getStringExtra("Email");
        tituloAgencia = (TextView) findViewById(R.id.textView69);





        tituloAgencia.setText("Seleccione ÁREA");


        listView = (ListView) findViewById(R.id.list_view);


        progressDialog = new ProgressDialog(Category.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if(DateHasta.length() == 0 || DateDesde.equals(DateHasta)){
            DatabaseReference myRef = database.getReference("Repuestas").child(Agencia).child(DateDesde);

            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    String value = (snapshot.getKey());

                    Dates dates = snapshot.getValue(Dates.class);

                    tasks.add(value);
                    // Collections.sort(tasks);
                    adapter = new ArrayAdapter<String>(Category.this,R.layout.list_item, R.id.codigo_token,tasks);
                    listView = (ListView) findViewById(R.id.list_view);
                    listView.setDividerHeight(1);
                    listView.setAdapter(adapter);

                    progressDialog.dismiss();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {
                            // Mensaje Toast del elemento seleccionado.

                            TextView textview=(TextView)view.findViewById(R.id.codigo_token);
                            textview.setTextColor(Color.RED);

                            String info = (String)parent.getItemAtPosition(position);
/*
                        int inicio = info.indexOf(".");
                        int fin = info.indexOf(" ", inicio + 1);

                        info =  info.substring(inicio + 1, fin);

*/
                            Intent intent = new Intent(Category.this,AnalyticsList.class);
                            intent.putExtra("Agencia",Agencia);
                            intent.putExtra("Date1",DateDesde);
                            intent.putExtra("Date2","");
                            intent.putExtra("Email",Email);
                            intent.putExtra("Categoria",info);
                            intent.putExtra("bitacora","");
                            startActivity(intent);

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

        }else{

            // aqui va el rango

            DatabaseReference myRef = database.getReference("Repuestas").child(Agencia).child(DateDesde);

            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    String value = (snapshot.getKey());

                    Dates dates = snapshot.getValue(Dates.class);

                    tasks.add(value);
                    // Collections.sort(tasks);
                    adapter = new ArrayAdapter<String>(Category.this,R.layout.list_item, R.id.codigo_token,tasks);
                    listView = (ListView) findViewById(R.id.list_view);
                    listView.setDividerHeight(1);
                    listView.setAdapter(adapter);

                    progressDialog.dismiss();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {
                            // Mensaje Toast del elemento seleccionado.

                            TextView textview=(TextView)view.findViewById(R.id.codigo_token);
                            textview.setTextColor(Color.RED);

                            String info = (String)parent.getItemAtPosition(position);
                            Intent intent = new Intent(Category.this,AnalyticsList.class);
                            intent.putExtra("Agencia",Agencia);
                            intent.putExtra("Date1",DateDesde);
                            intent.putExtra("Date2",DateHasta);
                            intent.putExtra("Email",Email);
                            intent.putExtra("Categoria",info);
                            startActivity(intent);

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


        }

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }
}