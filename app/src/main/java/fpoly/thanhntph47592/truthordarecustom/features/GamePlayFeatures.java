package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.PlayerAdapter;
import fpoly.thanhntph47592.truthordarecustom.adapter.SpinnerAdapter;
import fpoly.thanhntph47592.truthordarecustom.model.Question;
import fpoly.thanhntph47592.truthordarecustom.screen.GamePlayScreen_3;
import fpoly.thanhntph47592.truthordarecustom.screen.GamePlayScreen_4;
import fpoly.thanhntph47592.truthordarecustom.screen.HomeScreen;

public class GamePlayFeatures {

    private Context context;

    public GamePlayFeatures(Context context) {
        this.context = context;
    }

    public void addPlayer(EditText edPlayerName, ArrayList<String> arrayList, PlayerAdapter adapter){
        String playerName = edPlayerName.getText().toString();
        if (playerName.isEmpty()){
            Toast.makeText(context, "Người chơi không hợp lệ", Toast.LENGTH_SHORT).show();
        }else {
            String existedPlayer ="";
            for (String name :arrayList){
                if (name.equals(playerName)){
                    existedPlayer = name;
                    break;
                }
            }
            if (existedPlayer.equals("")){
                arrayList.add(playerName);
                edPlayerName.setText("");
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "Thêm người chơi thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Bạn cần đặt tên khác nhau để tránh nhầm lẫn", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void deletePlayer(int position, ArrayList<String> arrayList, PlayerAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn muốn xóa người chơi này?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void changePlayer(int position, String playerName, ArrayList<String> arrayList, PlayerAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.question_group_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        TextView tvTittle =view.findViewById(R.id.questionGroupDialog_tvTittle);
        EditText edPlayerName =view.findViewById(R.id.questionGroupDialog_edName);
        Button btnSave =view.findViewById(R.id.questionGroupDialog_btnAdd);
        Button btnCancel =view.findViewById(R.id.questionGroupDialog_btnCancel);
        tvTittle.setText("Thay đổi người chơi");
        btnSave.setText("Lưu");
        edPlayerName.setHint("Nhập tên người chơi mới");
        edPlayerName.setText(playerName);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPlayerName = edPlayerName.getText().toString();
                if (newPlayerName.isEmpty()){
                    Toast.makeText(context, "Người chơi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    String existedPlayer ="";
                    for (String name :arrayList){
                        if (name.equals(newPlayerName)){
                            existedPlayer = name;
                            break;
                        }
                    }
                    if (existedPlayer.equals("")){
                        arrayList.set(position, newPlayerName);
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Thay đổi người chơi thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Bạn cần đặt tên khác nhau để tránh nhầm lẫn", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void gamePlayExit(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Thoát trò chơi");
        builder.setMessage("Ván chơi sẽ được tính là kết thúc. Bạn xác nhận thoát?");
        builder.setNegativeButton("Ở lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new BasicFeatures(context).nextScreen(HomeScreen.class);
            }
        });
        builder.show();
    }

    public void startPlaying(int position, ArrayList<String> players, Class mClass){
        Intent intent=new Intent(context, mClass);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri", position);
        bundle.putStringArrayList("NguoiChoi", players);
        intent.putExtras(bundle);
        if (players !=null && players.size()<2){
            Toast.makeText(context, "Yêu cầu tối thiểu 2 người chơi", Toast.LENGTH_SHORT).show();
        }else {
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    public ArrayList<String> allTruthQuestions(ArrayList<Question> questionArrayList, Button button){
        ArrayList<String> truthArrayList =new ArrayList<>();
        for (Question question : questionArrayList){
            if (question.getType()==0){
                truthArrayList.add(question.getContent());
            }
        }
        if (truthArrayList.isEmpty()){
            button.setEnabled(false);
        }
        return truthArrayList;
    }

    public ArrayList<String> allDareQuestions(ArrayList<Question> questionArrayList, Button button){
        ArrayList<String> dareArrayList =new ArrayList<>();
        for (Question question : questionArrayList){
            if (question.getType()==1){
                dareArrayList.add(question.getContent());
            }
        }
        if (dareArrayList.isEmpty()){
            button.setEnabled(false);
        }
        return dareArrayList;
    }

    public ArrayList<String> allPunishments(ArrayList<Question> questionArrayList){
        ArrayList<String> punishmentArrayList =new ArrayList<>();
        for (Question question : questionArrayList){
            if (question.getType()==2){
                punishmentArrayList.add(question.getContent());
            }
        }
        return punishmentArrayList;
    }

    public void randomQuestion(ArrayList<String> questionArrayList, String playerName,
                               int position, ArrayList<String> players, ArrayList<String> punishment){
        int questionPosition = new Random().nextInt(questionArrayList.size());
        Intent intent=new Intent(context, GamePlayScreen_3.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri", position);
        bundle.putStringArrayList("NguoiChoi",players);
        bundle.putString("NguoiChoiDuocChon", playerName);
        bundle.putString("CauHoiDuocChon", questionArrayList.get(questionPosition));
        bundle.putStringArrayList("HinhPhat", punishment);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void punishmentSpinnerSetUp(Spinner spinner, ArrayList<String> arrayList){
        SpinnerAdapter adapter=new SpinnerAdapter(context,arrayList);
        spinner.setAdapter(adapter);
    }

    public void carryOutAPunishment(int position, ArrayList<String> players, ArrayList<String> punishment){
        Intent intent=new Intent(context, GamePlayScreen_4.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("HinhPhat", punishment);
        bundle.putInt("ViTri", position);
        bundle.putStringArrayList("NguoiChoi", players);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
