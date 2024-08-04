package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.PlayerAdapter;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.HistoryFeatures;

public class SetupScreen_2 extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private GamePlayFeatures gamePlayFeatures;
    private HistoryFeatures historyFeatures;
    LinearLayout btnBack;
    Button btnAdd, btnStart;
    EditText edPlayerName;
    PlayerAdapter adapter;
    RecyclerView rcPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_screen2);

        basicFeatures=new BasicFeatures(SetupScreen_2.this);
        gamePlayFeatures=new GamePlayFeatures(SetupScreen_2.this);
        historyFeatures=new HistoryFeatures(SetupScreen_2.this);

        edPlayerName =findViewById(R.id.setUpScreen2_edPlayerName);
        btnBack =findViewById(R.id.setUpScreen2_btnBack);
        btnAdd =findViewById(R.id.setUpScreen2_btnAdd);
        btnStart =findViewById(R.id.setUpScreen2_btnStart);
        rcPlayer =findViewById(R.id.setUpScreen2_rcPlayer);

        ArrayList<String> arrayList=new ArrayList<>();
        adapter=new PlayerAdapter(SetupScreen_2.this,arrayList);
        rcPlayer.setAdapter(adapter);
        basicFeatures.recycleViewSetUp(rcPlayer);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int position =bundle.getInt("ViTri");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(SetupScreen_1.class);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.addPlayer(edPlayerName,arrayList,adapter);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.startPlaying(position,arrayList, GamePlayScreen_1.class);
                if (adapter.getArrayList().size()>=2){
                    historyFeatures.addGamePlayData(adapter.getArrayList(), position);
                }
            }
        });
    }
}