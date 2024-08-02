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
    private Button btnLuatChoi, btnBoCauHoi, btnBatDauChoi;
    private ImageView btnMenu;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        context=this;

        BasicFeatures basicFeatures=new BasicFeatures(HomeScreen.this);

        btnLuatChoi=findViewById(R.id.homeScreen_btnLuatChoi);
        btnBoCauHoi=findViewById(R.id.homeScreen_btnBoCauHoi);
        btnBatDauChoi=findViewById(R.id.homeScreen_btnBatDauChoi);
        btnMenu=findViewById(R.id.homeScreen_btnMenu);

        PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu);

        btnBoCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(QuestionGroupsScreen.class);
            }
        });
        btnLuatChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(RulesScreen.class);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnBatDauChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(SetupScreen_1.class);
            }
        });
    }
}