package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.QuestionAdapter;
import fpoly.thanhntph47592.truthordarecustom.adapter.SpinnerAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionDAO;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;
import fpoly.thanhntph47592.truthordarecustom.model.Question;
import fpoly.thanhntph47592.truthordarecustom.screen.SetupScreen_2;

public class QuestionsFeatures {

    private Context context;
    private QuestionGroupDAO questionGroupDAO;
    private QuestionDAO questionDAO;

    public QuestionsFeatures(Context context) {
        this.context = context;
        questionGroupDAO =new QuestionGroupDAO(context);
        questionDAO =new QuestionDAO(context);
    }

    public ArrayList<Question> selectedQuestions(int position){
        ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
        int questionGroup = questionGroupArrayList.get(position).getId();
        return questionDAO.filterQuestionByGroup(questionGroup);
    }

    public void questionGroupSpinnerSetUp(Spinner spinner){
        ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
        ArrayList<String> allQuestionGroup =new ArrayList<>();
        for (QuestionGroup questionGroup : questionGroupArrayList){
            allQuestionGroup.add(questionGroup.getName());
        }
        SpinnerAdapter adapter=new SpinnerAdapter(context, allQuestionGroup);
        spinner.setAdapter(adapter);
    }

    public void typeSpinnerSetUp(Spinner spinner){
        ArrayList<String> type =new ArrayList<>();
        type.add("Sự thật");
        type.add("Thách thức");
        type.add("Hình phạt");
        SpinnerAdapter adapter=new SpinnerAdapter(context, type);
        spinner.setAdapter(adapter);
    }

    public void filterByGroup(int position, QuestionAdapter questionAdapter, RecyclerView recyclerView){
        ArrayList<Question> questionArrayList = selectedQuestions(position);

        questionAdapter.setArrayList(questionArrayList);
        recyclerView.setAdapter(questionAdapter);
    }

    public void addQuestion(int position, QuestionAdapter adapter, RecyclerView recyclerView){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.question_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edContent =view.findViewById(R.id.questionDialog_edContent);
        Button btnAdd =view.findViewById(R.id.questionDialog_btnAdd);
        Button btnCancel =view.findViewById(R.id.questionDialog_btnCancel);
        Spinner spQuestionGroup =view.findViewById(R.id.questionDialog_spQuestionGroup);
        Spinner spType =view.findViewById(R.id.questionDialog_spType);
        typeSpinnerSetUp(spType);
        questionGroupSpinnerSetUp(spQuestionGroup);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edContent.getText().toString();
                if (content.isEmpty()){
                    Toast.makeText(context, "Nội dung không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
                    int questionGroup = questionGroupArrayList.get(spQuestionGroup.getSelectedItemPosition()).getId();
                    Question question =new Question(content, spType.getSelectedItemPosition(), questionGroup);
                    boolean check= questionDAO.addQuestion(question);
                    if (check){
                        Toast.makeText(context, "Thêm câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        if (position == spQuestionGroup.getSelectedItemPosition()){
                            adapter.setArrayList(questionDAO.filterQuestionByGroup(questionGroup));
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }
        });
    }

    public void filterByType(int position, int type, ArrayList<Question> checkQuestionArrayList,
                                      QuestionAdapter adapter, RecyclerView recyclerView){
        ArrayList<Question> fixedQuestionArrayList = selectedQuestions(position);

        if (checkQuestionArrayList.size() == fixedQuestionArrayList.size()){
            ArrayList<Question> newQuestionArrayList =new ArrayList<>();
            for (Question question : fixedQuestionArrayList){
                if (type == question.getType()){
                    newQuestionArrayList.add(question);
                }
            }
            adapter.setArrayList(newQuestionArrayList);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setArrayList(fixedQuestionArrayList);
            recyclerView.setAdapter(adapter);
        }
    }

    public void deleteQuestion(Question question, ArrayList<Question> arrayList, QuestionAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn muốn xóa câu hỏi này?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check= questionDAO.deleteQuestion(question);
                if (check){
                    Toast.makeText(context, "Xóa câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    arrayList.remove(question);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.show();
    }

    public void editQuestion(Question question, QuestionAdapter adapter, TextView tvType,
                          RelativeLayout typeLayout, ArrayList<Question> questionArrayList){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.question_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        TextView tvTittle =view.findViewById(R.id.questionDialog_tvTittle);
        EditText edContent =view.findViewById(R.id.questionDialog_edContent);
        Button btnSave =view.findViewById(R.id.questionDialog_btnAdd);
        Button btnCancel =view.findViewById(R.id.questionDialog_btnCancel);
        Spinner spQuestionGroup =view.findViewById(R.id.questionDialog_spQuestionGroup);
        Spinner spType =view.findViewById(R.id.questionDialog_spType);
        typeSpinnerSetUp(spType);
        questionGroupSpinnerSetUp(spQuestionGroup);
        btnSave.setText("Lưu");
        tvTittle.setText("Sửa câu hỏi");
        edContent.setText(question.getContent());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int oldQuestionGroup = question.getQuestionGroup();
                int oldType = question.getType();
                String newContent = edContent.getText().toString();
                if (newContent.isEmpty()){
                    Toast.makeText(context, "Nội dung không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
                    int newQuestionGroup = questionGroupArrayList.get(spQuestionGroup.getSelectedItemPosition()).getId();
                    int newType = spType.getSelectedItemPosition();
                    question.setContent(newContent);
                    question.setType(newType);
                    question.setQuestionGroup(newQuestionGroup);
                    boolean check= questionDAO.editQuestion(question);
                    if (check){
                        if (oldQuestionGroup == newQuestionGroup){
                            if (oldType != newType){
                                new BasicFeatures(context).colorSetUp(newType, tvType, typeLayout);
                            }
                        }else {
                            questionArrayList.remove(question);
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Sửa câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void checkQuestionQuantity(int position){
        ArrayList<Question> questionArrayList =selectedQuestions(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        if (questionArrayList.isEmpty()){
            builder.setTitle("Bộ câu hỏi trống");
            builder.setMessage("Bạn cần thêm câu hỏi hoặc chọn bộ câu hỏi khác để bắt đầu");
            builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    new QuestionGroupsFeatures(context).questionGroupDetail(position);
                }
            });
            builder.setNegativeButton("Chọn bộ khác", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }else {
            new GamePlayFeatures(context).startPlaying(position,null, SetupScreen_2.class);
        }
    }
}
