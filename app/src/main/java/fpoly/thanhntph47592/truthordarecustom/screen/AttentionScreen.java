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
    TextView tvAttention;
    Button btnGotIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_screen);

        basicFeatures=new BasicFeatures(AttentionScreen.this);

        tvAttention =findViewById(R.id.attentionScreen_tvAttention);
        btnGotIt =findViewById(R.id.attentionScreen_btnGotIt);

        String attention ="Ứng dụng này được tạo ra để phục vụ mục đích giải trí lành mạnh. " +
                "Chúng tôi không khuyến khích bất kỳ hành vi nào làm tổn hại sức khỏe, " +
                "tinh thần người khác; không phù hợp với thuần phong mỹ tục hoặc " +
                "hành vi vi phạm pháp luật Việt Nam.\n\n" +
                "Chúc các bạn có những trải nghiệm vui vẻ và ý nghĩa cùng ứng dụng!";
        tvAttention.setText(attention);
        tvAttention.setMovementMethod(new ScrollingMovementMethod());

        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(HomeScreen.class);
            }
        });
    }
}