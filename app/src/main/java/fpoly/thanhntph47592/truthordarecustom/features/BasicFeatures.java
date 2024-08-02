package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.screen.FoundersScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.HistoryScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.HomeScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionGroupsScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionsScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.RulesScreen;

public class BasicFeatures {

    private Context context;

    public BasicFeatures(Context context) {
        this.context=context;
    }

    public void chuyenMan(Class mClass){
        Intent intent=new Intent(context,mClass);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public PopupMenu caiDatMenu(ImageView imageView){
        PopupMenu popupMenu=new PopupMenu(context,imageView);
        popupMenu.getMenuInflater().inflate(R.menu.all_features_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.menu_manHinhChinh){
                    if (!context.equals(HomeScreen.getContext())){
                        chuyenMan(HomeScreen.class);
                    }
                } else if (item.getItemId()==R.id.menu_timHieuLuatChoi) {
                    chuyenMan(RulesScreen.class);
                } else if (item.getItemId()==R.id.menu_thoat) {
                    xacNhanThoatApp();
                } else if (item.getItemId()==R.id.menu_thongTinNhaSangLap) {
                    chuyenMan(FoundersScreen.class);
                } else if (item.getItemId()==R.id.menu_danhSachBoCauHoi) {
                    if (!context.equals(QuestionGroupsScreen.getContext())){
                        chuyenMan(QuestionGroupsScreen.class);
                    }
                } else if (item.getItemId()==R.id.menu_danhSachCauHoi) {
                    if (!context.equals(QuestionsScreen.getContext())){
                        chuyenMan(QuestionsScreen.class);
                    }
                }else if (item.getItemId()==R.id.menu_lichSuChoi){
                    if (!context.equals(HistoryScreen.getContext())) {
                        chuyenMan(HistoryScreen.class);
                    }
                }
                return true;
            }
        });

        return popupMenu;
    }

    public void xacNhanThoatApp(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn muốn thoát ứng dụng?");
        builder.setNegativeButton("Ở lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)context).finish();
            }
        });
        builder.show();
    }

    public void moFacebook(String linkFacebook){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(linkFacebook));
        context.startActivity(intent);
    }

    public void anHien(ImageView btnAnHien, RelativeLayout layoutMoRong){
        if (layoutMoRong.getVisibility()== View.GONE){
            btnAnHien.setImageResource(R.drawable.triangle_down_svgrepo_com);
            layoutMoRong.setVisibility(View.VISIBLE);
        }else {
            btnAnHien.setImageResource(R.drawable.triangle_up_svgrepo_com);
            layoutMoRong.setVisibility(View.GONE);
        }
    }

    public void caiDatMauSac(int phanLoai, TextView tvPhanLoai, RelativeLayout layoutPhanLoai){
        if (phanLoai==0){
            tvPhanLoai.setText("Sự thật");
            layoutPhanLoai.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF66C4")));
        } else if (phanLoai==1) {
            tvPhanLoai.setText("Thách thức");
            layoutPhanLoai.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5CE1E6")));
        } else if (phanLoai==2) {
            tvPhanLoai.setText("Hình phạt");
            layoutPhanLoai.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0066FF")));
        }
    }

    public void caiDatRecycleView(RecyclerView recyclerView){
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    public void catDatChuGachChan(TextView textView, String noiDung){
        SpannableString spannableString = new SpannableString(noiDung);
        spannableString.setSpan(new UnderlineSpan(), 0, noiDung.length(), 0);
        textView.setText(spannableString);
    }
}
