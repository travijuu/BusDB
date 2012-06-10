package BusDB.TraviJuu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: Travego
 * Date: 12.01.2012
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.v("Database Helper onCreate","Tablolar oluşturuluyor");
        String CREATE_TABLE_STRING = "CREATE TABLE IF NOT EXISTS busdb_bus ( BUS_ID integer primary key autoincrement, BUS_PLATE text, BUS_BRAND text, BUS_MODEL text, BUS_CAPACITY integer, BUS_YEAR integer, BUS_COMPANY text,BUS_REGISTER_DATE text); ";
        String TABLE_NAME = "BusDB";
        try
        {
           // sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
            sqLiteDatabase.execSQL(CREATE_TABLE_STRING);
            Log.v("Tablo oluşturuldu!","Evet");
        }catch (Exception e)
        {
            Log.v("Tablo oluşturma hatası!",e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.w("Upgrade islemi","Tum verile silinecek !");
        String TABLE_NAME = "";
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
