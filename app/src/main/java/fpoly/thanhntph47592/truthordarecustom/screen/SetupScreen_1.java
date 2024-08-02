package fpoly.thanhntph47592.truthordarecustom.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;

public class SetupScreen_1 extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private QuestionsFeatures questionFeatures;
    private QuestionGroupsFeatures questionGroupsFeatures;
    private BoCauHoiDAO boCauHoiDAO;
    private Button btnThoat,btnTiepTheo;
    private Spinner spBoCauHoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_screen1);

        basicFeatures=new BasicFeatures(SetupScreen_1.this);
        questionFeatures=new QuestionsFeatures(SetupScreen_1.this);
        questionGroupsFeatures=new QuestionGroupsFeatures(SetupScreen_1.this);
        boCauHoiDAO=new BoCauHoiDAO(SetupScreen_1.this);

        btnThoat=findViewById(R.id.setUpScreen1_btnThoat);
        btnTiepTheo=findViewById(R.id.setUpScreen1_btnTieptheo);
        spBoCauHoi=findViewById(R.id.setUpScreen1_spBoCauHoi);
        questionFeatures.caiDatSpinnerBoCauHoi(spBoCauHoi);

        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        if (boCauHoiArrayList.isEmpty()){
            questionGroupsFeatures.kiemTraBoCauHoi();
        }

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    basicFeatures.chuyenMan(HomeScreen.class);
                }
        });
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionFeatures.kiemTraSoCauHoi(spBoCauHoi.getSelectedItemPosition());
            }
        });
    }
}