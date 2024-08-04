package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.QuestionGroupAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class QuestionGroupsScreen extends AppCompatActivity {

    private static Context context;
    ImageView btnMenu, btnAdd;
    RecyclerView rcQuestionGroup;
    BasicFeatures basicFeatures;
    QuestionGroupsFeatures questionGroupsFeatures;
    QuestionGroupDAO questionGroupDAO;
    QuestionGroupAdapter adapter;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_groups_screen);
        context=this;

        questionGroupDAO =new QuestionGroupDAO(QuestionGroupsScreen.this);
        basicFeatures=new BasicFeatures(QuestionGroupsScreen.this);
        questionGroupsFeatures =new QuestionGroupsFeatures(QuestionGroupsScreen.this);

        btnMenu=findViewById(R.id.questionGroupsScreen_btnMenu);
        btnAdd =findViewById(R.id.questionGroupsScreen_btnAdd);
        rcQuestionGroup =findViewById(R.id.questionGroupsScreen_rcQuestionGroup);

        PopupMenu popupMenu=basicFeatures.menuSetUp(btnMenu);
        basicFeatures.recycleViewSetUp(rcQuestionGroup);
        adapter=new QuestionGroupAdapter(QuestionGroupsScreen.this, questionGroupDAO.allQuestionGroup());
        rcQuestionGroup.setAdapter(adapter);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.addQuestionGroup(adapter);
            }
        });
    }
}