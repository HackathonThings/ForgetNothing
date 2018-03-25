package com.example.hackbdx.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipe on 24/03/18.
 */

public class EjemploDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Maleta.db";

    public static final String TABLA_NOMBRES = "BasicClothes";
    public static final String COLUMNA_ID = "_id";
    public static final String NOMBRE_CIUDAD = "city" ;
    public static final String MULTIPLICADOR = "multip" ;

    private static final String SQL_CREAR = "create table "
            + TABLA_NOMBRES + "(" + COLUMNA_ID
            + " integer primary key autoincrement, " +
            NOMBRE_CIUDAD + " text not null)" +
            MULTIPLICADOR + "float)";

    public EjemploDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void nueva_tabla (String nom_taula, String pri_col, String seg_col, String multu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLA_NOMBRES, nom_taula);
        values.put(COLUMNA_ID, pri_col);
        values.put(NOMBRE_CIUDAD, seg_col);
        values.put(MULTIPLICADOR, multu);

        db.insert(TABLA_NOMBRES, null,values);
    }

    public int agregar(String nombre, float mult){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOMBRE_CIUDAD, nombre);
        values.put(MULTIPLICADOR, mult);

        long newRowId;
        newRowId = db.insert(TABLA_NOMBRES, null,values);
        db.close();

        return (int) newRowId;

    }

    public void obtener(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMNA_ID, NOMBRE_CIUDAD, MULTIPLICADOR};

        Cursor cursor =
                db.query(TABLA_NOMBRES,
                        projection,
                        " _id = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);


        if (cursor != null)
            cursor.moveToFirst();

        System.out.println("El nombre es " +  cursor.getString(1) );

        db.close();

    }

    public void actualizar (String nombre, int id){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre",nombre);

        int i = db.update(TABLA_NOMBRES,
                values,
                " _id = ?",
                new String[] { String.valueOf( id ) });
        db.close();
    }

    public boolean eliminar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLA_NOMBRES,
                    " _id = ?",
                    new String[] { String.valueOf (id ) });
            db.close();
            return true;

        }catch(Exception ex){
            return false;
        }
    }
}




/*
package com.felipe.testing;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EjemploDB db = new EjemploDB( getApplicationContext() );
        int id = db.agregar("Underpants", 1);
        db.agregar("Socks", 1);
        db.agregar("T-shirt", 2/3);
        System.out.println(id);
        db.obtener(id);

        db.nueva_tabla("Mireia", "id", "nom", "mult");

    }
}
*/
