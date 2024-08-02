package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.BoCauHoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;
import fpoly.thanhntph47592.truthordarecustom.screen.HomeScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionGroupsScreen;
import fpoly.thanhntph47592.truthordarecustom.screen.QuestionsScreen;

public class QuestionGroupsFeatures {

    private Context context;
    private BoCauHoiDAO boCauHoiDAO;

    public QuestionGroupsFeatures(Context context) {
        this.context = context;
        boCauHoiDAO=new BoCauHoiDAO(context);
    }

    public void themBoCauHoi(BoCauHoiAdapter adapter){
        Toast.makeText(context, "Bạn nên đặt tên bộ câu hỏi khác nhau để tránh nhầm lẫn", Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.bocauhoi_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edBoCauHoi=view.findViewById(R.id.boCauHoi_edNoiDung);
        Button btnThem=view.findViewById(R.id.boCauHoi_btnDongY);
        Button btnHuy=view.findViewById(R.id.boCauHoi_btnTuChoi);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenBoCauHoi =edBoCauHoi.getText().toString();
                if (tenBoCauHoi.isEmpty()){
                    Toast.makeText(context, "Bộ câu hỏi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    BoCauHoi boCauHoi=new BoCauHoi(tenBoCauHoi);
                    boolean check=boCauHoiDAO.themBoCauHoi(boCauHoi);
                    if (check){
                        Toast.makeText(context, "Thêm bộ câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        adapter.setArrayList(boCauHoiDAO.tatCaBoCauHoi());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void xoaBoCauHoi(int soCauHoi, BoCauHoi boCauHoi, ArrayList<BoCauHoi> arrayList,
                            BoCauHoiAdapter adapter){
        if (soCauHoi>0){
            Toast.makeText(context, "Không thể xóa bộ câu hỏi khi vẫn còn câu hỏi bên trong",
                    Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setMessage("Bạn muốn xóa bộ câu hỏi này?");
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean check=boCauHoiDAO.xoaBoCauHoi(boCauHoi);
                    if (check){
                        Toast.makeText(context, "Xóa bộ câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        arrayList.remove(boCauHoi);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        }
    }

    public void suaBoCauHoi(BoCauHoi boCauHoi, BoCauHoiAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.bocauhoi_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edBoCauHoi=view.findViewById(R.id.boCauHoi_edNoiDung);
        Button btnLuu=view.findViewById(R.id.boCauHoi_btnDongY);
        Button btnHuy=view.findViewById(R.id.boCauHoi_btnTuChoi);
        TextView tvTieuDe=view.findViewById(R.id.boCauHoi_tvTieuDe);

        tvTieuDe.setText("Sửa bộ câu hỏi");
        btnLuu.setText("Lưu");
        edBoCauHoi.setText(boCauHoi.getTenBoCauHoi());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boCauHoiMoi =edBoCauHoi.getText().toString();
                if (boCauHoiMoi.isEmpty()){
                    Toast.makeText(context, "Bộ câu hỏi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    boCauHoi.setTenBoCauHoi(boCauHoiMoi);
                    boolean check=boCauHoiDAO.suaBoCauHoi(boCauHoi);
                    if (check){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void xemChiTietDanhSachCauHoi(int viTri){
        Intent intent=new Intent(context, QuestionsScreen.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri",viTri);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public void kiemTraBoCauHoi(){
        BasicFeatures basicFeatures=new BasicFeatures(context);
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Chưa có bộ câu hỏi");
        builder.setMessage("Bạn cần phải thêm bộ câu hỏi trước");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                basicFeatures.chuyenMan(QuestionGroupsScreen.class);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                basicFeatures.chuyenMan(HomeScreen.class);
            }
        });
        builder.show();
    }
}
