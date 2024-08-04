package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.GamePlayDataAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.GamePlayDataDAO;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class HistoryScreen extends AppCompatActivity {

    private static Context context;
    BasicFeatures basicFeatures;
    GamePlayDataDAO gamePlayDataDAO;
    GamePlayDataAdapter gamePlayDataAdapter;
    ImageView btnMenu;
    RecyclerView rcHistory;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);
        context=this;

        basicFeatures=new BasicFeatures(HistoryScreen.this);
        gamePlayDataDAO =new GamePlayDataDAO(HistoryScreen.this);
        gamePlayDataAdapter =new GamePlayDataAdapter(HistoryScreen.this, gamePlayDataDAO.allGamePlay());

        btnMenu=findViewById(R.id.historyScreen_btnMenu);
        rcHistory =findViewById(R.id.historyScreen_rcHistory);

        basicFeatures.recycleViewSetUp(rcHistory);
        rcHistory.setAdapter(gamePlayDataAdapter);
        PopupMenu popupMenu=basicFeatures.menuSetUp(btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }
}