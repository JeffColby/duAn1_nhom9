package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;

public class GamePlayScreen_4 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    Spinner spPunishment;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen4);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_4.this);

        spPunishment=findViewById(R.id.gamePlayScreen_4_spPunishment);
        btnComplete =findViewById(R.id.gamePlayScreen_4_btnComplete);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ArrayList<String> punishments =bundle.getStringArrayList("HinhPhat");

        gamePlayFeatures.punishmentSpinnerSetUp(spPunishment, punishments);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.startPlaying(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"), GamePlayScreen_1.class);
            }
        });
    }
}