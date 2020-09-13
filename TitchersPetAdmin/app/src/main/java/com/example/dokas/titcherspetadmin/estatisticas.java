package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class estatisticas extends Activity {

    private HandlerBD ajudante;
    private SQLiteDatabase bd;
    private Cursor faltas;
    private EditText data1;
    private String data;
    private int contar;
    private Handler handler;
    private TextView nome1;
    private TextView nome2;
    private TextView nome3;
    private TextView faltas1;
    private TextView faltas2;
    private TextView faltas3;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_estatisticas);

        ajudante = new HandlerBD(this, "titchersPet");
        bd = ajudante.getWritableDatabase();

        data1 = (EditText) findViewById(R.id.insData);

        nome1 = (TextView) findViewById(R.id.nome1);
        nome2 = (TextView) findViewById(R.id.nome2);
        nome3 = (TextView) findViewById(R.id.nome3);

        faltas1 = (TextView) findViewById(R.id.faltas1);
        faltas2 = (TextView) findViewById(R.id.faltas2);
        faltas3 = (TextView) findViewById(R.id.faltas3);

        handler = new Handler();
        contexto = this;

        Thread novo = new Thread(){

            private boolean inicio = true;

            public void run() {

                while (true){

                    try {

                        sleep(300); // ligeiro delay, o utilizador pode estar a apagar a data (assim evitamos que o thread se sobreponha)
                    }

                    catch (Exception e){}

                    Runnable r = new Runnable() {

                        @Override
                        public void run() {

                            if(data1.getText().toString().length()==2 || data1.getText().toString().length()==5){

                                data1.setText(data1.getText().toString() + "/");
                                data1.setSelection(data1.getText().length());
                            }

                            else if(data1.getText().toString().length()==10 && (inicio || !data1.getText().toString().equals(nome1.getText().toString().split(" ")[3]
                                    + "/" + nome2.getText().toString().split(" ")[3] + "/" + nome3.getText().toString().split(" ")[3]))){

                                inicio = false;
                                dia((View)data1);
                            }
                        }
                    };

                    handler.post(r);
                }
            }
        };

        novo.start();

    }

    public void dia(View v){

        try {
            contar = 0;

            data1 = (EditText) findViewById(R.id.insData);
            data = data1.getText().toString();

            String query = "SELECT * FROM " + ajudante.TABLE_NAME5 + ";";
            Cursor faltas = bd.rawQuery(query, null);

            String[] aux = data.split("/");

            while (faltas.moveToNext()) {

                if (faltas.getString(1).contains(aux[0]+"/"+aux[1]+"/"+aux[2]))
                    contar++;

            }

            nome1.setText("Faltas no dia " + data.split("/")[0]);
            faltas1.setText("" + contar + " faltas");
            mes(v);
        }

        catch(Exception e){

            final AlertDialog alerta = new AlertDialog.Builder(estatisticas.this).create();
            alerta.setTitle("Guardar");
            alerta.setMessage("Data mal introduzida!");
            alerta.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.hide();
                }

            });
            alerta.show();

        }

    }

    public void mes(View v){

        try{

            contar=0;

            data1 = (EditText) findViewById(R.id.insData);
            data = data1.getText().toString();

            String query = "SELECT * FROM " + ajudante.TABLE_NAME5 + ";";
            Cursor faltas = bd.rawQuery(query, null);

            String[] aux = data.split("/");

            while(faltas.moveToNext()){

                if(faltas.getString(1).contains("/"+aux[1]+"/"+aux[2]))
                    contar++;

            }

            nome2.setText("Faltas no mês " + data.split("/")[1]);
            faltas2.setText("" + contar + " faltas");
            ano(v);

        }

        catch(Exception e){

            final AlertDialog alerta = new AlertDialog.Builder(estatisticas.this).create();
            alerta.setTitle("Guardar");
            alerta.setMessage("Data mal introduzida!");
            alerta.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.hide();
                }

            });
            alerta.show();

        }

    }

    public void ano(View v){

        try {

            contar = 0;

            data1 = (EditText) findViewById(R.id.insData);
            data = data1.getText().toString();

            String query = "SELECT * FROM " + ajudante.TABLE_NAME5 + ";";
            Cursor faltas = bd.rawQuery(query, null);

            String[] aux = data.split("/");

            while (faltas.moveToNext()) {

                if (faltas.getString(1).contains("/" + aux[2]))
                    contar++;

            }

            nome3.setText("Faltas no ano " + data.split("/")[2]);
            faltas3.setText("" + contar + " faltas");

        }

        catch(Exception e){

            final AlertDialog alerta = new AlertDialog.Builder(estatisticas.this).create();
            alerta.setTitle("Guardar");
            alerta.setMessage("Data mal introduzida!");
            alerta.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta.hide();
                }

            });
            alerta.show();

        }

    }

    public void voltar(View v){

        this.finish();

    }

}
