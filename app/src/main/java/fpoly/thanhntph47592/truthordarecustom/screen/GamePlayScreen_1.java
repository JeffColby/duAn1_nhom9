package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;

public class GamePlayScreen_1 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    ImageView btnExit;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen1);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_1.this);

        btnExit=findViewById(R.id.gamePlayScreen_1_btnExit);
        btnStart =findViewById(R.id.gamePlayScreen_1_btnStart);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.gamePlayExit();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.startPlaying(bundle.getInt("ViTri"),bundle.getStringArrayList("NguoiChoi"),
                        GamePlayScreen_2.class);
            }
        });
    }
}