package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Menu extends Activity {
    Button Agencias,Preguntas,Usuarios,Salir;
    DatabaseReference mDatabase;
    String Email,Agencia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Email = getIntent().getStringExtra("Email");


        Agencias = (Button)findViewById(R.id.button9);
        //Preguntas = (Button)findViewById(R.id.button10);
        Usuarios = (Button)findViewById(R.id.button11);
        Salir =(Button)findViewById(R.id.button12);

        Email = Email.replace(".","-");

        FirebaseApp.initializeApp(this);



        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Users").child(Email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Agencia agencia = dataSnapshot.getValue(Agencia.class);

                if(dataSnapshot.exists()){
                    Agencia = agencia.Agencia;

                    Log.d("agenciaBDd",Agencia);
                    //si tengo la agencia


                    Agencias.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Menu.this, AgenciasList.class);
                            intent.putExtra("Agencia","Central");
                            startActivity(intent);
                        }
                    });

                    /*Preguntas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Menu.this, SpinnerQuestions.class);
                            // intent.putExtra("Agencia",Agencia);

                            startActivity(intent);
                        }
                    });*/
                    Usuarios.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    Salir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            //) FirebaseAuth.getInstance().signOut();
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                            finish();
                            onTrimMemory(80);
                        }
                    });

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
