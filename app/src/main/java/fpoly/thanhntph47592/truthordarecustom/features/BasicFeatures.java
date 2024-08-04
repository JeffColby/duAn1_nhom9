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

    public void nextScreen(Class mClass){
        Intent intent=new Intent(context,mClass);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public PopupMenu menuSetUp(ImageView imageView){
        PopupMenu popupMenu=new PopupMenu(context,imageView);
        popupMenu.getMenuInflater().inflate(R.menu.all_features_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.menu_homeScreen){
                    if (!context.equals(HomeScreen.getContext())){
                        nextScreen(HomeScreen.class);
                    }
                } else if (item.getItemId()==R.id.menu_ruleScreen) {
                    nextScreen(RulesScreen.class);
                } else if (item.getItemId()==R.id.menu_exit) {
                    exitConfirm();
                } else if (item.getItemId()==R.id.menu_founderScreen) {
                    nextScreen(FoundersScreen.class);
                } else if (item.getItemId()==R.id.menu_questionGroupScreen) {
                    if (!context.equals(QuestionGroupsScreen.getContext())){
                        nextScreen(QuestionGroupsScreen.class);
                    }
                } else if (item.getItemId()==R.id.menu_questionScreen) {
                    if (!context.equals(QuestionsScreen.getContext())){
                        nextScreen(QuestionsScreen.class);
                    }
                }else if (item.getItemId()==R.id.menu_GamePlayDataScreen){
                    if (!context.equals(HistoryScreen.getContext())) {
                        nextScreen(HistoryScreen.class);
                    }
                }
                return true;
            }
        });

        return popupMenu;
    }

    public void exitConfirm(){
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

    public void openFacebook(String facebookURL){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(facebookURL));
        context.startActivity(intent);
    }

    public void hideAndShow(ImageView btnHideAndShow, RelativeLayout extendLayout){
        if (extendLayout.getVisibility()== View.GONE){
            btnHideAndShow.setImageResource(R.drawable.triangle_down_svgrepo_com);
            extendLayout.setVisibility(View.VISIBLE);
        }else {
            btnHideAndShow.setImageResource(R.drawable.triangle_up_svgrepo_com);
            extendLayout.setVisibility(View.GONE);
        }
    }

    public void colorSetUp(int type, TextView tvType, RelativeLayout typeLayout){
        if (type ==0){
            tvType.setText("Sự thật");
            typeLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF66C4")));
        } else if (type ==1) {
            tvType.setText("Thách thức");
            typeLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5CE1E6")));
        } else if (type ==2) {
            tvType.setText("Hình phạt");
            typeLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0066FF")));
        }
    }

    public void recycleViewSetUp(RecyclerView recyclerView){
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    public void textUnderlineSetUp(TextView textView, String content){
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(spannableString);
    }
}
