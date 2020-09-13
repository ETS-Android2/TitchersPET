package com.example.dokas.titcherspetadmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HandlerBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "titchersPet";
    protected static final String TABLE_NAME1 = "utilizadores";
    protected static final String T1_COLUMN1 = "nome";
    protected static final String T1_COLUMN2 = "password";
    protected static final String T1_COLUMN3 = "admin";
    protected static final String T1_COLUMN4 = "confirmação";
    protected static final String T1_COLUMN5 = "turma";

    protected static final String TABLE_NAME2 = "alunos";
    protected static final String T2_COLUMN1 = "nome";
    protected static final String T2_COLUMN2 = "id";
    protected static final String T2_COLUMN3 = "turma";
    protected static final String T2_COLUMN4 = "idade";
    protected static final String T2_COLUMN5 = "alergias";
    protected static final String T2_COLUMN6 = "telemovel";
    protected static final String T2_COLUMN7 = "email";

    protected static final String TABLE_NAME3 = "relatorios";
    protected static final String T3_COLUMN1 = "idAluno";
    protected static final String T3_COLUMN2 = "data";
    protected static final String T3_COLUMN3 = "comeu";
    protected static final String T3_COLUMN4 = "dormiu";
    protected static final String T3_COLUMN5 = "xixi";
    protected static final String T3_COLUMN6 = "coco";
    protected static final String T3_COLUMN7 = "magoou";
    protected static final String T3_COLUMN8 = "chorou";
    protected static final String T3_COLUMN9 = "turma";
    protected static final String T3_COLUMN10 = "notas";

    protected static final String TABLE_NAME4 = "sumarios";
    protected static final String T4_COLUMN1 = "turma";
    protected static final String T4_COLUMN2 = "data";
    protected static final String T4_COLUMN3 = "sumario";

    protected static final String TABLE_NAME5 = "faltas";
    protected static final String T5_COLUMN1 = "turma";
    protected static final String T5_COLUMN2 = "data";
    protected static final String T5_COLUMN3 = "id";
    protected static final String T5_COLUMN4 = "falta";

    private static final String UTILIZADORES_TABLE_CREATE =

            "CREATE TABLE " + TABLE_NAME1 + " (" +
                    T1_COLUMN1 + " VARCHAR(50), " +
                    T1_COLUMN2 + " VARCHAR(50), " +
                    T1_COLUMN3 + " INT, " +
                    T1_COLUMN4 + " INT, " +
                    T1_COLUMN5 + " VARCHAR(5));";

    private static final String ALUNOS_TABLE_CREATE =

            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    T2_COLUMN1 + " VARCHAR(50), " +
                    T2_COLUMN2 + " INTEGER PRIMARY KEY, " +
                    T2_COLUMN3 + " VARCHAR(5), " +
                    T2_COLUMN4 + " VARCHAR(5), " +
                    T2_COLUMN5 + " VARCHAR(500), " +
                    T2_COLUMN6 + " VARCHAR(12), " +
                    T2_COLUMN7 + " VARCHAR(20));";

    private static final String RELATORIO_TABLE_CREATE =

            "CREATE TABLE " + TABLE_NAME3 + " (" +
                    T3_COLUMN1 + " INT, " +
                    T3_COLUMN2 + " VARCHAR(10), " +
                    T3_COLUMN3 + " INT, " +
                    T3_COLUMN4 + " INT, " +
                    T3_COLUMN5 + " INT, " +
                    T3_COLUMN6 + " INT, " +
                    T3_COLUMN7 + " INT, " +
                    T3_COLUMN8 + " INT, " +
                    T3_COLUMN9 + " VARCHAR(5), " +
                    T3_COLUMN10 + " VARCHAR(1000));";

    private static final String SUMARIO_TABLE_CREATE =

            "CREATE TABLE " + TABLE_NAME4 + " (" +
                    T4_COLUMN1 + " VARCHAR(5), " +
                    T4_COLUMN2 + " VARCHAR(10), " +
                    T4_COLUMN3 + " VARCHAR(2000)); ";

    private static final String FALTAS_TABLE_CREATE =

            "CREATE TABLE " + TABLE_NAME5 + " (" +
                    T5_COLUMN1 + " VARCHAR(5), " +
                    T5_COLUMN2 + " VARCHAR(10), " +
                    T5_COLUMN3 + " INT, " +
                    T5_COLUMN4 + " INT); ";

    private static final String UTILIZADORES_TABLE_DROP =
            "DROP TABLE " + TABLE_NAME1 + ";";

    private static final String ALUNOS_TABLE_DROP =
            "DROP TABLE " + TABLE_NAME2 + ";";

    private static final String RELATORIO_TABLE_DROP =
            "DROP TABLE " + TABLE_NAME3 + ";";

    private static final String SUMARIO_TABLE_DROP =
            "DROP TABLE " + TABLE_NAME4 + ";";

    private static final String FALTAS_TABLE_DROP =
            "DROP TABLE " + TABLE_NAME5 + ";";

    private static final String UTILIZADORES_TABLE_TEMP =
            "CREATE TEMP TABLE auxiliar AS SELECT * FROM " + TABLE_NAME1 + ";";

    private static final String ALUNOS_TABLE_TEMP =
            "CREATE TEMP TABLE auxiliar AS SELECT * FROM " + TABLE_NAME2 + ";";

    private static final String RELATORIO_TABLE_TEMP =
            "CREATE TEMP TABLE auxiliar AS SELECT * FROM " + TABLE_NAME3 + ";";

    private static final String SUMARIO_TABLE_TEMP =
            "CREATE TEMP TABLE auxiliar AS SELECT * FROM " + TABLE_NAME4 + ";";

    private static final String FALTAS_TABLE_TEMP =
            "CREATE TEMP TABLE auxiliar AS SELECT * FROM " + TABLE_NAME5 + ";";

    private static final String UTILIZADORES_TABLE_INSERT =
            String.format("INSERT INTO %s (%s, %s, %s, %s, %s) SELECT * FROM %s;", TABLE_NAME1, T1_COLUMN1, T1_COLUMN2, T1_COLUMN3, T1_COLUMN4, T1_COLUMN5, UTILIZADORES_TABLE_TEMP);

    private static final String ALUNOS_TABLE_INSERT =
            String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) SELECT * FROM %s;", TABLE_NAME2, T2_COLUMN1, T2_COLUMN2, T2_COLUMN3, T2_COLUMN4, T2_COLUMN5, T2_COLUMN6, T2_COLUMN7, ALUNOS_TABLE_TEMP);

    private static final String RELATORIO_TABLE_INSERT =
            String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) SELECT * FROM %s;", TABLE_NAME3, T3_COLUMN1, T3_COLUMN2, T3_COLUMN3, T3_COLUMN4, T3_COLUMN5, T3_COLUMN6, T3_COLUMN7, T3_COLUMN8, T3_COLUMN9, T3_COLUMN10, RELATORIO_TABLE_TEMP);

    private static final String SUMARIO_TABLE_INSERT =
            String.format("INSERT INTO %s (%s, %s, %s) SELECT * FROM %s;", TABLE_NAME4, T4_COLUMN1, T4_COLUMN2, T4_COLUMN3, SUMARIO_TABLE_TEMP);

    private static final String FALTAS_TABLE_INSERT =
            String.format("INSERT INTO %s (%s, %s, %s, %S) SELECT * FROM %s;", TABLE_NAME5, T5_COLUMN1, T5_COLUMN2, T5_COLUMN3, T5_COLUMN4, FALTAS_TABLE_TEMP);

    HandlerBD(Context context, String DB_NAME){

        super(context, DB_NAME, null, DATABASE_VERSION);

    }

    public void addUser(SQLiteDatabase db, String nome, String password, int admin, int conf, String turma){

        db.execSQL("INSERT INTO " + TABLE_NAME1 + " (" + T1_COLUMN1 + "," + T1_COLUMN2 + "," + T1_COLUMN3 + "," + T1_COLUMN4 + "," + T1_COLUMN5 + ")" + " VALUES ('" + nome + "', '" + password + "', " + admin + ", " + conf + ", '" + turma + "');");

    }

    public void addAluno(SQLiteDatabase db, String nome, String turma, String idade, String alergias, String telemovel, String email){

        db.execSQL("INSERT INTO " + TABLE_NAME2 + "(" + T2_COLUMN1 + "," + T2_COLUMN2 + "," +
                T2_COLUMN3 + "," + T2_COLUMN4 + "," + T2_COLUMN5 + "," + T2_COLUMN6 + "," + T2_COLUMN7 + ")" +
                " VALUES('" + nome + "', NULL, '" + turma + "', '" + idade + "', '" + alergias + "', '" +
                telemovel + "', '" + email + "');");
    }

    public void onCreate(SQLiteDatabase db) {

        //Criar tabelas
        db.execSQL(UTILIZADORES_TABLE_CREATE);
        db.execSQL(ALUNOS_TABLE_CREATE);
        db.execSQL(RELATORIO_TABLE_CREATE);
        db.execSQL(SUMARIO_TABLE_CREATE);
        db.execSQL(FALTAS_TABLE_CREATE);

        addUser(db, "admin", "21232f297a57a5a743894a0e4a801fc3", 1, 1, "1A");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Metodo para dar update da base de dados sem perda de dados
        db.execSQL(UTILIZADORES_TABLE_TEMP);
        db.execSQL(UTILIZADORES_TABLE_DROP);
        db.execSQL(UTILIZADORES_TABLE_CREATE);
        db.execSQL(UTILIZADORES_TABLE_INSERT);

        db.execSQL(ALUNOS_TABLE_TEMP);
        db.execSQL(ALUNOS_TABLE_DROP);
        db.execSQL(ALUNOS_TABLE_CREATE);
        db.execSQL(ALUNOS_TABLE_INSERT);

        db.execSQL(RELATORIO_TABLE_TEMP);
        db.execSQL(RELATORIO_TABLE_DROP);
        db.execSQL(RELATORIO_TABLE_CREATE);
        db.execSQL(RELATORIO_TABLE_INSERT);

        db.execSQL(SUMARIO_TABLE_TEMP);
        db.execSQL(SUMARIO_TABLE_DROP);
        db.execSQL(SUMARIO_TABLE_CREATE);
        db.execSQL(SUMARIO_TABLE_INSERT);

        db.execSQL(FALTAS_TABLE_TEMP);
        db.execSQL(FALTAS_TABLE_DROP);
        db.execSQL(FALTAS_TABLE_CREATE);
        db.execSQL(FALTAS_TABLE_INSERT);

    }

}
