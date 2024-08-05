package fpoly.thanhntph47592.truthordarecustom.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    
    public DBHelper(@Nullable Context context) {
        super(context, "TRUTHORDARE.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QUESTIONGROUP(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT NOT NULL)");

        db.execSQL("CREATE TABLE QUESTION(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CONTENT TEXT NOT NULL," +
                "TYPE INTEGER NOT NULL," +
                "QUESTIONGROUP INTEGER REFERENCES QUESTIONGROUP(ID))");

        db.execSQL("CREATE TABLE GAMEPLAYDATA(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TIME TEXT NOT NULL," +
                "NUMPLAYER INTEGER NOT NULL," +
                "PLAYERNAME TEXT NOT NULL," +
                "QUESTION TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
