package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.BoCauHoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class QuestionGroupsScreen extends AppCompatActivity {

    private static Context context;
    private ImageView btnMenu, btnThem;
    private RecyclerView rcBoCauHoi;
    private BasicFeatures basicFeatures;
    private QuestionGroupsFeatures questionGroupsFeatures;
    private BoCauHoiDAO boCauHoiDAO;
    private BoCauHoiAdapter adapter;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_groups_screen);
        context=this;

        boCauHoiDAO=new BoCauHoiDAO(QuestionGroupsScreen.this);
        basicFeatures=new BasicFeatures(QuestionGroupsScreen.this);
        questionGroupsFeatures =new QuestionGroupsFeatures(QuestionGroupsScreen.this);

        btnMenu=findViewById(R.id.questionGroupsScreen_btnMenu);
        btnThem=findViewById(R.id.questionGroupsScreen_btnThem);
        rcBoCauHoi=findViewById(R.id.questionGroupsScreen_rcBoCauHoi);

        PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu);
        basicFeatures.caiDatRecycleView(rcBoCauHoi);
        adapter=new BoCauHoiAdapter(QuestionGroupsScreen.this,boCauHoiDAO.tatCaBoCauHoi());
        rcBoCauHoi.setAdapter(adapter);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.themBoCauHoi(adapter);
            }
        });
    }
}