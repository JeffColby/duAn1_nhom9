package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.LichSuAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.CauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.LichSuDAO;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.LichSu;

public class HistoryFeatures {

    private Context context;
    private LichSuDAO lichSuDAO;
    private BoCauHoiDAO boCauHoiDAO;
    private CauHoiDAO cauHoiDAO;

    public HistoryFeatures(Context context) {
        this.context=context;
        lichSuDAO =new LichSuDAO(context);
        boCauHoiDAO=new BoCauHoiDAO(context);
        cauHoiDAO=new CauHoiDAO(context);
    }

    public void themVaoLichSu(ArrayList<String> nguoiChoi,int viTri){
        LocalDateTime time=LocalDateTime.now();
        String hour=time.getHour()+":";
        String minutes=time.getMinute()+" ";
        String day=time.getDayOfMonth()+"/";
        String month=time.getMonthValue()+"/";
        String year=time.getYear()+"";
        String ngayGio=hour+minutes+day+month+year;

        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        int maBoCauHoi=boCauHoiArrayList.get(viTri).getMaBoCauHoi();
        ArrayList<CauHoi> cauHoiArrayList=cauHoiDAO.cauHoiTheoBo(maBoCauHoi);

        lichSuDAO.themVanChoi(ngayGio,nguoiChoi,cauHoiArrayList);
    }

    public void xemLichSu(LichSu vanChoi){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.lichsu_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        ImageView btnThoat=view.findViewById(R.id.lichSu_btnThoat);
        TextView tvNgayGio=view.findViewById(R.id.lichSu_tvNgayGio);
        TextView tvSoNguoiChoi=view.findViewById(R.id.lichSu_tvSoNguoiChoi);
        TextView tvTenNguoiChoi=view.findViewById(R.id.lichSu_tvTenNguoiChoi);
        TextView tvCauHoi=view.findViewById(R.id.lichSu_tvCauHoi);

        tvNgayGio.setText("Ngày giờ: "+vanChoi.getNgayGio());
        tvSoNguoiChoi.setText("Số người chơi: "+(vanChoi.getSoNguoiChoi()));
        tvTenNguoiChoi.setText(vanChoi.getTenNguoiChoi());
        tvCauHoi.setText(vanChoi.getCauHoi());

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void xoaLichSu(LichSu lichSu, ArrayList<LichSu> arrayList, LichSuAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa lịch sử chơi");
        builder.setMessage("Tất cả thông tin của ván chơi này sẽ bị xóa vĩnh viễn. Xác nhận tiếp tục xóa?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check=lichSuDAO.xoaVanChoi(lichSu);
                if (check){
                    arrayList.remove(lichSu);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Xóa lịch sử thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
