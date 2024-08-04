package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class HomeScreen extends AppCompatActivity {

    private static Context context;
    Button btnHowToPlay, btnViewQuestionGroup, btnStartPlaying;
    ImageView btnMenu;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        context=this;

        BasicFeatures basicFeatures=new BasicFeatures(HomeScreen.this);

        btnHowToPlay =findViewById(R.id.homeScreen_btnHowToPlay);
        btnViewQuestionGroup =findViewById(R.id.homeScreen_btnViewQuestionGroup);
        btnStartPlaying =findViewById(R.id.homeScreen_btnStartPlaying);
        btnMenu=findViewById(R.id.homeScreen_btnMenu);

        PopupMenu popupMenu=basicFeatures.menuSetUp(btnMenu);

        btnViewQuestionGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(QuestionGroupsScreen.class);
            }
        });
        btnHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(RulesScreen.class);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnStartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(SetupScreen_1.class);
            }
        });
    }
}