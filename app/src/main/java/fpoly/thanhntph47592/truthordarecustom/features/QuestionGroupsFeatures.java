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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.QuestionGroupAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;
import fpoly.thanhntph47592.truthordarecustom.screen.HomeScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionGroupsScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionsScreen;

public class QuestionGroupsFeatures {

    private Context context;
    private QuestionGroupDAO questionGroupDAO;

    public QuestionGroupsFeatures(Context context) {
        this.context = context;
        questionGroupDAO =new QuestionGroupDAO(context);
    }

    public void addQuestionGroup(QuestionGroupAdapter adapter){
        Toast.makeText(context, "Bạn nên đặt tên bộ câu hỏi khác nhau để tránh nhầm lẫn", Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.question_group_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edName =view.findViewById(R.id.questionGroupDialog_edName);
        Button btnAdd =view.findViewById(R.id.questionGroupDialog_btnAdd);
        Button btnCancel =view.findViewById(R.id.questionGroupDialog_btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(context, "Bộ câu hỏi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    QuestionGroup questionGroup =new QuestionGroup(name);
                    boolean check= questionGroupDAO.addQuestionGroup(questionGroup);
                    if (check){
                        Toast.makeText(context, "Thêm bộ câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        adapter.setArrayList(questionGroupDAO.allQuestionGroup());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void deleteQuestionGroup(int numQuestion, QuestionGroup questionGroup, ArrayList<QuestionGroup> arrayList,
                            QuestionGroupAdapter adapter){
        if (numQuestion >0){
            Toast.makeText(context, "Không thể xóa bộ câu hỏi khi vẫn còn câu hỏi bên trong",
                    Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setMessage("Bạn muốn xóa bộ câu hỏi này?");
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean check= questionGroupDAO.deleteQuestionGroup(questionGroup);
                    if (check){
                        Toast.makeText(context, "Xóa bộ câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        arrayList.remove(questionGroup);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        }
    }

    public void editQuestionGroup(QuestionGroup questionGroup, QuestionGroupAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.question_group_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edName =view.findViewById(R.id.questionGroupDialog_edName);
        Button btnSave =view.findViewById(R.id.questionGroupDialog_btnAdd);
        Button btnCancel =view.findViewById(R.id.questionGroupDialog_btnCancel);
        TextView tvTittle =view.findViewById(R.id.questionGroupDialog_tvTittle);

        tvTittle.setText("Sửa bộ câu hỏi");
        btnSave.setText("Lưu");
        edName.setText(questionGroup.getName());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestionGroup = edName.getText().toString();
                if (newQuestionGroup.isEmpty()){
                    Toast.makeText(context, "Bộ câu hỏi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    questionGroup.setName(newQuestionGroup);
                    boolean check= questionGroupDAO.editQuestionGroup(questionGroup);
                    if (check){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void questionGroupDetail(int position){
        Intent intent=new Intent(context, QuestionsScreen.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri", position);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public void checkQuestionGroup(){
        BasicFeatures basicFeatures=new BasicFeatures(context);
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Chưa có bộ câu hỏi");
        builder.setMessage("Bạn cần phải thêm bộ câu hỏi trước");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                basicFeatures.nextScreen(QuestionGroupsScreen.class);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                basicFeatures.nextScreen(HomeScreen.class);
            }
        });
        builder.show();
    }
}
