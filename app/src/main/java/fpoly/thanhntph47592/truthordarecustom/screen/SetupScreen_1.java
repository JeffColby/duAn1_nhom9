package fpoly.thanhntph47592.truthordarecustom.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionGroupDAO;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;

public class SetupScreen_1 extends AppCompatActivity {

    BasicFeatures basicFeatures;
    QuestionsFeatures questionFeatures;
    QuestionGroupsFeatures questionGroupsFeatures;
    QuestionGroupDAO questionGroupDAO;
    Button btnExit, btnNext;
    Spinner spQuestionGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_screen1);

        basicFeatures=new BasicFeatures(SetupScreen_1.this);
        questionFeatures=new QuestionsFeatures(SetupScreen_1.this);
        questionGroupsFeatures=new QuestionGroupsFeatures(SetupScreen_1.this);
        questionGroupDAO =new QuestionGroupDAO(SetupScreen_1.this);

        btnExit =findViewById(R.id.setUpScreen1_btnExit);
        btnNext =findViewById(R.id.setUpScreen1_btnNext);
        spQuestionGroup =findViewById(R.id.setUpScreen1_spQuesionGroup);
        questionFeatures.questionGroupSpinnerSetUp(spQuestionGroup);

        ArrayList<QuestionGroup> questionGroupArrayList = questionGroupDAO.allQuestionGroup();
        if (questionGroupArrayList.isEmpty()){
            questionGroupsFeatures.checkQuestionGroup();
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    basicFeatures.nextScreen(HomeScreen.class);
                }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionFeatures.checkQuestionQuantity(spQuestionGroup.getSelectedItemPosition());
            }
        });
    }
}