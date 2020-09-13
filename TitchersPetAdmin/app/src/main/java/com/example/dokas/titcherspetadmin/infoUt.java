package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class infoUt extends Activity {

    private SQLiteDatabase db;
    private HandlerBD ajudante;
    private String nome, turma, pass;
    private TextView turma1, nome1;
    private EditText edit_nome, edit_turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info_ut);

        ajudante = new HandlerBD(this, "titchersPet");
        db = ajudante.getWritableDatabase();

        try{

            Intent intento = getIntent();
            nome = intento.getStringExtra("nome");
            turma = intento.getStringExtra("turma");
            pass = intento.getStringExtra("pass");
            nome1 = (TextView) findViewById(R.id.nome);
            nome1.setText(intento.getStringExtra("nome"));

            turma1 = (TextView) findViewById(R.id.turma_texto);
            turma1.setText(intento.getStringExtra("turma"));

        }

        catch(Exception e){}



    }

    public void editarUser(View v){

        try {

            LinearLayout linear1 = (LinearLayout) findViewById(R.id.linear1);
            LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);

            // alterar o botão de editar para apresentar a mensagem "Guardar"
            ConstraintLayout layout_constraint = (ConstraintLayout) findViewById(R.id.constraint_grande);
            ImageButton editar = (ImageButton) findViewById(R.id.edit);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) editar.getLayoutParams();

            ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);

            newParams.rightToRight = params.rightToRight;
            newParams.topToTop = params.topToTop;
            newParams.setMargins(0,30,55,0);

            layout_constraint.removeView(editar);

            ConstraintLayout layout_constraint_secundario = (ConstraintLayout) getLayoutInflater().inflate(R.layout.texto_guardar,null);
            Button guardar = (Button) layout_constraint_secundario.findViewById(R.id.guardar);

            layout_constraint_secundario.removeView(guardar);

            layout_constraint.addView(guardar, newParams);


            LinearLayout linear_secundario = (LinearLayout) getLayoutInflater().inflate(R.layout.edittext_spinner, null);

            linear_secundario = (LinearLayout) getLayoutInflater().inflate(R.layout.edittext_spinner, null);
            edit_turma = (EditText) linear_secundario.findViewById(R.id.edit1);

            edit_turma.setHint(turma1.getText().toString());
            linear_secundario.removeView(edit_turma);

            linear2.removeView(turma1);
            linear2.addView(edit_turma);

        }

        catch (Exception e){

            Log.v("TitchersPET",e.getMessage());
        }

    }

    public void guardar(View v){

        Toast.makeText(this,"Aluno atualizado com sucesso!", Toast.LENGTH_LONG).show();

        ContentValues conteudo = new ContentValues();

        conteudo.put(ajudante.T1_COLUMN1, nome);
        conteudo.put(ajudante.T1_COLUMN2, pass);
        conteudo.put(ajudante.T1_COLUMN3, 0);
        conteudo.put(ajudante.T1_COLUMN4, 1);

        if(edit_turma.getText().toString().equals(""))
            conteudo.put(ajudante.T1_COLUMN5,edit_turma.getHint().toString());

        else
            conteudo.put(ajudante.T1_COLUMN5,edit_turma.getText().toString());

        db.update(ajudante.TABLE_NAME1,conteudo,"nome='" + nome +"'",null);

        super.finish();

    }

    public void voltar(View v){

        this.finish();

    }

}
