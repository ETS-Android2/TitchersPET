package com.example.dokas.titcherspetadmin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class provedor extends ContentProvider{

    private HandlerBD ajudante;
    private String DBNAME = "titchersPet";
    private SQLiteDatabase db;
    private String autoridade = "com.example.dokas.titcherspetadmin.provedor";
    private Cursor cursor;
    private int id;

    @Override
    public boolean onCreate(){

        ajudante = new HandlerBD(getContext(), DBNAME);
        return true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        db = ajudante.getReadableDatabase();

        if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/utilizadores")))

            cursor = db.query("utilizadores", projection, selection, selectionArgs, null, null, sortOrder);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/alunos")))

            cursor = db.query("alunos", projection, selection, selectionArgs, null, null, sortOrder);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/relatorios")))

            cursor = db.query("relatorios", projection, selection, selectionArgs, null, null, sortOrder);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/sumarios")))

            cursor = db.query("sumarios", projection, selection, selectionArgs, null, null, sortOrder);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/faltas")))

            cursor = db.query("faltas", projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;

    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues valor){

        db = ajudante.getWritableDatabase();

        if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/utilizadores")))

            id = (int) db.insert(ajudante.TABLE_NAME1, null, valor);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/alunos")))

            ajudante.addAluno(db,valor.get(ajudante.T2_COLUMN1).toString(),valor.get(ajudante.T2_COLUMN3).toString(),valor.get(ajudante.T2_COLUMN4).toString(),valor.get(ajudante.T2_COLUMN5).toString(),valor.get(ajudante.T2_COLUMN6).toString(),valor.get(ajudante.T2_COLUMN7).toString());

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/relatorios")))

            id = (int) db.insert(ajudante.TABLE_NAME3, null, valor);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/sumarios")))

            id = (int) db.insert(ajudante.TABLE_NAME4, null, valor);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/faltas")))

            id = (int) db.insert(ajudante.TABLE_NAME5, null, valor);

        db.close();

        return Uri.parse(autoridade+"/"+id);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        db = ajudante.getWritableDatabase();

        if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/utilizadores")))

            id = db.delete(ajudante.TABLE_NAME1, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/alunos")))

            id = db.delete(ajudante.TABLE_NAME2, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/relatorios")))

            id = db.delete(ajudante.TABLE_NAME3, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/sumarios")))

            id = db.delete(ajudante.TABLE_NAME4, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/faltas")))

            id = db.delete(ajudante.TABLE_NAME5, selection, selectionArgs);

        db.close();

        return id;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        db = ajudante.getWritableDatabase();

        if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/utilizadores")))

            id = db.update(ajudante.TABLE_NAME1, values, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/alunos")))

            id = db.update(ajudante.TABLE_NAME2, values, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/relatorios")))

            id = db.update(ajudante.TABLE_NAME3, values, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/sumarios")))

            id = db.update(ajudante.TABLE_NAME4, values, selection, selectionArgs);

        else if(uri.equals(Uri.parse("content://com.example.dokas.titcherspetadmin.provedor/faltas")))

            id = db.update(ajudante.TABLE_NAME5, values, selection, selectionArgs);

        db.close();

        return id;

    }

}
