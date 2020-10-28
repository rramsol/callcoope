package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;

public class Agradecimiento extends Activity {

    Vibrator vibe;
    Button Iniciar;
    Handler handler = new Handler();
    boolean f = false;

    String Agencia,Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10,MuyBueno,Bueno,Regular,Malo,MuyMalo,Suma= "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agradecimiento);
        f=true;
        Agencia = getIntent().getStringExtra("Agencia");
        Iniciar = (Button)findViewById(R.id.button13);



        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Agradecimiento.this, Area.class);
                intent.putExtra("Agencia",Agencia);
                intent.putExtra("Flag","1");
                startActivity(intent);
                finish();

                //probar esta linea de codigo
                handler.removeCallbacksAndMessages(null);
            }
        });

        //aqui va el contador para el cambio automatico


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(f){
                            Intent intent = new Intent(Agradecimiento.this, Area.class);
                            intent.putExtra("Agencia",Agencia);
                            intent.putExtra("Flag","1");
                            startActivity(intent);
                            f = false;
                        }

                    }
                },
                5000);

    }

    protected void onStop() {
        f=false;
        super.onStop();
    }

}
