package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class pedidos extends Activity {

    private SQLiteDatabase db;
    private HandlerBD ajudante;
    private Cursor users;
    private ArrayList<String> nomes = new ArrayList<String>();
    private ArrayList<String> passes = new ArrayList<String>();
    private ArrayList<String> turmas = new ArrayList<String>();
    private int id=0;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pedidos);
    }

    @Override
    public void onResume() {

        super.onResume();

        contexto = this;

        ajudante = new HandlerBD(this, "titchersPet");
        db = ajudante.getWritableDatabase();

        String query = "SELECT * FROM " + ajudante.TABLE_NAME1 + " WHERE " + ajudante.T1_COLUMN4 + "= 0;";
        users = db.rawQuery(query, null);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.removeAllViews();

        boolean conteudo = users.moveToFirst();

        if(conteudo==false){

            ConstraintLayout principal = (ConstraintLayout) findViewById(R.id.principal_pedidos);

            ConstraintLayout vazio = (ConstraintLayout) getLayoutInflater().inflate(R.layout.texto_vazio,null);
            TextView texto = (TextView) vazio.findViewById(R.id.vazio_texto);
            texto.setText("Nenhum pedido pendente");
            vazio.removeView(texto);

            principal.addView(texto);
        }

        else{

            ConstraintLayout principal = (ConstraintLayout) findViewById(R.id.principal_pedidos);

            TextView texto = (TextView) findViewById(R.id.vazio_texto);

            if(texto!=null)
                principal.removeView(texto);
        }

        while(conteudo) {

            ConstraintLayout secundario = (ConstraintLayout) getLayoutInflater().inflate(R.layout.linha_user, null);

            Button novo = (Button) secundario.findViewById(R.id.user);
            nomes.add(users.getString(0));
            passes.add(users.getString(1));
            turmas.add(users.getString(4));
            novo.setId(id);
            id += 1;
            novo.setText(users.getString(0));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 10, 5, 0);
            layout.addView(secundario, layoutParams);

            novo.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

                    builder.setTitle("Pedido de registo");
                    builder.setMessage("Pretende aceitar o pedido da " + nomes.get(v.getId()) + "?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("ACEITAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Guardar valores numa variavel de conteudo e dar Update na BD
                            ContentValues cv = new ContentValues();
                            cv.put(ajudante.T1_COLUMN1, nomes.get(v.getId()));
                            cv.put(ajudante.T1_COLUMN2, passes.get(v.getId()));
                            cv.put(ajudante.T1_COLUMN3, 0);
                            cv.put(ajudante.T1_COLUMN4, 1);
                            cv.put(ajudante.T1_COLUMN5, turmas.get(v.getId()));

                            db.update(ajudante.TABLE_NAME1, cv, ajudante.T1_COLUMN1 + "='" + nomes.get(v.getId()) + "' and " + ajudante.T1_COLUMN2 + "= '" + passes.get(v.getId()) + "'", null);
                            onResume();

                            Toast.makeText(contexto, "Utilizador aceite com sucesso!", Toast.LENGTH_LONG).show();
                        }

                    });
                    builder.setNegativeButton("Recusar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }

                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    // mudar a cor dos botões
                    Button positivo = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positivo.setTextColor(Color.parseColor("#3392ff"));

                    Button negativo = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    negativo.setTextColor(Color.parseColor("#3392ff"));

                }
            });

            conteudo = users.moveToNext();
        }

    }

    public void voltar(View v){

        super.finish();
    }

}
