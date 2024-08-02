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
    private Spinner spHinhPhat;
    private Button btnHoanThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen4);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_4.this);

        spHinhPhat=findViewById(R.id.gamePlayScreen_4_spHinhPhat);
        btnHoanThanh=findViewById(R.id.gamePlayScreen_4_btnHoanThanh);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ArrayList<String> hinhPhat=bundle.getStringArrayList("HinhPhat");

        gamePlayFeatures.caiDatSpinnerHinhPhat(spHinhPhat,hinhPhat);

        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.batDauChoi(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"), GamePlayScreen_1.class);
            }
        });
    }
}