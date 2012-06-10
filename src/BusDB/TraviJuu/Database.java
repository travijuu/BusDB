package BusDB.TraviJuu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Travego
 * Date: 13.01.2012
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
 */
public class Database {

    private SQLiteDatabase db;
    private final Context context;
    private final DatabaseHelper dbHelper;

    public final String DATABASE_NAME = "BusDB";
    public final String DATABASE_TABLE = "busdb_bus";
    public final int DATABASE_VERSION = 1;

    public Database(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    public void closeDB()
    {
        db.close();
    }


    public void openDB() throws SQLiteException
    {
        try
        {
            db = dbHelper.getWritableDatabase();
        }catch (SQLiteException e)
        {
            Log.v("Database açılamadı..",e.getMessage());
            db = dbHelper.getReadableDatabase();
        }
        //dbHelper.onCreate(db);
    }

    public long addBus(Bus bus)
    {
        ContentValues values = new ContentValues();
        values.put("BUS_PLATE",bus.getBUS_PLATE());
        values.put("BUS_BRAND",bus.getBUS_BRAND());
        values.put("BUS_MODEL",bus.getBUS_MODEL());
        values.put("BUS_CAPACITY",bus.getBUS_CAPACITY());
        values.put("BUS_YEAR",bus.getBUS_YEAR());
        values.put("BUS_COMPANY",bus.getBUS_COMPANY());
        values.put("BUS_REGISTER_DATE",bus.getBUS_REGISTER_DATE());
        return db.insert(DATABASE_TABLE,null,values );
    }

    public void updateBus()
    {

    }
    public boolean deleteBus()
    {
        return true;
    }
    public void deleteAll()
    {
        db.execSQL("Delete from " + DATABASE_TABLE);
    }
    public Cursor getBusCursor()
    {
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null, null);
        return cursor;
    }

    public ArrayList<String> getBusList()
    {
        ArrayList<String> busList = new ArrayList<String>();
        Cursor cursor = getBusCursor();

        while(cursor.moveToNext())
        {
            busList.add(cursor.getString(1));
        }

        return busList;
    }



}
