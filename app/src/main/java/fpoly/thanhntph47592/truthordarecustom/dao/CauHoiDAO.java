package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;

public class CauHoiDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public CauHoiDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<CauHoi> cauHoiTheoBo(int maBoCauHoi){
        ArrayList<CauHoi> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM CAUHOI WHERE BOCAUHOI=? ORDER BY PHANLOAI",
                new String[]{String.valueOf(maBoCauHoi)});
        while (cursor.moveToNext()){
            CauHoi cauHoi=new CauHoi();
            cauHoi.setMaCauHoi(cursor.getInt(0));
            cauHoi.setNoiDung(cursor.getString(1));
            cauHoi.setPhanLoai(cursor.getInt(2));
            cauHoi.setBoCauHoi(cursor.getInt(3));
            arrayList.add(cauHoi);
        }
        return arrayList;
    }

    public boolean themCauHoi(CauHoi cauHoi){
        ContentValues values=new ContentValues();
        values.put("NOIDUNG",cauHoi.getNoiDung());
        values.put("PHANLOAI",cauHoi.getPhanLoai());
        values.put("BOCAUHOI",cauHoi.getBoCauHoi());
        long check= database.insert("CAUHOI",null,values);
        return check>0;
    }

    public boolean suaCauHoi(CauHoi cauHoi){
        ContentValues values=new ContentValues();
        values.put("NOIDUNG",cauHoi.getNoiDung());
        values.put("PHANLOAI",cauHoi.getPhanLoai());
        values.put("BOCAUHOI",cauHoi.getBoCauHoi());
        int check= database.update("CAUHOI",values,"MACAUHOI=?",
                new String[]{String.valueOf(cauHoi.getMaCauHoi())});
        return check>0;
    }

    public boolean xoaCauHoi(CauHoi cauHoi){
        int check= database.delete("CAUHOI","MACAUHOI=?",
                new String[]{String.valueOf(cauHoi.getMaCauHoi())});
        return check>0;
    }
}
