package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Quetions extends Activity {
    String Agencia;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    EditText Pre1, Pre2, Pre3, Pre4, Pre5, Pre6, Pre7, Pre8, Pre9, Pre10;
    String Categoria,Question1, Question2, Question3, Question4, Question5, Question6, Question7, Question8, Question9, Question10;
    Button Guardar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        //Agencia = getIntent().getStringExtra("Agencia");
        Agencia = "Central";
        Categoria = getIntent().getStringExtra("Categoria");

        Pre1 = (EditText) findViewById(R.id.editText);
        Pre2 = (EditText) findViewById(R.id.editText2);
        Pre3 = (EditText) findViewById(R.id.editText3);
        Pre4 = (EditText) findViewById(R.id.editText4);
        Pre5 = (EditText) findViewById(R.id.editText5);
        Pre6 = (EditText) findViewById(R.id.editText6);
        Pre7 = (EditText) findViewById(R.id.editText7);
        Pre8 = (EditText) findViewById(R.id.editText8);
        Pre9 = (EditText) findViewById(R.id.editText9);
        Pre10 = (EditText) findViewById(R.id.editText10);

        Guardar = (Button)findViewById(R.id.button8);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        final String strDate = mdformat.format(calendar.getTime());

        FirebaseApp.initializeApp(this);

        progressDialog = new ProgressDialog(Quetions.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Preguntas").child(Categoria).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final API api = dataSnapshot.getValue(API.class);

                if (dataSnapshot.exists()) {
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

                    if (Question1 != null) {
                        Pre1.setText(Question1);
                    }else{Pre1.setText("-");}
                    if (Question2 != null) {

                        Pre2.setText(Question2);
                    }else{Pre2.setText("-");}
                    if (Question3 != null) {
                        Pre3.setText(Question3);
                    }else{Pre3.setText("-");}
                    if (Question4 != null) {
                        Pre4.setText(Question4);
                    }else{Pre4.setText("-");}
                    if (Question5 != null) {
                        Pre5.setText(Question5);
                    }else{Pre5.setText("-");}
                    if (Question6 != null) {
                        Pre6.setText(Question6);
                    }else{Pre6.setText("-");}
                    if (Question7 != null) {
                        Pre7.setText(Question7);
                    }else{Pre7.setText("-");}
                    if (Question8 != null) {
                        Pre8.setText(Question8);
                    }else{Pre8.setText("-");}
                    if (Question9 != null) {
                        Pre9.setText(Question9);
                    }else{Pre9.setText("-");}
                    if (Question10 != null) {
                        Pre10.setText(Question10);
                    }else{Pre10.setText("-");}
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    Guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if ( (!Pre1.getText().toString().equals("-"))) {

                mDatabase.child("Preguntas").child(Categoria).child("Q1").setValue(Pre1.getText().toString());
                Toast.makeText(Quetions.this,"Datos guardados Exitosamente",Toast.LENGTH_LONG);
            }
            if ( (!Pre2.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q2").setValue(Pre2.getText().toString());

            }
            if ( (!Pre3.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q3").setValue(Pre3.getText().toString());
            }
            if ( (!Pre4.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q4").setValue(Pre4.getText().toString());
            }
            if ( (!Pre5.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q5").setValue(Pre5.getText().toString());
            }
            if ( (!Pre6.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q6").setValue(Pre6.getText().toString());
            }
            if ((!Pre7.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q7").setValue(Pre7.getText().toString());
            }
            if ( (!Pre8.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q8").setValue(Pre8.getText().toString());
            }
            if ((!Pre9.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q9").setValue(Pre9.getText().toString());
            }
            if ((!Pre10.getText().toString().equals("-"))) {
                mDatabase.child("Preguntas").child(Categoria).child("Q10").setValue(Pre10.getText().toString());

            }
            progressDialog.dismiss();
        }
    });




    }

}
