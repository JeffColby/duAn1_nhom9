package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.QuestionAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;

public class QuestionsScreen extends AppCompatActivity {

    private static Context context;
    BasicFeatures basicFeatures;
    QuestionsFeatures questionsFeatures;
    QuestionGroupsFeatures questionGroupsFeatures;
    QuestionGroupDAO questionGroupDAO;
    QuestionAdapter questionAdapter;
    ImageView btnMenu, btnAdd;
    Spinner spQuestionGroup;
    RecyclerView rcQuestion;
    LinearLayout btnTruth, btnDare, btnPunish;
    int position =-1;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        context=this;

        basicFeatures=new BasicFeatures(QuestionsScreen.this);
        questionsFeatures=new QuestionsFeatures(QuestionsScreen.this);
        questionGroupsFeatures=new QuestionGroupsFeatures(QuestionsScreen.this);
        questionGroupDAO =new QuestionGroupDAO(QuestionsScreen.this);
        questionAdapter =new QuestionAdapter(QuestionsScreen.this,null);

        btnMenu=findViewById(R.id.questionsScreen_btnMenu);
        btnAdd =findViewById(R.id.questionsScreen_btnAdd);
        spQuestionGroup =findViewById(R.id.questionsScreen_spQuestionGroup);
        rcQuestion =findViewById(R.id.questionsScreen_rcQuestion);
        btnTruth =findViewById(R.id.questionsScreen_btnTruth);
        btnDare =findViewById(R.id.questionsScreen_btnDare);
        btnPunish =findViewById(R.id.questionsScreen_btnPunish);

        ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
        if (questionGroupArrayList.isEmpty()){
            questionGroupsFeatures.checkQuestionGroup();
        }else {
            PopupMenu popupMenu=basicFeatures.menuSetUp(btnMenu);
            basicFeatures.recycleViewSetUp(rcQuestion);
            questionsFeatures.questionGroupSpinnerSetUp(spQuestionGroup);

            position = spQuestionGroup.getSelectedItemPosition();
            Intent intent=getIntent();
            Bundle bundle=intent.getExtras();
            if (bundle!=null){
                position =bundle.getInt("ViTri");
            }
            spQuestionGroup.setSelection(position);
            questionsFeatures.filterByGroup(position, questionAdapter, rcQuestion);

            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupMenu.show();
                }
            });
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.addQuestion(position, questionAdapter, rcQuestion);
                }
            });
            btnTruth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.filterByType(position,0, questionAdapter.getArrayList(),
                            questionAdapter, rcQuestion);
                }
            });
            btnDare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.filterByType(position,1, questionAdapter.getArrayList(),
                            questionAdapter, rcQuestion);
                }
            });
            btnPunish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.filterByType(position,2, questionAdapter.getArrayList(),
                            questionAdapter, rcQuestion);
                }
            });
            spQuestionGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    QuestionsScreen.this.position =position;
                    questionsFeatures.filterByGroup(QuestionsScreen.this.position, questionAdapter, rcQuestion);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}