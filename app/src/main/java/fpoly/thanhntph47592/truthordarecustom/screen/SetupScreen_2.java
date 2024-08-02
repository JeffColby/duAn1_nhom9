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
import fpoly.thanhntph47592.truthordarecustom.adapter.NguoiChoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.HistoryFeatures;

public class SetupScreen_2 extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private GamePlayFeatures gamePlayFeatures;
    private HistoryFeatures historyFeatures;
    private LinearLayout btnTroLai;
    private Button btnThem,btnBatDau;
    private EditText edTenNguoiChoi;
    private NguoiChoiAdapter adapter;
    private RecyclerView rcNguoiChoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_screen2);

        basicFeatures=new BasicFeatures(SetupScreen_2.this);
        gamePlayFeatures=new GamePlayFeatures(SetupScreen_2.this);
        historyFeatures=new HistoryFeatures(SetupScreen_2.this);

        edTenNguoiChoi=findViewById(R.id.setUpScreen2_edTenNguoiChoi);
        btnTroLai=findViewById(R.id.setUpScreen2_btnTroLai);
        btnThem=findViewById(R.id.setUpScreen2_btnThem);
        btnBatDau=findViewById(R.id.setUpScreen2_btnBatDau);
        rcNguoiChoi=findViewById(R.id.setUpScreen2_rcNguoiChoi);

        ArrayList<String> arrayList=new ArrayList<>();
        adapter=new NguoiChoiAdapter(SetupScreen_2.this,arrayList);
        rcNguoiChoi.setAdapter(adapter);
        basicFeatures.caiDatRecycleView(rcNguoiChoi);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int viTri=bundle.getInt("ViTri");

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(SetupScreen_1.class);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.themNguoiChoi(edTenNguoiChoi,arrayList,adapter);
            }
        });
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.batDauChoi(viTri,arrayList, GamePlayScreen_1.class);
                if (adapter.getArrayList().size()>=2){
                    historyFeatures.themVaoLichSu(adapter.getArrayList(),viTri);
                }
            }
        });
    }
}