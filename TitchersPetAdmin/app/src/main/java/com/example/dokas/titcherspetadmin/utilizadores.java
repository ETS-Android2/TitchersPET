package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class utilizadores extends Activity {

    private SQLiteDatabase db;
    private HandlerBD ajudante;
    private Cursor users;
    private ArrayList<String> turmas = new ArrayList<String>();
    private ArrayList<String> nomes = new ArrayList<String>();
    private ArrayList<String> passes = new ArrayList<String>();
    private int id=0;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_utilizadores);
    }

    @Override
    public void onResume() {

        super.onResume();

        ajudante = new HandlerBD(this, "titchersPet");
        db = ajudante.getWritableDatabase();

        String query = "SELECT * FROM " + ajudante.TABLE_NAME1 + " WHERE " + ajudante.T1_COLUMN3 + "= 0 AND "+ ajudante.T1_COLUMN4 +"= 1;";
        users = db.rawQuery(query, null);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear1);
        layout.removeAllViews();

        boolean conteudo = users.moveToFirst();

        if(conteudo==false){

            ConstraintLayout principal = (ConstraintLayout) findViewById(R.id.principal_utilizadores);

            ConstraintLayout vazio = (ConstraintLayout) getLayoutInflater().inflate(R.layout.texto_vazio,null);
            TextView texto = (TextView) vazio.findViewById(R.id.vazio_texto);
            texto.setText("Nenhuma educadora no sistema");
            vazio.removeView(texto);

            principal.addView(texto);
        }

        else{

            ConstraintLayout principal = (ConstraintLayout) findViewById(R.id.principal_utilizadores);

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

            contexto = this;

            novo.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {

                    LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.editar_dialog,null);
                    final EditText editar = (EditText) layout.findViewById(R.id.edicao);
                    editar.setHint("Turma: " + turmas.get(v.getId()));
                    layout.removeView(editar);

                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

                    builder.setTitle("Informação de utilizador");
                    builder.setMessage(nomes.get(v.getId()));
                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // botão positivo

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Guardar", new DialogInterface.OnClickListener() { // botão negativo

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ContentValues conteudo = new ContentValues();
                            conteudo.put(ajudante.T1_COLUMN1, nomes.get(v.getId()));
                            conteudo.put(ajudante.T1_COLUMN2, passes.get(v.getId()));
                            conteudo.put(ajudante.T1_COLUMN3, 0);
                            conteudo.put(ajudante.T1_COLUMN4, 1);
                            conteudo.put(ajudante.T1_COLUMN5, editar.getText().toString());
                            db.update(ajudante.TABLE_NAME1,conteudo,ajudante.T1_COLUMN1 + "='" + nomes.get(v.getId()) + "'",null);
                            onResume();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.setView(editar,50,0,50,0);
                    dialog.show();

                    // mudar a cor dos botões
                    Button positivo = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positivo.setTextColor(Color.parseColor("#3392ff"));

                    Button negativo = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    negativo.setTextColor(Color.parseColor("#3392ff"));

                }
            });

            novo.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(final View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(utilizadores.this);

                    builder.setTitle("Deseja mesmo apagar este utilizador?");
                    builder.setMessage("Esta ação é irreversível");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() { // botão positivo

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            db.delete(ajudante.TABLE_NAME1, ajudante.T1_COLUMN1 + "='"+ nomes.get(v.getId()) +"' and "+ ajudante.T1_COLUMN5 + "='"+ turmas.get(v.getId()) +"'", null);
                            onResume();
                        }
                    });

                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() { // botão negativo

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

                    return true;
                }
            });

            conteudo = users.moveToNext();
        }

    }

    public void verInfo(View v){

        Intent muda = new Intent(this, infoUt.class);
        startActivity(muda);

    }

    public void voltar(View v){

        this.finish();

    }
}
