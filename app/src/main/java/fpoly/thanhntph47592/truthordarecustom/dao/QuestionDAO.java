package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.Question;

public class QuestionDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public QuestionDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<Question> filterQuestionByGroup(int id){
        ArrayList<Question> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM QUESTION WHERE QUESTIONGROUP=? ORDER BY TYPE",
                new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
            Question question =new Question();
            question.setId(cursor.getInt(0));
            question.setContent(cursor.getString(1));
            question.setType(cursor.getInt(2));
            question.setQuestionGroup(cursor.getInt(3));
            arrayList.add(question);
        }
        cursor.close();
        return arrayList;
    }

    public boolean addQuestion(Question question){
        ContentValues values=new ContentValues();
        values.put("CONTENT", question.getContent());
        values.put("TYPE", question.getType());
        values.put("QUESTIONGROUP", question.getQuestionGroup());
        long check= database.insert("QUESTION",null,values);
        return check>0;
    }

    public boolean editQuestion(Question question){
        ContentValues values=new ContentValues();
        values.put("CONTENT", question.getContent());
        values.put("TYPE", question.getType());
        values.put("QUESTIONGROUP", question.getQuestionGroup());
        int check= database.update("QUESTION",values,"ID=?",
                new String[]{String.valueOf(question.getId())});
        return check>0;
    }

    public boolean deleteQuestion(Question question){
        int check= database.delete("QUESTION","ID=?",
                new String[]{String.valueOf(question.getId())});
        return check>0;
    }
}
