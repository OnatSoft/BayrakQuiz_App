package per.example.bayrakquiz_appexp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BayraklarDao {

    public ArrayList<Bayraklar> rastgeleGetir(DBConnection db) {

        ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();
        SQLiteDatabase sqdb = db.getWritableDatabase();
        Cursor c = sqdb.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 10", null);

        while (c.moveToNext()) {
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndexOrThrow("bayrak_Id"))
                    , c.getString(c.getColumnIndexOrThrow("bayrak_Ad"))
                    , c.getString(c.getColumnIndexOrThrow("bayrak_Resim")));
            bayraklarArrayList.add(b);
        }

        return bayraklarArrayList;
    }

    public ArrayList<Bayraklar> rastgele3YanlisGetir(DBConnection db, int bayrak_Id) {

        ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();
        SQLiteDatabase sqdb = db.getWritableDatabase();
        Cursor c = sqdb.rawQuery("SELECT * FROM bayraklar WHERE bayrak_Id != " + bayrak_Id + " ORDER BY RANDOM() LIMIT 3", null);

        while (c.moveToNext()) {
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndexOrThrow("bayrak_Id"))
                    , c.getString(c.getColumnIndexOrThrow("bayrak_Ad"))
                    , c.getString(c.getColumnIndexOrThrow("bayrak_Resim")));
            bayraklarArrayList.add(b);
        }

        return bayraklarArrayList;
    }
}
