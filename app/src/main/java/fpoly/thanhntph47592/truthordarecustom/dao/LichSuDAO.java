package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.LichSu;

public class LichSuDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public LichSuDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<LichSu> toanBoLichSu(){
        ArrayList<LichSu> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM VANCHOI ORDER BY MAVANCHOI DESC",null);
        while (cursor.moveToNext()){
            LichSu lichSu =new LichSu();
            lichSu.setMaVanChoi(cursor.getInt(0));
            lichSu.setNgayGio(cursor.getString(1));
            lichSu.setSoNguoiChoi(cursor.getInt(2));
            lichSu.setTenNguoiChoi(cursor.getString(3));
            lichSu.setCauHoi(cursor.getString(4));
            arrayList.add(lichSu);
        }
        return arrayList;
    }

    public boolean xoaVanChoi(LichSu lichSu){
        int check=database.delete("VANCHOI","MAVANCHOI=?",
                new String[]{String.valueOf(lichSu.getMaVanChoi())});
        return check>0;
    }

    public boolean themVanChoi(String ngayGio, ArrayList<String> nguoiChoi, ArrayList<CauHoi> cauHoi){
        ContentValues values=new ContentValues();
        values.put("NGAYGIO",ngayGio);
        values.put("SONGUOICHOI",nguoiChoi.size());
        values.put("TENNGUOICHOI", gopChuoi("",nguoiChoi));
        values.put("CAUHOI",toanBoCauHoi(cauHoi));

        long check=database.insert("VANCHOI",null,values);
        return check>0;
    }

    public String gopChuoi(String tenThanhPhan, ArrayList<String> arrayList){
        StringBuilder phanLoai = new StringBuilder();
        for (String phanTu : arrayList) {
            phanLoai.append("\t").append(phanTu).append("\n");
        }
        return tenThanhPhan + phanLoai.toString();
    }

    public String toanBoCauHoi(ArrayList<CauHoi> cauHoi){
        ArrayList<String> truthArrayList =new ArrayList<>();
        ArrayList<String> dareArrayList =new ArrayList<>();
        ArrayList<String> punishArrayList =new ArrayList<>();
        for (CauHoi cauHoi1:cauHoi){
            if (cauHoi1.getPhanLoai()==0){
                truthArrayList.add(cauHoi1.getNoiDung());
            } else if (cauHoi1.getPhanLoai()==1) {
                dareArrayList.add(cauHoi1.getNoiDung());
            }else {
                punishArrayList.add(cauHoi1.getNoiDung());
            }
        }

        String cauHoiTruth= gopChuoi("Sự thật: \n", truthArrayList);
        String cauHoiDare= gopChuoi("Thách thức: \n", dareArrayList);
        String cauHoiPunish= gopChuoi("Hình phạt: \n", punishArrayList);

        return (cauHoiTruth+"\n"+cauHoiDare+"\n"+cauHoiPunish);
    }
}
