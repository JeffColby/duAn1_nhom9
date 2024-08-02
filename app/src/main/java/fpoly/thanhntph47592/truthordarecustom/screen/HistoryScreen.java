package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.LichSuAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.LichSuDAO;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class HistoryScreen extends AppCompatActivity {

    private static  Context context;
    private BasicFeatures basicFeatures;
    private LichSuDAO lichSuDAO;
    private LichSuAdapter lichSuAdapter;
    private ImageView btnMenu;
    private RecyclerView rcLichSu;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);
        context=this;

        basicFeatures=new BasicFeatures(HistoryScreen.this);
        lichSuDAO =new LichSuDAO(HistoryScreen.this);
        lichSuAdapter=new LichSuAdapter(HistoryScreen.this, lichSuDAO.toanBoLichSu());

        btnMenu=findViewById(R.id.historyScreen_btnMenu);
        rcLichSu=findViewById(R.id.historyScreen_rcLichSu);

        basicFeatures.caiDatRecycleView(rcLichSu);
        rcLichSu.setAdapter(lichSuAdapter);
        PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }
}