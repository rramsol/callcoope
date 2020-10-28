package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BanderaRespuestas extends Activity {
    String Agencia,Categoria,Flag;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;

    String Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Agencia = getIntent().getStringExtra("Agencia");
        Categoria = getIntent().getStringExtra("Categoria");
        Flag = getIntent().getStringExtra("Flag");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        final String strDate =  mdformat.format(calendar.getTime());

        FirebaseApp.initializeApp(this);

        progressDialog = new ProgressDialog(BanderaRespuestas.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Bandera bandera = dataSnapshot.getValue(Bandera.class);

                if(dataSnapshot.exists()){

                    Flag = bandera.Bandera;

                        progressDialog.dismiss();

                        Intent intent = new Intent(BanderaRespuestas.this, InicioPreguntas.class);
                        intent.putExtra("Agencia", Agencia);
                        intent.putExtra("Flag", Flag);
                        intent.putExtra("Categoria", Categoria);
                        intent.putExtra("Pregunta1", Question1);
                        intent.putExtra("Pregunta2", Question2);
                        intent.putExtra("Pregunta3", Question3);
                        intent.putExtra("Pregunta4", Question4);
                        intent.putExtra("Pregunta5", Question5);
                        intent.putExtra("Pregunta6", Question6);
                        intent.putExtra("Pregunta6", Question6);
                        intent.putExtra("Pregunta7", Question7);
                        intent.putExtra("Pregunta8", Question8);
                        intent.putExtra("Pregunta9", Question9);
                        intent.putExtra("Pregunta10", Question10);
                        startActivity(intent);
                    }

                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            });

        }

    }
