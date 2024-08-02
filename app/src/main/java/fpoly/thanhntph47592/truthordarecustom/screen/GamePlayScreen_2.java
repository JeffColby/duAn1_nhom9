package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;

public class GamePlayScreen_2 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    private TextView tvTenNguoiChoi;
    private ImageView btnThoat;
    private Button btnSuThat,btnThachThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen2);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_2.this);

        tvTenNguoiChoi=findViewById(R.id.gamePlayScreen_2_tvTenNguoiChoi);
        btnThoat=findViewById(R.id.gamePlayScreen_2_btnThoat);
        btnSuThat=findViewById(R.id.gamePlayScreen_2_btnSuThat);
        btnThachThuc=findViewById(R.id.gamePlayScreen_2_btnThachThuc);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int viTri=bundle.getInt("ViTri");
        ArrayList<String> nguoiChoi=bundle.getStringArrayList("NguoiChoi");

        ArrayList<CauHoi> danhSachCauHoi=gamePlayFeatures.danhSachCauHoiDuocChon(viTri);
        ArrayList<String> tatCaCauHoiTruth=gamePlayFeatures.tatCaCauHoiTruth(danhSachCauHoi,btnSuThat);
        ArrayList<String> tatCaCauHoiDare =gamePlayFeatures.tatCaCauHoiDare(danhSachCauHoi,btnThachThuc);
        ArrayList<String> tatCaHinhPhat=gamePlayFeatures.tatCaHinhPhat(danhSachCauHoi);

        int nguoiChoiBatKi = new Random().nextInt(nguoiChoi.size());
        String tenNguoiChoi=nguoiChoi.get(nguoiChoiBatKi);
        tvTenNguoiChoi.setText(tenNguoiChoi);
        tvTenNguoiChoi.setMovementMethod(new ScrollingMovementMethod());

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.thoatTroChoi();
            }
        });
        btnSuThat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.cauHoitBatKi(tatCaCauHoiTruth,tenNguoiChoi,viTri,nguoiChoi,tatCaHinhPhat);
            }
        });
        btnThachThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.cauHoitBatKi(tatCaCauHoiDare,tenNguoiChoi,viTri,nguoiChoi,tatCaHinhPhat);
            }
        });
    }
}