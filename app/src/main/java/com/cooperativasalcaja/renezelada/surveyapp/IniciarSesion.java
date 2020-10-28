package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;



public class IniciarSesion extends Activity {

    String email,password;
    EditText Correo, Contra;
    Button Iniciar;
    ProgressDialog progressDialog;
    DatabaseReference mDatabase;
    String agenciaBD = "";
    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logeo);

        auth = FirebaseAuth.getInstance();
        Correo = (EditText) findViewById(R.id.editText2);
        Contra = (EditText)findViewById(R.id.editText3);
        Iniciar = (Button)findViewById(R.id.button2);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = Correo.getText().toString().trim();
                password = Contra.getText().toString().trim();

                progressDialog = new ProgressDialog(IniciarSesion.this);
                progressDialog.setMessage("Cargando...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                if (email.isEmpty())
                {
                    Toast.makeText(IniciarSesion.this,"Ingrese un email valido", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else if (password.length() < 6)
                {
                    Toast.makeText(IniciarSesion.this,"Ingrese una contraseña mayor de 6 caracteres", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else{

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(IniciarSesion.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(IniciarSesion.this, "Bienvenido",
                                                Toast.LENGTH_SHORT).show();

                                        String emailRepla = email.replace(".","-");

                                        //aqui va la validacion si es admin (REPORTES)

                                        mDatabase.child("Users").child(emailRepla).child("Agencia")
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        agenciaBD = dataSnapshot.getValue(String.class);

                                                        Log.d("agenciaBDd",agenciaBD);

                                                        if(agenciaBD.equals("admin")){

                                                            Intent myIntent = new Intent(IniciarSesion.this, AgenciasList.class);
                                                            myIntent.putExtra("Email",email);
                                                            startActivity(myIntent);
                                                            progressDialog.dismiss();
                                                            finish();


                                                        }else{

                                                            Intent myIntent = new Intent(IniciarSesion.this, DateList.class);
                                                            myIntent.putExtra("Email",email);
                                                            myIntent.putExtra("Agencia",agenciaBD);
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
                                        Toast.makeText(IniciarSesion.this, "Verifique su usuario.",
                                                Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
            }
        });
    }
}