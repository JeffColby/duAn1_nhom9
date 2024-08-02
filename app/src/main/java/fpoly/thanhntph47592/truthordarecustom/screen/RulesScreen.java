package fpoly.thanhntph47592.truthordarecustom.screen;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class RulesScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    private TextView tvNoiDung;
    private ImageView btnTroVe;
    private Button btnBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_screen);

        basicFeatures=new BasicFeatures(RulesScreen.this);

        tvNoiDung=findViewById(R.id.rulesScreen_tvNoiDung);
        btnTroVe=findViewById(R.id.rulesScreen_btnTroVe);
        btnBatDau=findViewById(R.id.rulesScreen_btnBatDau);

        String noiDung="Bước 1. Chọn bộ câu hỏi đã thêm ở danh sách\n\n" +
                "Bước 2. Thêm người chơi (yêu cầu tối thiểu 2 người)\n\n" +
                "Bước 3. Bắt đầu lượt chơi. Chọn ngẫu nhiên 1 người chơi để trả lời câu hỏi\n\n" +
                "Bước 4. Sẽ có 2 lựa chọn cho người chơi: Sự thật hoặc Thách thức \n\n" +
                "Bước 5. Khi người chơi bấm chọn sẽ hiển thị câu hỏi ngẫu nhiên tương ứng\n\n" +
                "Bước 6. Sẽ có 3 tùy chọn cho người chơi: Hoàn thành, Trùng câu hỏi và Chịu thua\n\n" +
                "Bước 7. Khi bấm Hoàn thành sẽ quay lại bước 3; bấm Trùng câu hỏi sẽ quay lại bước 5; " +
                "bấm Chịu thua sẽ hiển thị danh sách những hình phạt đã thêm, sau khi hoàn thành sẽ quay lại bước 3\n" +
                "\n\nChúng tôi không khuyến khích bất kỳ hành vi nào làm tổn hại sức khỏe, " +
                "tinh thần người khác; không phù hợp với thuần phong mỹ tục hoặc " +
                "hành vi vi phạm pháp luật Việt Nam. " +
                "Chúc các bạn có những giây phút vui vẻ và ý nghĩa cùng ứng dụng!";
        tvNoiDung.setText(noiDung);
        tvNoiDung.setMovementMethod(new ScrollingMovementMethod());

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(HomeScreen.class);
            }
        });
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.chuyenMan(SetupScreen_1.class);
            }
        });
    }
}