package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.Question;

public class GamePlayScreen_2 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    private QuestionsFeatures questionsFeatures;
    TextView tvPlayerName;
    ImageView btnExit;
    Button btnTruth,btnDare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen2);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_2.this);
        questionsFeatures=new QuestionsFeatures(GamePlayScreen_2.this);

        tvPlayerName =findViewById(R.id.gamePlayScreen_2_tvPlayerName);
        btnExit=findViewById(R.id.gamePlayScreen_2_btnExit);
        btnTruth =findViewById(R.id.gamePlayScreen_2_btnTruth);
        btnDare=findViewById(R.id.gamePlayScreen_2_btnDare);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int position =bundle.getInt("ViTri");
        ArrayList<String> player =bundle.getStringArrayList("NguoiChoi");

        ArrayList<Question> questionArrayList =questionsFeatures.selectedQuestions(position);
        ArrayList<String> truthQuestions =gamePlayFeatures.allTruthQuestions(questionArrayList, btnTruth);
        ArrayList<String> dareQuestions =gamePlayFeatures.allDareQuestions(questionArrayList,btnDare);
        ArrayList<String> punishments =gamePlayFeatures.allPunishments(questionArrayList);

        int randomPlayer = new Random().nextInt(player.size());
        String playerName = player.get(randomPlayer);
        tvPlayerName.setText(playerName);
        tvPlayerName.setMovementMethod(new ScrollingMovementMethod());

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.gamePlayExit();
            }
        });
        btnTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.randomQuestion(truthQuestions, playerName, position, player, punishments);
            }
        });
        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.randomQuestion(dareQuestions, playerName, position, player, punishments);
            }
        });
    }
}