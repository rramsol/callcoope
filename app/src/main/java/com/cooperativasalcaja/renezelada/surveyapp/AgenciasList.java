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
import java.util.List;

public class AgenciasList extends Activity {
    private ListView listView;
    List<String> tasks;
    ArrayAdapter<String> adapter;
    TextView tituloAgencia;
    EditText inputSearch;
    String Email,lat,lon,reloj;
    Button regresar, enviarRe,rangoFechas;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    String Agencia;
    android.widget.Button Ir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datelist);

        /*rangoFechas = (Button) findViewById(R.id.button20);*/
        regresar = (Button) findViewById(R.id.button17);
        tituloAgencia = (TextView) findViewById(R.id.textView69);
        listView = (ListView) findViewById(R.id.list_view);
        tasks = new ArrayList<String>();

        Email = getIntent().getStringExtra("Email");

        /*rangoFechas.setVisibility(View.GONE);*/



        tituloAgencia.setText("Seleccione Agencia");

        progressDialog = new ProgressDialog(AgenciasList.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference("Repuestas");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                String value = (snapshot.getKey());

                Dates dates = snapshot.getValue(Dates.class);

                tasks.add(value);
                // Collections.sort(tasks);
                adapter = new ArrayAdapter<String>(AgenciasList.this,R.layout.list_item, R.id.codigo_token,tasks);
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
                        String titu = "Agencia "+ info;

                        Intent intent = new Intent(AgenciasList.this,DateList.class);
                        intent.putExtra("Agencia",info);
                        intent.putExtra("Email",Email);
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

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }
}