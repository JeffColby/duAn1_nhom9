package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;

public class GamePlayScreen_3 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    TextView tvPlayerName, tvQuestion;
    Button btnComplete, btnCoinsident, btnSurrender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen3);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_3.this);

        tvPlayerName =findViewById(R.id.gamePlayScreen_3_tvPlayerName);
        tvQuestion=findViewById(R.id.gamePlayScreen_3_tvQuestion);
        btnComplete =findViewById(R.id.gamePlayScreen_3_btnComplete);
        btnCoinsident=findViewById(R.id.gamePlayScreen_3_btnCoinsident);
        btnSurrender=findViewById(R.id.gamePlayScreen_3_btnSurrender);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tvPlayerName.setText(bundle.getString("NguoiChoiDuocChon"));
        tvQuestion.setText(bundle.getString("CauHoiDuocChon"));
        tvQuestion.setMovementMethod(new ScrollingMovementMethod());

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.startPlaying(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"), GamePlayScreen_1.class);
            }
        });
        btnCoinsident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSurrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.carryOutAPunishment(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"),
                        bundle.getStringArrayList("HinhPhat"));
            }
        });
    }
}