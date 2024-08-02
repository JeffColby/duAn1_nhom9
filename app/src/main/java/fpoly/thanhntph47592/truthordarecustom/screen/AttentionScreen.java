package fpoly.thanhntph47592.truthordarecustom.screen;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class AttentionScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private TextView tvLuuY;
    private Button btnDaHieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_screen);
        basicFeatures=new BasicFeatures(AttentionScreen.this);
        tvLuuY=findViewById(R.id.attentionScreen_tvLuuY);
        btnDaHieu=findViewById(R.id.attentionScreen_btnDaHieu);

        String luuY="Ứng dụng này được tạo ra để phục vụ mục đích giải trí lành mạnh. " +
                "Chúng tôi không khuyến khích bất kỳ hành vi nào làm tổn hại sức khỏe, " +
                "tinh thần người khác; không phù hợp với thuần phong mỹ tục hoặc " +
                "hành vi vi phạm pháp luật Việt Nam.\n\n" +
                "Chúc các bạn có những trải nghiệm vui vẻ và ý nghĩa cùng ứng dụng!";
        tvLuuY.setText(luuY);
        tvLuuY.setMovementMethod(new ScrollingMovementMethod());

        btnDaHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(HomeScreen.class);
            }
        });
    }
}