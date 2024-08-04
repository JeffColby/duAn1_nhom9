package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;

public class QuestionGroupDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public QuestionGroupDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<QuestionGroup> allQuestionGroup(){
        ArrayList<QuestionGroup> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM QUESTIONGROUP",null);
        while (cursor.moveToNext()){
            QuestionGroup questionGroup =new QuestionGroup();
            questionGroup.setId(cursor.getInt(0));
            questionGroup.setName(cursor.getString(1));
            arrayList.add(questionGroup);
        }
        cursor.close();
        return arrayList;
    }

    public boolean addQuestionGroup(QuestionGroup questionGroup){
        ContentValues values=new ContentValues();
        values.put("NAME", questionGroup.getName());
        long check=database.insert("QUESTIONGROUP",null,values);
        return check > 0;
    }

    public boolean editQuestionGroup(QuestionGroup questionGroup){
        ContentValues values=new ContentValues();
        values.put("NAME", questionGroup.getName());
        int check=database.update("QUESTIONGROUP",values,"ID=?",
                new String[]{String.valueOf(questionGroup.getId())});
        return check>0;
    }

    public boolean deleteQuestionGroup(QuestionGroup questionGroup){
        int check= database.delete("QUESTIONGROUP","ID=?",
                new String[]{String.valueOf(questionGroup.getId())});
        return check>0;
    }
}
