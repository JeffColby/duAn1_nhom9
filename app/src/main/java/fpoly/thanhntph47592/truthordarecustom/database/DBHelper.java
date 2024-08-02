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
        db.execSQL("CREATE TABLE BOCAUHOI(" +
                "MABOCAUHOI INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENBOCAUHOI TEXT NOT NULL)");

        db.execSQL("CREATE TABLE CAUHOI(" +
                "MACAUHOI INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOIDUNG TEXT NOT NULL," +
                "PHANLOAI INTEGER NOT NULL," +
                "BOCAUHOI INTEGER REFERENCES BOCAUHOI(MABOCAUHOI))");

        db.execSQL("CREATE TABLE VANCHOI(" +
                "MAVANCHOI INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NGAYGIO TEXT NOT NULL," +
                "SONGUOICHOI INTEGER NOT NULL," +
                "TENNGUOICHOI TEXT NOT NULL," +
                "CAUHOI TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
