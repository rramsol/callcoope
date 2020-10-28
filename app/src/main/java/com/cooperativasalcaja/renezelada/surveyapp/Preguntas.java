package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Text;


public class Preguntas extends Activity {
    Button Very,Good,Medium,Bad,VeryBad;
    Integer very,good,medium,bad,verybad;
    TextView Pregunta,Conteo;
     DatabaseReference mDatabase;
    String Ch;
    TextView Autorizado;
    android.widget.Button Guardar;
    ProgressDialog progressDialog;
    Vibrator vibe;
    Integer x = 1;
    String Categoria,Agencia,Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10,MuyBueno,Bueno,Regular,Malo,MuyMalo,Suma= "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Pregunta = (TextView)findViewById(R.id.textView);
        Very = (Button)findViewById(R.id.button2);
        Good = (Button)findViewById(R.id.button6);
        Medium = (Button)findViewById(R.id.button4);
        Bad = (Button)findViewById(R.id.button5);
        VeryBad = (Button)findViewById(R.id.button3);

        Agencia = getIntent().getStringExtra("Agencia");
        Categoria = getIntent().getStringExtra("Categoria");

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

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-M-yyyy");
        final String strDate =  mdformat.format(calendar.getTime());

        Pregunta.setText(Question1);


        progressDialog = new ProgressDialog(Preguntas.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        FirebaseApp.initializeApp(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Calificacion calificacion = dataSnapshot.getValue(Calificacion.class);

                if(dataSnapshot.exists()){
                    MuyBueno = calificacion.MuyBueno;
                    Bueno= calificacion.Bueno;
                    Regular= calificacion.Regular;
                    Malo= calificacion.Malo;
                    MuyMalo= calificacion.MuyMalo;

                    progressDialog.dismiss();
                    }
                    else{
                    if (Question1 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question2 != null) {

                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question3 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question4 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question5 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question6 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question7 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question8 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question9 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }
                    if (Question10 != null) {
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("MuyBueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Bueno").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Regular").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Malo").setValue("0");
                        mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("MuyMalo").setValue("0");
                        progressDialog.dismiss();
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Very.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            vibe.vibrate(100);
if(!Pregunta.getText().toString().equals("")){
                            Suma = String.valueOf(Integer.parseInt(MuyBueno) + 1);
                            mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyBueno").setValue(Suma);

                            if(Question2 != null)
                            {
                                Intent intent = new Intent(Preguntas.this, Pregunta2.class);
                                intent.putExtra("Agencia",Agencia);
                                intent.putExtra("Categoria",Categoria);
                                intent.putExtra("Pregunta1",Question1);
                                intent.putExtra("Pregunta2",Question2);
                                intent.putExtra("Pregunta3",Question3);
                                intent.putExtra("Pregunta4",Question4);
                                intent.putExtra("Pregunta5",Question5);
                                intent.putExtra("Pregunta6",Question6);
                                intent.putExtra("Pregunta6",Question6);
                                intent.putExtra("Pregunta7",Question7);
                                intent.putExtra("Pregunta8",Question8);
                                intent.putExtra("Pregunta9",Question9);
                                intent.putExtra("Pregunta10",Question10);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(Preguntas.this, Agradecimiento.class);
                                intent.putExtra("Agencia",Agencia);
                                intent.putExtra("Pregunta1",Question1);
                                intent.putExtra("Pregunta2",Question2);
                                intent.putExtra("Pregunta3",Question3);
                                intent.putExtra("Pregunta4",Question4);
                                intent.putExtra("Pregunta5",Question5);
                                intent.putExtra("Pregunta6",Question6);
                                intent.putExtra("Pregunta6",Question6);
                                intent.putExtra("Pregunta7",Question7);
                                intent.putExtra("Pregunta8",Question8);
                                intent.putExtra("Pregunta9",Question9);
                                intent.putExtra("Pregunta10",Question10);
                                startActivity(intent);
                            }} else {Toast.makeText(Preguntas.this,"Verifica tu conexion a internet",Toast.LENGTH_LONG).show();
}



                        }
         });

        Good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(100);
if(!Pregunta.getText().toString().equals("")){
                Suma = String.valueOf(Integer.parseInt(Bueno) + 1);
                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Bueno").setValue(Suma);

                if(Question2 != null)
                {
                    Intent intent = new Intent(Preguntas.this, Pregunta2.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Categoria",Categoria);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Preguntas.this, Agradecimiento.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }} else {Toast.makeText(Preguntas.this,"Verifica tu conexion a internet",Toast.LENGTH_LONG).show();
}
            }
        });
        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(100);
if(!Pregunta.getText().toString().equals("")){
                Suma = String.valueOf(Integer.parseInt(Regular) + 1);
                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Regular").setValue(Suma);

                if(Question2 != null)
                {
                    Intent intent = new Intent(Preguntas.this, Pregunta2.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Categoria",Categoria);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Preguntas.this, Agradecimiento.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }}
else {Toast.makeText(Preguntas.this,"Verifica tu conexion a internet",Toast.LENGTH_LONG).show();
}
            }
        });
        Bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(100);
if(!Pregunta.getText().toString().equals("")){
                Suma = String.valueOf(Integer.parseInt(Malo) + 1);
                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Malo").setValue(Suma);

                if(Question2 != null)
                {
                    Intent intent = new Intent(Preguntas.this, Pregunta2.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Categoria",Categoria);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Preguntas.this, Agradecimiento.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }} else {Toast.makeText(Preguntas.this,"Verifica tu conexion a internet",Toast.LENGTH_LONG).show();
}
            }
        });
        VeryBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(100);
if(!Pregunta.getText().toString().equals("")){
                Suma = String.valueOf(Integer.parseInt(MuyMalo) + 1);
                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyMalo").setValue(Suma);

                if(Question2 != null)
                {
                    Intent intent = new Intent(Preguntas.this, Pregunta2.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Categoria",Categoria);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Preguntas.this, Agradecimiento.class);
                    intent.putExtra("Agencia",Agencia);
                    intent.putExtra("Pregunta1",Question1);
                    intent.putExtra("Pregunta2",Question2);
                    intent.putExtra("Pregunta3",Question3);
                    intent.putExtra("Pregunta4",Question4);
                    intent.putExtra("Pregunta5",Question5);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta6",Question6);
                    intent.putExtra("Pregunta7",Question7);
                    intent.putExtra("Pregunta8",Question8);
                    intent.putExtra("Pregunta9",Question9);
                    intent.putExtra("Pregunta10",Question10);
                    startActivity(intent);
                }} else {Toast.makeText(Preguntas.this,"Verifica tu conexion a internet",Toast.LENGTH_LONG).show();
}
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
