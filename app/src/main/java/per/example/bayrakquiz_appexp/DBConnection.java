package per.example.bayrakquiz_appexp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {


    public DBConnection(@Nullable Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"bayraklar\" (\n" +
                "\t\"bayrak_Id\"\tINTEGER,\n" +
                "\t\"bayrak_Ad\"\tTEXT,\n" +
                "\t\"bayrak_Resim\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"bayrak_Id\" AUTOINCREMENT)\n" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bayraklar");
        onCreate(db);
    }
}
