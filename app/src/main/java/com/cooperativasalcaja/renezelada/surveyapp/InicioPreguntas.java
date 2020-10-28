package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InicioPreguntas extends Activity{
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
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-M-yyyy");
        final String strDate =  mdformat.format(calendar.getTime());

        FirebaseApp.initializeApp(this);

        progressDialog = new ProgressDialog(InicioPreguntas.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Preguntas").child(Categoria).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final API api = dataSnapshot.getValue(API.class);

                if(dataSnapshot.exists()){
                    Question1 = api.Q1;
                    Question2 = api.Q2;
                    Question3 = api.Q3;
                    Question4 = api.Q4;
                    Question5 = api.Q5;
                    Question6 = api.Q6;
                    Question7 = api.Q7;
                    Question8 = api.Q8;
                    Question9 = api.Q9;
                    Question10 = api.Q10;

                        if(!Flag.equals("1")) {

                            if (Question1 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question1).child("MuyMalo").setValue("0");
                            }
                            if (Question2 != null) {

                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question2).child("MuyMalo").setValue("0");

                            }
                            if (Question3 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question3).child("MuyMalo").setValue("0");

                            }
                            if (Question4 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question4).child("MuyMalo").setValue("0");

                            }
                            if (Question5 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question5).child("MuyMalo").setValue("0");

                            }
                            if (Question6 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question6).child("MuyMalo").setValue("0");

                            }
                            if (Question7 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question7).child("MuyMalo").setValue("0");

                            }
                            if (Question8 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question8).child("MuyMalo").setValue("0");

                            }
                            if (Question9 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question9).child("MuyMalo").setValue("0");

                            }
                            if (Question10 != null) {
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("MuyBueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Bueno").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Regular").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("Malo").setValue("0");
                                mDatabase.child("Repuestas").child(Agencia).child(strDate).child(Categoria).child(Question10).child("MuyMalo").setValue("0");

                            }
                        }


                            progressDialog.dismiss();

                            Intent intent = new Intent(InicioPreguntas.this, Preguntas.class);
                            intent.putExtra("Agencia", Agencia);
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

                    }else{
                        progressDialog.dismiss();

                        Intent intent = new Intent(InicioPreguntas.this, Preguntas.class);
                        intent.putExtra("Agencia", Agencia);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
        }
        return true;
    }
}
