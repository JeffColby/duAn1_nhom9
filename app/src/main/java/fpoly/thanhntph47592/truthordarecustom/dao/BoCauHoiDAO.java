package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;

public class BoCauHoiDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public BoCauHoiDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<BoCauHoi> tatCaBoCauHoi(){
        ArrayList<BoCauHoi> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM BOCAUHOI",null);
        while (cursor.moveToNext()){
            BoCauHoi boCauHoi=new BoCauHoi();
            boCauHoi.setMaBoCauHoi(cursor.getInt(0));
            boCauHoi.setTenBoCauHoi(cursor.getString(1));
            arrayList.add(boCauHoi);
        }
        return arrayList;
    }

    public boolean themBoCauHoi(BoCauHoi boCauHoi){
        ContentValues values=new ContentValues();
        values.put("TENBOCAUHOI",boCauHoi.getTenBoCauHoi());
        long check=database.insert("BOCAUHOI",null,values);
        return check > 0;
    }

    public boolean suaBoCauHoi(BoCauHoi boCauHoi){
        ContentValues values=new ContentValues();
        values.put("TENBOCAUHOI",boCauHoi.getTenBoCauHoi());
        int check=database.update("BOCAUHOI",values,"MABOCAUHOI=?",
                new String[]{String.valueOf(boCauHoi.getMaBoCauHoi())});
        return check>0;
    }

    public boolean xoaBoCauHoi(BoCauHoi boCauHoi){
        int check= database.delete("BOCAUHOI","MABOCAUHOI=?",
                new String[]{String.valueOf(boCauHoi.getMaBoCauHoi())});
        return check>0;
    }
}
