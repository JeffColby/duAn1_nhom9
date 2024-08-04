package fpoly.thanhntph47592.truthordarecustom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.database.DBHelper;
import fpoly.thanhntph47592.truthordarecustom.model.Question;
import fpoly.thanhntph47592.truthordarecustom.model.GamePlayData;

public class GamePlayDataDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public GamePlayDataDAO(Context context) {
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
    }

    public ArrayList<GamePlayData> allGamePlay(){
        ArrayList<GamePlayData> arrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM GAMEPLAYDATA ORDER BY ID DESC",null);
        while (cursor.moveToNext()){
            GamePlayData gamePlayData =new GamePlayData();
            gamePlayData.setId(cursor.getInt(0));
            gamePlayData.setTime(cursor.getString(1));
            gamePlayData.setNumPlayer(cursor.getInt(2));
            gamePlayData.setPlayerName(cursor.getString(3));
            gamePlayData.setQuestion(cursor.getString(4));
            arrayList.add(gamePlayData);
        }
        cursor.close();
        return arrayList;
    }

    public boolean deleteGamePlayData(GamePlayData gamePlayData){
        int check=database.delete("GAMEPLAYDATA","ID=?",
                new String[]{String.valueOf(gamePlayData.getId())});
        return check>0;
    }

    public boolean addGamePlayData(String time, ArrayList<String> playerArrayList, ArrayList<Question> question){
        ContentValues values=new ContentValues();
        values.put("TIME", time);
        values.put("NUMPLAYER", playerArrayList.size());
        values.put("PLAYERNAME", stringConcat("", playerArrayList));
        values.put("QUESTION", allQuestions(question));

        long check=database.insert("GAMEPLAYDATA",null,values);
        return check>0;
    }

    public String stringConcat(String nameType, ArrayList<String> arrayList){
        StringBuilder type = new StringBuilder();
        for (String element : arrayList) {
            type.append("\t").append(element).append("\n");
        }
        return nameType + type.toString();
    }

    public String allQuestions(ArrayList<Question> question){
        ArrayList<String> truthArrayList =new ArrayList<>();
        ArrayList<String> dareArrayList =new ArrayList<>();
        ArrayList<String> punishArrayList =new ArrayList<>();
        for (Question question1 : question){
            if (question1.getType()==0){
                truthArrayList.add(question1.getContent());
            } else if (question1.getType()==1) {
                dareArrayList.add(question1.getContent());
            }else {
                punishArrayList.add(question1.getContent());
            }
        }

        String truthQuestion = stringConcat("Sự thật: \n", truthArrayList);
        String dareQuestion = stringConcat("Thách thức: \n", dareArrayList);
        String punish = stringConcat("Hình phạt: \n", punishArrayList);

        return (truthQuestion +"\n"+ dareQuestion +"\n"+ punish);
    }
}
