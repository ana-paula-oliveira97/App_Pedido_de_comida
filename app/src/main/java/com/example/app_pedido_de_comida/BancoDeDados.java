package com.example.app_pedido_de_comida;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BancoDeDados extends SQLiteOpenHelper {

    public BancoDeDados(@Nullable Context context) {
        super(context, "pedido", null, 1);
    }

    public long insereprodutos(String client, String product,String flavor, Double value) {
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("client", client);
        registro.put("product", product);
        registro.put("flavor", flavor);
        registro.put("value", value);
        long id = banco.insert("pedido", null, registro);
        banco.close();
        return id;
    }

    public ArrayList<String> consultar() {
        SQLiteDatabase banco = this.getReadableDatabase();
        String sql = "SELECT * FROM pedido";
        ArrayList<String> resultado = null;

        Cursor c = banco.rawQuery(sql, null);
        if (c.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String registro;
                registro = "\n client : " + c.getString(1);
                registro+= "\n product      : " + c.getString(2);
                registro+= "\n flavor    : " + c.getString(3);
                registro+= "\n value     : " + c.getDouble(4);

                resultado.add(registro);
            } while (c.moveToNext());
        }
        banco.close();
        return resultado;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE pedido( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "client TEXT, " +
                "product TEXT, " +
                "flavor TEXT," +
                "value Real " +
                ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
