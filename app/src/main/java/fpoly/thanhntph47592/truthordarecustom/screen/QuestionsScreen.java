package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.CauHoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;

public class QuestionsScreen extends AppCompatActivity {

    private static Context context;
    private BasicFeatures basicFeatures;
    private QuestionsFeatures questionsFeatures;
    private QuestionGroupsFeatures questionGroupsFeatures;
    private BoCauHoiDAO boCauHoiDAO;
    private CauHoiAdapter cauHoiAdapter;
    private ImageView btnMenu, btnThem;
    private Spinner spBoCauHoi;
    private RecyclerView rcCauHoi;
    private LinearLayout btnSuThat,btnThachThuc,btnHinhPhat;
    int viTri=-1;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_screen);
        context=this;

        basicFeatures=new BasicFeatures(QuestionsScreen.this);
        questionsFeatures=new QuestionsFeatures(QuestionsScreen.this);
        questionGroupsFeatures=new QuestionGroupsFeatures(QuestionsScreen.this);
        boCauHoiDAO=new BoCauHoiDAO(QuestionsScreen.this);
        cauHoiAdapter=new CauHoiAdapter(QuestionsScreen.this,null);

        btnMenu=findViewById(R.id.questionsScreen_btnMenu);
        btnThem=findViewById(R.id.questionsScreen_btnThem);
        spBoCauHoi=findViewById(R.id.questionsScreen_spBoCauHoi);
        rcCauHoi=findViewById(R.id.questionsScreen_rcCauHoi);
        btnSuThat=findViewById(R.id.questionsScreen_btnSuThat);
        btnThachThuc=findViewById(R.id.questionsScreen_btnThachThuc);
        btnHinhPhat=findViewById(R.id.questionsScreen_btnHinhPhat);

        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        if (boCauHoiArrayList.isEmpty()){
            questionGroupsFeatures.kiemTraBoCauHoi();
        }else {
            PopupMenu popupMenu=basicFeatures.caiDatMenu(btnMenu);
            basicFeatures.caiDatRecycleView(rcCauHoi);
            questionsFeatures.caiDatSpinnerBoCauHoi(spBoCauHoi);

            viTri=spBoCauHoi.getSelectedItemPosition();
            Intent intent=getIntent();
            Bundle bundle=intent.getExtras();
            if (bundle!=null){
                viTri=bundle.getInt("ViTri");
            }
            spBoCauHoi.setSelection(viTri);
            questionsFeatures.locCauHoiTheoBo(viTri,cauHoiAdapter,rcCauHoi);

            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupMenu.show();
                }
            });
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.themCauHoi(viTri,cauHoiAdapter,rcCauHoi);
                }
            });
            btnSuThat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.locCauHoiTheoPhanLoai(viTri,0,cauHoiAdapter.getArrayList(),
                            cauHoiAdapter,rcCauHoi);
                }
            });
            btnThachThuc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.locCauHoiTheoPhanLoai(viTri,1,cauHoiAdapter.getArrayList(),
                            cauHoiAdapter,rcCauHoi);
                }
            });
            btnHinhPhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionsFeatures.locCauHoiTheoPhanLoai(viTri,2,cauHoiAdapter.getArrayList(),
                            cauHoiAdapter,rcCauHoi);
                }
            });
            spBoCauHoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    viTri=position;
                    questionsFeatures.locCauHoiTheoBo(viTri,cauHoiAdapter,rcCauHoi);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}