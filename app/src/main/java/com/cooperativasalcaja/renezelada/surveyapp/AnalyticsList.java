package com.cooperativasalcaja.renezelada.surveyapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class AnalyticsList extends Activity {

    private ListView listView;
    List<String> tasks, convertir;
    ArrayAdapter<String> adapter;

    EditText inputSearch;
    TextView ver;
    Button regresar, enviarRe,btnTotales;
    String Email,lat,lon,reloj;
    int opcion;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    String Agencia,DateDesde,DateHasta,Category;
    android.widget.Button Ir;
    String a[];
    String sub, texto;

     int totalCreditos = 0;
     int totalReceptoria =0 ;
     int totalCuentasNuevas =0;
    
     int muyBueno=0;
     int bueno=0;
     int regular=0;
     int malo=0;
     int muyMalo=0;

    int muyBuenoc=0;
    int buenoc=0;
    int regularc=0;
    int maloc=0;
    int muyMaloc=0;

    int muyBuenor=0;
    int buenor=0;
    int regularr=0;
    int malor=0;
    int muyMalor=0;

    int muyBuenon=0;
    int buenon=0;
    int regularn=0;
    int malon=0;
    int muyMalon=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyticslist);

       final Gson gson = new Gson();

        convertir = new ArrayList<String>();

        /*gson.toJson(data)*/

        ver = (TextView) findViewById(R.id.textView73);
        regresar = (Button) findViewById(R.id.button18);
        enviarRe = (Button) findViewById(R.id.button19);
        btnTotales = (Button) findViewById(R.id.btnTotales);
        Agencia = getIntent().getStringExtra("Agencia");
        DateDesde = getIntent().getStringExtra("Date1");
        DateHasta = getIntent().getStringExtra("Date2");
        Category = getIntent().getStringExtra("Categoria");
        Email=getIntent().getStringExtra("Email");
        opcion=getIntent().getIntExtra("opcion",0);

        enviarRe.setVisibility(View.INVISIBLE);



        tasks = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.list_view);

        listView.setTextFilterEnabled(true);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        TextView textView = new TextView(AnalyticsList.this);
        //textView.setText("Cheques");
        listView.addHeaderView(textView);
        progressDialog = new ProgressDialog(AnalyticsList.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if(DateHasta.length() == 0 || DateDesde.equals(DateHasta)){
            ver.setText("Estadísticas: \n \n Agencia: "+ Agencia+"\n Area: "+ Category+" \n Fecha: "+DateDesde );

        }else{

            ver.setText("Estadísticas: \n Agencia: "+ Agencia+"\n Area: "+ Category+" \n\n Fecha\n De: "+DateDesde +"\nHasta: "+DateHasta);
            opcion = 3;

        }

        //BITACORA


        if (opcion == 1){

            btnTotales.setVisibility(View.INVISIBLE);
            enviarRe.setVisibility(View.VISIBLE);

            DatabaseReference myRefBitacora = database.getReference("Bitacora").child(Agencia);


            myRefBitacora.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String value = (dataSnapshot.getKey());

                    for (DataSnapshot snapshot1: dataSnapshot.getChildren()){

                        String area = (snapshot1.getKey());

                        Log.d("hola", value);
                        tasks.add("Area: "+value+"\n Fecha y hora: "+area+"\n");

                        // Collections.sort(tasks);
                        adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
                        listView = (ListView) findViewById(R.id.list_view);
                        listView.setDividerHeight(1);
                        listView.setAdapter(adapter);
                        progressDialog.dismiss();

                    }



                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else if (opcion == 2){

            //Reporte Completo

            DatabaseReference myRef = database.getReference("Repuestas").child(Agencia);
            //DatabaseReference myRef = database.getReference("Repuestas").child(Agencia).child(DateDesde).child(Category);

            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                    String fechahora = (snapshot.getKey());


                    for (DataSnapshot snapshot1: snapshot.getChildren()){

                        String area = (snapshot1.getKey());

                        String data = snapshot1.getValue().toString();

                        Log.d("dataaa", data);

                        String formatdata = data.replaceAll("[{}]", "\n");
                        String formatdata2 = formatdata.replace(", ¿", "¿");
                        String formatdata3 = formatdata2.replace("?=", "?");
                        String formatdata4 = formatdata3.replace("¿","\n¿");
                        String formatdata5 = formatdata4.replace("?","?");
                        String formatdata6 = formatdata5.replace("MuyMalo","    MuyMalo");


                        tasks.add(" Fecha: "+ fechahora + "\n Area:  " +area+ "\n\nPreguntas: "+formatdata6);

                        // Collections.sort(tasks);
                        adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
                        listView = (ListView) findViewById(R.id.list_view);
                        listView.setDividerHeight(1);
                        listView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                                long id) {

                        }

                    });
                }



                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });



        }else if (opcion == 3) {

            //Reporte Rango
            DatabaseReference myRef = database.getReference("Repuestas").child(Agencia);

            myRef.orderByKey().startAt(DateDesde).endAt(DateHasta).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    tasks.add("______________________________________________________________________________________________");
                    adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
                    listView = (ListView) findViewById(R.id.list_view);
                    listView.setDividerHeight(0);
                    listView.setAdapter(adapter);
                    progressDialog.dismiss();


                    String RangoFechas = (dataSnapshot.getKey());
                    Log.d("verdataRangoFechas", RangoFechas);


                    tasks.add("Fecha: "+ RangoFechas);

                    for (DataSnapshot snapshot1: dataSnapshot.getChildren()) {

                        String area = snapshot1.getKey();
                        Log.d("verdataarea", area);

                        tasks.add("Area: "+ area.toUpperCase());

                        for (DataSnapshot snapshot2: snapshot1.getChildren()){

                            String pregunta = snapshot2.getKey();
                            Log.d("verdatapregunta", pregunta);

                            tasks.add("Pregunta: "+ pregunta);
                            Calificacion valorRespuesta = snapshot2.getValue(Calificacion.class);

                            tasks.add("              Muy Malo: "+valorRespuesta.MuyMalo + " Malo: "+valorRespuesta.Malo + " Regular: "+valorRespuesta.Regular+" Bueno: "+valorRespuesta.Bueno+ " Muy Bueno: "+valorRespuesta.MuyBueno );

                            if(area.equals("Creditos")){
                                muyBuenoc = muyBuenoc + Integer.parseInt(valorRespuesta.MuyBueno);
                                buenoc = buenoc + Integer.parseInt(valorRespuesta.Bueno);
                                regularc = regularc + Integer.parseInt(valorRespuesta.Regular);
                                maloc = maloc + Integer.parseInt(valorRespuesta.Malo);
                                muyMaloc = muyMaloc + Integer.parseInt(valorRespuesta.MuyMalo);
                            }
                            if(area.equals("CuentasNuevas")){
                                muyBuenon = muyBuenon + Integer.parseInt(valorRespuesta.MuyBueno);
                                buenon = buenon + Integer.parseInt(valorRespuesta.Bueno);
                                regularn = regularn + Integer.parseInt(valorRespuesta.Regular);
                                malon = malon + Integer.parseInt(valorRespuesta.Malo);
                                muyMalon = muyMalon + Integer.parseInt(valorRespuesta.MuyMalo);
                            }
                            if(area.equals("Receptoria")){
                                muyBuenor = muyBuenor + Integer.parseInt(valorRespuesta.MuyBueno);
                                buenor = buenor + Integer.parseInt(valorRespuesta.Bueno);
                                regularr = regularr + Integer.parseInt(valorRespuesta.Regular);
                                malor = malor + Integer.parseInt(valorRespuesta.Malo);
                                muyMalor = muyMalor + Integer.parseInt(valorRespuesta.MuyMalo);
                            }

                            Log.d("verdataValorRespuesta", valorRespuesta.MuyMalo);
                            Log.d("verdataValorRespuesta", valorRespuesta.Malo);
                            Log.d("verdataValorRespuesta", valorRespuesta.Regular);
                            Log.d("verdataValorRespuesta", valorRespuesta.Bueno);
                            Log.d("verdataValorRespuesta", valorRespuesta.MuyBueno);

                        }

                    }



                    // Collections.sort(tasks);
                    adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
                    listView = (ListView) findViewById(R.id.list_view);
                    listView.setDividerHeight(0);
                    listView.setAdapter(adapter);
                    progressDialog.dismiss();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });






        }else{

            //Reporte Normal

            DatabaseReference myRef = database.getReference("Repuestas").child(Agencia).child(DateDesde).child(Category);


            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                    String value = (snapshot.getKey());
                    Calificacion value2 = snapshot.getValue(Calificacion.class);

                    tasks.add("Pregunta \n"+value +" " +"\n"+"Muy bueno: "+ value2.MuyBueno+"\n"+"Bueno: "+value2.Bueno +"\n"+"Regular: "+value2.Regular+"\n"+"Malo: "+value2.Malo+"\n"+"Muy Malo: "+value2.MuyMalo+"\n");

                    muyBueno = muyBueno + Integer.parseInt(value2.MuyBueno);
                    bueno = bueno + Integer.parseInt(value2.Bueno);
                    regular = regular + Integer.parseInt(value2.Regular);
                    malo = malo + Integer.parseInt(value2.Malo);
                    muyMalo = muyMalo + Integer.parseInt(value2.MuyMalo);

                    Log.d("qwe", String.valueOf(muyBueno));

                    // Collections.sort(tasks);
                    adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
                    listView = (ListView) findViewById(R.id.list_view);
                    listView.setDividerHeight(1);
                    listView.setAdapter(adapter);
                    progressDialog.dismiss();


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });

        }//endif


        //Enviar reporte

       enviarRe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(opcion == 3){

                   String resultados = ("TOTAL CREDITOS:\n" +
                           "Muy buenas: "+muyBuenoc +"\n" +
                           "Bueno: "+buenoc+"\n"+
                           "Regular: "+regularc+"\n"+
                           "Malo: "+maloc+"\n"+
                           "Muy Malo: "+muyMaloc+"\n\n"+
                           "TOTAL RECEPTORIA:\n" +
                           "Muy buenas: "+muyBuenor +"\n" +
                           "Bueno: "+buenor+"\n"+
                           "Regular: "+regularr+"\n"+
                           "Malo: "+malor+"\n"+
                           "Muy Malo: "+muyMalor+"\n\n"+
                           "TOTAL Cuentas Nuevas:\n" +
                           "Muy buenas: "+muyBuenon +"\n" +
                           "Bueno: "+buenon+"\n"+
                           "Regular: "+regularn+"\n"+
                           "Malo: "+malon+"\n"+
                           "Muy Malo: "+muyMalon+"\n\n");

                   sendEmail(Email,resultados,Category, Agencia,DateDesde);
               }else{
                   sendEmail(Email,tasks.toString(),Category, Agencia,DateDesde);
               }



           }
       });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnTotales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opcion == 3){
                    totalesRango();
                }else{
                    totales();
                }

            }
        });



        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                AnalyticsList.this.adapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

     private void  totales() {

        //AQUI NO SUMA EL MUY BUENO
        Log.d("qwe2", String.valueOf(muyBueno));

        tasks.add("TOTALES:\n" +
                "Muy buenas: "+muyBueno +"\n" +
                "Bueno: "+bueno+"\n"+
                "Regular: "+regular+"\n"+
                "Malo: "+malo+"\n"+
                "Muy Malo: "+muyMalo+"\n");

        // Collections.sort(tasks);
        adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setDividerHeight(2);
        listView.setAdapter(adapter);
        progressDialog.dismiss();

        btnTotales.setVisibility(View.INVISIBLE);
        enviarRe.setVisibility(View.VISIBLE);

    }

    private void  totalesRango() {

        //AQUI NO SUMA EL MUY BUENO


        tasks.add("TOTAL CREDITOS:\n" +
                "Muy buenas: "+muyBuenoc +"\n" +
                "Bueno: "+buenoc+"\n"+
                "Regular: "+regularc+"\n"+
                "Malo: "+maloc+"\n"+
                "Muy Malo: "+muyMaloc+"\n\n"+
                "TOTAL RECEPTORIA:\n" +
                "Muy buenas: "+muyBuenor +"\n" +
                "Bueno: "+buenor+"\n"+
                "Regular: "+regularr+"\n"+
                "Malo: "+malor+"\n"+
                "Muy Malo: "+muyMalor+"\n\n"+
                "TOTAL Cuentas Nuevas:\n" +
                "Muy buenas: "+muyBuenon +"\n" +
                "Bueno: "+buenon+"\n"+
                "Regular: "+regularn+"\n"+
                "Malo: "+malon+"\n"+
                "Muy Malo: "+muyMalon+"\n\n");

        // Collections.sort(tasks);
        adapter = new ArrayAdapter<String>(AnalyticsList.this,R.layout.list_item, R.id.codigo_token,tasks);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setDividerHeight(0);
        listView.setAdapter(adapter);
        progressDialog.dismiss();

        btnTotales.setVisibility(View.INVISIBLE);
        enviarRe.setVisibility(View.VISIBLE);

    }

    private void sendEmail( String Email, String texto, String cat, String agen, String date){


        /*

        Para enviar archivo

        String path = Environment.getExternalStorageDirectory().toString();

        File pngDir = new File(Environment.getExternalStorageDirectory(),"My Documents/");

        File pngfile=new File(pngDir,"Prueba123.xlsx");

        Uri pngUri = Uri.fromFile(pngfile);

         */

        sub = "Reporte \n \n Agencia: "+ agen+"\n del Area de: "+ cat +" \n con Fecha: "+date ;


        Intent intent  = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { Email });
        intent.putExtra(Intent.EXTRA_SUBJECT, sub);
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        /* enviar archivo
        intent.putExtra(Intent.EXTRA_STREAM, pngUri);
        */
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"elige un mail"));

    }

    private List<Date> ListaDeFechas (Date fechaInicio, Date fechaFin) {


        Calendar c1 = Calendar.getInstance();
        c1.setTime(fechaInicio);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(fechaFin);


        List<Date> listaFechas = new ArrayList<Date>();
        while (!c1.after(c2)) {
            listaFechas.add(c1.getTime());
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return listaFechas;
    }
}
