package fpoly.thanhntph47592.truthordarecustom.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;

public class FoundersScreen extends AppCompatActivity {

    private BasicFeatures basicFeatures;
    ImageView btnBack;
    TextView tvContent, btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founders_screen);

        basicFeatures=new BasicFeatures(FoundersScreen.this);

        btnBack=findViewById(R.id.foundersScreen_btnBack);
        tvContent=findViewById(R.id.foundersScreen_tvContent);
        btnFacebook=findViewById(R.id.foundersScreen_btnFacebook);

        String content ="Ứng dụng được phát triển bởi Nguyễn Trung Thành - " +
                "sinh viên chuyên ngành lập trình Mobile tại trường Cao đẳng FPT Polytechnic.";
        tvContent.setText(content);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.nextScreen(HomeScreen.class);
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.openFacebook("https://www.facebook.com/profile.php?id=100027720627238");
            }
        });
    }
}