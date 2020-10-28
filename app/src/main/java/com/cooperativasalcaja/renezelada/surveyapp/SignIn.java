package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rzelada on 31/8/2018.
 */

public class SignIn extends Activity {
    private FirebaseAuth auth;
    // ...
    String email,password,name;
    EditText Correo, Contra,Nombre;
    Button Iniciar,Olvidar;
    ProgressDialog progressDialog;
    Spinner  Tipo,Agencia;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Correo = (EditText) findViewById(R.id.editText2);
        Contra = (EditText)findViewById(R.id.editText3);
        Nombre = (EditText)findViewById(R.id.editText7);
        Iniciar = (Button)findViewById(R.id.button2);
        Olvidar = (Button)findViewById(R.id.button8);
        Tipo = (Spinner)findViewById(R.id.spinner);
        Agencia = (Spinner)findViewById(R.id.spinner2);

        List<String> categories = new ArrayList<String>();

        categories.add("Seleccione");
        categories.add("Gerencia");
        categories.add("Tesoreria");
        categories.add("Contabilidad");
        categories.add("Jefe de Agencia");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Tipo.setAdapter(dataAdapter);

        List<String> categories2 = new ArrayList<String>();

        categories2.add("Seleccione");
        categories2.add("Corporativo");
        categories2.add("Central");
        categories2.add("Democracia");
        categories2.add("San Cristobal");
        categories2.add("San Nicolas");
        categories2.add("Momostenango");
        categories2.add("El Carmen");
        categories2.add("San Juan");
        categories2.add("Coatepeque");
        categories2.add("Malacatan");
        categories2.add("Trinidad");
        categories2.add("4 caminos");
        categories2.add("Maxi Coatepeque");
        categories2.add("Maxi Trigales");
        categories2.add("Centro Historico");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, categories2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Agencia.setAdapter(dataAdapter2);




        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Cargando...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                email = Correo.getText().toString().trim();
                password = Contra.getText().toString().trim();

                if (email.isEmpty())
                {
                    Toast.makeText(SignIn.this,"Ingrese un email valido", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else if (password.length() < 6)
                {
                    Toast.makeText(SignIn.this,"Ingrese una contrase;a mayor de 6 caracteres", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else{
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        // Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                        //        Toast.LENGTH_SHORT).show();
                                        email = email.replace(".","_");

                                        mDatabase.child("Correos").child(email).child("Nombre").setValue(Nombre.getText().toString().trim());
                                        mDatabase.child("Correos").child(email).child("Tipo").setValue(Tipo.getSelectedItem().toString().trim());
                                        mDatabase.child("Correos").child(email).child("Agencia").setValue(Agencia.getSelectedItem().toString().trim());

                                        Toast.makeText(SignIn.this,"Usuario Creado", Toast.LENGTH_LONG).show();
                                        Nombre.setText("");
                                        Correo.setText("");
                                        Contra.setText("");
                                        progressDialog.dismiss();

                                    } else {


                                        Toast.makeText(SignIn.this,"Ocurrio un problema por favor verifique ", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });

        Olvidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent intent = new Intent(SignIn.this, Olvide.class);
               // startActivity(intent);
            }
        });


    }
}