package fpoly.thanhntph47592.truthordarecustom.screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;

public class GamePlayScreen_3 extends AppCompatActivity {

    private GamePlayFeatures gamePlayFeatures;
    private TextView tvTenNguoiChoi, tvNoiDungCauHoi;
    private Button btnHoanThanh, btnTrungCauHoi, btnChiuThua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen3);

        gamePlayFeatures=new GamePlayFeatures(GamePlayScreen_3.this);

        tvTenNguoiChoi=findViewById(R.id.gamePlayScreen_3_tvTenNguoiChoi);
        tvNoiDungCauHoi=findViewById(R.id.gamePlayScreen_3_tvNoiDungCauHoi);
        btnHoanThanh=findViewById(R.id.gamePlayScreen_3_btnHoanThanh);
        btnTrungCauHoi=findViewById(R.id.gamePlayScreen_3_btnTrungCauHoi);
        btnChiuThua=findViewById(R.id.gamePlayScreen_3_btnChiuThua);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tvTenNguoiChoi.setText(bundle.getString("NguoiChoiDuocChon"));
        tvNoiDungCauHoi.setText(bundle.getString("CauHoiDuocChon"));
        tvNoiDungCauHoi.setMovementMethod(new ScrollingMovementMethod());

        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.batDauChoi(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"), GamePlayScreen_1.class);
            }
        });
        btnTrungCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChiuThua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.thucHienHinhPhat(bundle.getInt("ViTri"),
                        bundle.getStringArrayList("NguoiChoi"),
                        bundle.getStringArrayList("HinhPhat"));
            }
        });
    }
}