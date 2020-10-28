package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    android.widget.Button  Inicio, listado, usuario, salir;
    ProgressDialog progressDialog;
    Spinner  Agencias;

    //logueo
    DatabaseReference mDatabase;
    String email,password;
    EditText Correo, Contra;
    private FirebaseAuth auth;
    Button Iniciarlogueo;
    String agenciaBD = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //logueo
        auth = FirebaseAuth.getInstance();
        Correo = (EditText) findViewById(R.id.editText14);
        Contra = (EditText)findViewById(R.id.editText13);
        Iniciarlogueo = (Button)findViewById(R.id.button16);
        listado = (Button)findViewById(R.id.button7);

        Correo.requestFocus();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        Iniciarlogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                email = Correo.getText().toString().trim();
                password = Contra.getText().toString().trim();

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Cargando...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                if (email.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Ingrese un email valido", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else if (password.length() < 6)
                {
                    Toast.makeText(MainActivity.this,"Ingrese una contraseña mayor de 6 caracteres", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else{

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity.this, "Bienvenido",
                                                Toast.LENGTH_SHORT).show();


                                        //cambio de email . a -
                                        String emailRepla = email.replace(".","-");


                                        //ENCONTRAR AGENCIA

                                        mDatabase.child("Users").child(emailRepla).child("Agencia")
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        agenciaBD = dataSnapshot.getValue(String.class);

                                                        Log.d("agenciaBDd",agenciaBD);

                                                        if(agenciaBD.equals("admin")){

                                                            Intent myIntent = new Intent(MainActivity.this, ListadoAgencias.class);
                                                            myIntent.putExtra("Email",email);
                                                            myIntent.putExtra("Flag","0");
                                                            startActivity(myIntent);
                                                            progressDialog.dismiss();
                                                            finish();

                                                        }else{
                                                            Intent myIntent = new Intent(MainActivity.this, Area.class);
                                                            myIntent.putExtra("Email",email);
                                                            myIntent.putExtra("Agencia",agenciaBD);
                                                            Log.d("agenciaBDelse",agenciaBD);
                                                            myIntent.putExtra("Flag","0");
                                                            startActivity(myIntent);
                                                            progressDialog.dismiss();
                                                            finish();

                                                        }


                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }

                                                });


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Verifique su usuario.",
                                                Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
            }
        });

        //-----------------------------------------------------------------------------



        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IniciarSesion.class);
                startActivity(intent);
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
