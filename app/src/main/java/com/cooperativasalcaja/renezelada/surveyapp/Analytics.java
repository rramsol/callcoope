package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Analytics extends Activity {
    TextView Pre1,Pre2,Pre3,Pre4,Very,Good,Medium,Bad,Vbad,Very2,Good2,Medium2,Bad2,Vbad2,Very3,Good3,Medium3,Bad3,Vbad3,Very4,Good4,Medium4,Bad4,Vbad4,Very5,Good5,Medium5,Bad5,Vbad5;
    DatabaseReference mDatabase;
    String Agencia,Categoria,Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10,MuyBueno,Bueno,Regular,Malo,MuyMalo,Suma= "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);

        Agencia = getIntent().getStringExtra("Agencia");
        Categoria= getIntent().getStringExtra("Categoria");

        Pre1 = (TextView)findViewById(R.id.textView5);
        Pre2 = (TextView)findViewById(R.id.textView16);
        Pre3 = (TextView)findViewById(R.id.textView29);
        Pre4 = (TextView)findViewById(R.id.textView39);

        Very = (TextView)findViewById(R.id.MuyBueno);
        Good = (TextView)findViewById(R.id.Bueno);
        Medium = (TextView)findViewById(R.id.Regular);
        Bad = (TextView)findViewById(R.id.Malo);
        Vbad = (TextView)findViewById(R.id.MuyMalo);

        Very2 = (TextView)findViewById(R.id.textView23);
        Good2 = (TextView)findViewById(R.id.textView24);
        Medium2 = (TextView)findViewById(R.id.textView25);
        Bad2 = (TextView)findViewById(R.id.textView26);
        Vbad2 = (TextView)findViewById(R.id.textView28);

        Very3 = (TextView)findViewById(R.id.textView34);
        Good3 = (TextView)findViewById(R.id.textView35);
        Medium3 = (TextView)findViewById(R.id.textView36);
        Bad3 = (TextView)findViewById(R.id.textView37);
        Vbad3 = (TextView)findViewById(R.id.textView38);

        Very4 = (TextView)findViewById(R.id.textView46);
        Good4 = (TextView)findViewById(R.id.textView47);
        Medium4 = (TextView)findViewById(R.id.textView48);
        Bad4 = (TextView)findViewById(R.id.textView49);
        Vbad4 = (TextView)findViewById(R.id.textView50);




        Question1 = getIntent().getStringExtra("Pregunta1");
        Question2 = getIntent().getStringExtra("Pregunta2");
        Question3 = getIntent().getStringExtra("Pregunta3");
        Question4 = getIntent().getStringExtra("Pregunta4");
        Question5 = getIntent().getStringExtra("Pregunta5");
        Question6 = getIntent().getStringExtra("Pregunta6");
        Question7 = getIntent().getStringExtra("Pregunta7");
        Question8 = getIntent().getStringExtra("Pregunta8");
        Question9 = getIntent().getStringExtra("Pregunta9");
        Question10 = getIntent().getStringExtra("Pregunta10");


        Pre1.setText(Question1);
        Pre2.setText(Question2);
        Pre3.setText(Question3);
        Pre4.setText(Question4);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        final String strDate =  mdformat.format(calendar.getTime());

        FirebaseApp.initializeApp(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Repuestas").child("Central").child("2019-04-08").child(Question1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Calificacion calificacion = dataSnapshot.getValue(Calificacion.class);

                if(dataSnapshot.exists()){
                    MuyBueno = calificacion.MuyBueno;
                    Bueno= calificacion.Bueno;
                    Regular= calificacion.Regular;
                    Malo= calificacion.Malo;
                    MuyMalo= calificacion.MuyMalo;

                    Very.setText(MuyBueno);
                    Good.setText(Bueno);
                    Medium.setText(Regular);
                    Bad.setText(Malo);
                    Vbad.setText(MuyMalo);


                }else{
                    Very.setText("Nada");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Repuestas").child("Central").child("2019-04-08").child(Question2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Calificacion calificacion = dataSnapshot.getValue(Calificacion.class);

                if(dataSnapshot.exists()){
                    MuyBueno = calificacion.MuyBueno;
                    Bueno= calificacion.Bueno;
                    Regular= calificacion.Regular;
                    Malo= calificacion.Malo;
                    MuyMalo= calificacion.MuyMalo;

                    Very2.setText(MuyBueno);
                    Good2.setText(Bueno);
                    Medium2.setText(Regular);
                    Bad2.setText(Malo);
                    Vbad2.setText(MuyMalo);

                }else{
                    Very.setText("Nada");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Repuestas").child("Central").child("2019-04-08").child(Question3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Calificacion calificacion = dataSnapshot.getValue(Calificacion.class);

                if(dataSnapshot.exists()){
                    MuyBueno = calificacion.MuyBueno;
                    Bueno= calificacion.Bueno;
                    Regular= calificacion.Regular;
                    Malo= calificacion.Malo;
                    MuyMalo= calificacion.MuyMalo;

                    Very3.setText(MuyBueno);
                    Good3.setText(Bueno);
                    Medium3.setText(Regular);
                    Bad3.setText(Malo);
                    Vbad3.setText(MuyMalo);

                }else{
                    Very.setText("Nada");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Repuestas").child("Central").child("2019-04-08").child(Question4).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Calificacion calificacion = dataSnapshot.getValue(Calificacion.class);

                if(dataSnapshot.exists()){
                    MuyBueno = calificacion.MuyBueno;
                    Bueno= calificacion.Bueno;
                    Regular= calificacion.Regular;
                    Malo= calificacion.Malo;
                    MuyMalo= calificacion.MuyMalo;

                    Very4.setText(MuyBueno);
                    Good4.setText(Bueno);
                    Medium4.setText(Regular);
                    Bad4.setText(Malo);
                    Vbad4.setText(MuyMalo);

                }else{
                    Very.setText("Nada");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
