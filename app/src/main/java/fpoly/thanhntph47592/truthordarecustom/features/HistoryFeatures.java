package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.GamePlayDataAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.GamePlayDataDAO;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;
import fpoly.thanhntph47592.truthordarecustom.model.Question;
import fpoly.thanhntph47592.truthordarecustom.model.GamePlayData;

public class HistoryFeatures {

    private Context context;
    private GamePlayDataDAO gamePlayDataDAO;
    private QuestionGroupDAO questionGroupDAO;
    private QuestionDAO questionDAO;

    public HistoryFeatures(Context context) {
        this.context=context;
        gamePlayDataDAO =new GamePlayDataDAO(context);
        questionGroupDAO =new QuestionGroupDAO(context);
        questionDAO =new QuestionDAO(context);
    }

    public void addGamePlayData(ArrayList<String> players,int position){
        LocalDateTime localDateTime =LocalDateTime.now();
        String hour= localDateTime.getHour()+":";
        String minutes= localDateTime.getMinute()+" ";
        String day= localDateTime.getDayOfMonth()+"/";
        String month= localDateTime.getMonthValue()+"/";
        String year= localDateTime.getYear()+"";
        String time =hour+minutes+day+month+year;

        ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
        int questionGroup = questionGroupArrayList.get(position).getId();
        ArrayList<Question> questionArrayList = questionDAO.filterQuestionByGroup(questionGroup);

        gamePlayDataDAO.addGamePlayData(time, players, questionArrayList);
    }

    public void showGamePlayData(GamePlayData gamePlayData){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.gameplay_data_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        ImageView btnExit =view.findViewById(R.id.gamePlayData_btnExit);
        TextView tvTime =view.findViewById(R.id.gamePlayData_tvTime);
        TextView tvNumPlayer =view.findViewById(R.id.gamePlayData_tvNumPlayer);
        TextView tvPlayers =view.findViewById(R.id.gamePlayData_tvPlayers);
        TextView tvQuestions =view.findViewById(R.id.gamePlayData_tvQuestions);

        tvTime.setText("Ngày giờ: "+ gamePlayData.getTime());
        tvNumPlayer.setText("Số người chơi: "+(gamePlayData.getNumPlayer()));
        tvPlayers.setText(gamePlayData.getPlayerName());
        tvQuestions.setText(gamePlayData.getQuestion());

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void deleteGamePlayData(GamePlayData gamePlayData, ArrayList<GamePlayData> arrayList, GamePlayDataAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa lịch sử chơi");
        builder.setMessage("Tất cả thông tin của ván chơi này sẽ bị xóa vĩnh viễn. Xác nhận tiếp tục xóa?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check= gamePlayDataDAO.deleteGamePlayData(gamePlayData);
                if (check){
                    arrayList.remove(gamePlayData);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Xóa lịch sử thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
