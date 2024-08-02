package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.NguoiChoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.adapter.SpinnerAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.CauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;
import fpoly.thanhntph47592.truthordarecustom.screen.GamePlayScreen_3;
import fpoly.thanhntph47592.truthordarecustom.screen.GamePlayScreen_4;
import fpoly.thanhntph47592.truthordarecustom.screen.HomeScreen;

public class GamePlayFeatures {

    private Context context;
    private BoCauHoiDAO boCauHoiDAO;
    private CauHoiDAO cauHoiDAO;

    public GamePlayFeatures(Context context) {
        this.context = context;
        boCauHoiDAO=new BoCauHoiDAO(context);
        cauHoiDAO=new CauHoiDAO(context);
    }

    public void themNguoiChoi(EditText edTenNguoiChoi, ArrayList<String> arrayList, NguoiChoiAdapter adapter){
        String tenNguoiChoi=edTenNguoiChoi.getText().toString();
        if (tenNguoiChoi.isEmpty()){
            Toast.makeText(context, "Người chơi không hợp lệ", Toast.LENGTH_SHORT).show();
        }else {
            String tenDaCo="";
            for (String ten:arrayList){
                if (ten.equals(tenNguoiChoi)){
                    tenDaCo=ten;
                    break;
                }
            }
            if (tenDaCo.equals("")){
                arrayList.add(tenNguoiChoi);
                edTenNguoiChoi.setText("");
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "Thêm người chơi thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Bạn cần đặt tên khác nhau để tránh nhầm lẫn", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void xoaNguoiChoi(int viTri,ArrayList<String> arrayList,NguoiChoiAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn muốn xóa người chơi này?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(viTri);
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void suaNguoiChoi(int viTri,String tenNguoiChoi,ArrayList<String> arrayList,NguoiChoiAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.bocauhoi_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        TextView tvTieuDe=view.findViewById(R.id.boCauHoi_tvTieuDe);
        EditText edTenNguoiChoi=view.findViewById(R.id.boCauHoi_edNoiDung);
        Button btnLuu=view.findViewById(R.id.boCauHoi_btnDongY);
        Button btnHuy=view.findViewById(R.id.boCauHoi_btnTuChoi);
        tvTieuDe.setText("Thay đổi người chơi");
        btnLuu.setText("Lưu");
        edTenNguoiChoi.setHint("Nhập tên người chơi mới");
        edTenNguoiChoi.setText(tenNguoiChoi);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenNguoiChoiMoi=edTenNguoiChoi.getText().toString();
                if (tenNguoiChoiMoi.isEmpty()){
                    Toast.makeText(context, "Người chơi không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    String tenDaCo="";
                    for (String ten:arrayList){
                        if (ten.equals(tenNguoiChoiMoi)){
                            tenDaCo=ten;
                            break;
                        }
                    }
                    if (tenDaCo.equals("")){
                        arrayList.set(viTri,tenNguoiChoiMoi);
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Thay đổi người chơi thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Bạn cần đặt tên khác nhau để tránh nhầm lẫn", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void thoatTroChoi(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Thoát trò chơi");
        builder.setMessage("Ván chơi sẽ được tính là kết thúc. Bạn xác nhận thoát?");
        builder.setNegativeButton("Ở lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new BasicFeatures(context).chuyenMan(HomeScreen.class);
            }
        });
        builder.show();
    }

    public void batDauChoi(int viTri,ArrayList<String> nguoiChoi,Class mClass){
        Intent intent=new Intent(context, mClass);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri",viTri);
        bundle.putStringArrayList("NguoiChoi",nguoiChoi);
        intent.putExtras(bundle);
        if (nguoiChoi!=null && nguoiChoi.size()<2){
            Toast.makeText(context, "Yêu cầu tối thiểu 2 người chơi", Toast.LENGTH_SHORT).show();
        }else {
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    public ArrayList<CauHoi> danhSachCauHoiDuocChon(int viTri){
        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        int maBoCauHoi=boCauHoiArrayList.get(viTri).getMaBoCauHoi();
        return cauHoiDAO.cauHoiTheoBo(maBoCauHoi);
    }

    public ArrayList<String> tatCaCauHoiTruth(ArrayList<CauHoi> cauHoiArrayList, Button button){
        ArrayList<String> danhSachCauHoiTruth=new ArrayList<>();
        for (CauHoi cauHoi:cauHoiArrayList){
            if (cauHoi.getPhanLoai()==0){
                danhSachCauHoiTruth.add(cauHoi.getNoiDung());
            }
        }
        if (danhSachCauHoiTruth.isEmpty()){
            button.setEnabled(false);
        }
        return danhSachCauHoiTruth;
    }

    public ArrayList<String> tatCaCauHoiDare(ArrayList<CauHoi> cauHoiArrayList, Button button){
        ArrayList<String> danhSachCauHoiDare =new ArrayList<>();
        for (CauHoi cauHoi:cauHoiArrayList){
            if (cauHoi.getPhanLoai()==1){
                danhSachCauHoiDare.add(cauHoi.getNoiDung());
            }
        }
        if (danhSachCauHoiDare.isEmpty()){
            button.setEnabled(false);
        }
        return danhSachCauHoiDare;
    }

    public ArrayList<String> tatCaHinhPhat(ArrayList<CauHoi> cauHoiArrayList){
        ArrayList<String> danhSachHinhPhat =new ArrayList<>();
        for (CauHoi cauHoi:cauHoiArrayList){
            if (cauHoi.getPhanLoai()==2){
                danhSachHinhPhat.add(cauHoi.getNoiDung());
            }
        }
        return danhSachHinhPhat;
    }

    public void cauHoitBatKi(ArrayList<String> cauHoiArrayList, String tenNguoiChoi,
                             int viTri, ArrayList<String> nguoiChoi,ArrayList<String> hinhPhat){
        int viTriCauHoi = new Random().nextInt(cauHoiArrayList.size());
        Intent intent=new Intent(context, GamePlayScreen_3.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViTri",viTri);
        bundle.putStringArrayList("NguoiChoi",nguoiChoi);
        bundle.putString("NguoiChoiDuocChon",tenNguoiChoi);
        bundle.putString("CauHoiDuocChon", cauHoiArrayList.get(viTriCauHoi));
        bundle.putStringArrayList("HinhPhat",hinhPhat);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void caiDatSpinnerHinhPhat(Spinner spinner,ArrayList<String> arrayList){
        SpinnerAdapter adapter=new SpinnerAdapter(context,arrayList);
        spinner.setAdapter(adapter);
    }

    public void thucHienHinhPhat(int viTri, ArrayList<String> nguoiChoi, ArrayList<String> hinhPhat){
        Intent intent=new Intent(context, GamePlayScreen_4.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("HinhPhat",hinhPhat);
        bundle.putInt("ViTri",viTri);
        bundle.putStringArrayList("NguoiChoi",nguoiChoi);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
