package fpoly.thanhntph47592.truthordarecustom.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.adapter.CauHoiAdapter;
import fpoly.thanhntph47592.truthordarecustom.adapter.SpinnerAdapter;
import fpoly.thanhntph47592.truthordarecustom.dao.BoCauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.dao.CauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;
import fpoly.thanhntph47592.truthordarecustom.screen.SetupScreen_2;

public class QuestionsFeatures {

    private Context context;
    private BoCauHoiDAO boCauHoiDAO;
    private CauHoiDAO cauHoiDAO;

    public QuestionsFeatures(Context context) {
        this.context = context;
        boCauHoiDAO=new BoCauHoiDAO(context);
        cauHoiDAO=new CauHoiDAO(context);
    }

    public void caiDatSpinnerBoCauHoi(Spinner spinner){
        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        ArrayList<String> fullBoCauHoi=new ArrayList<>();
        for (BoCauHoi boCauHoi:boCauHoiArrayList){
            fullBoCauHoi.add(boCauHoi.getTenBoCauHoi());
        }
        SpinnerAdapter adapter=new SpinnerAdapter(context,fullBoCauHoi);
        spinner.setAdapter(adapter);
    }

    public void caiDatSpinnerPhanLoai(Spinner spinner){
        ArrayList<String> phanLoai=new ArrayList<>();
        phanLoai.add("Sự thật");
        phanLoai.add("Thách thức");
        phanLoai.add("Hình phạt");
        SpinnerAdapter adapter=new SpinnerAdapter(context,phanLoai);
        spinner.setAdapter(adapter);
    }

    public void locCauHoiTheoBo(int viTri, CauHoiAdapter cauHoiAdapter, RecyclerView rcCauHoi){
        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        int maBoCauHoi=boCauHoiArrayList.get(viTri).getMaBoCauHoi();
        ArrayList<CauHoi> cauHoiArrayList=cauHoiDAO.cauHoiTheoBo(maBoCauHoi);

        cauHoiAdapter.setArrayList(cauHoiArrayList);
        rcCauHoi.setAdapter(cauHoiAdapter);
    }

    public void themCauHoi(int viTri,CauHoiAdapter adapter,RecyclerView rcCauHoi){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.cauhoi_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        EditText edNoiDung=view.findViewById(R.id.cauHoi_edNoiDung);
        Button btnThem=view.findViewById(R.id.cauHoi_btnDongY);
        Button btnHuy=view.findViewById(R.id.cauHoi_btnTuChoi);
        Spinner spBoCauHoi=view.findViewById(R.id.cauHoiDialog_spBoCauHoi);
        Spinner spPhanLoai=view.findViewById(R.id.cauHoiDialog_spPhanLoai);
        caiDatSpinnerPhanLoai(spPhanLoai);
        caiDatSpinnerBoCauHoi(spBoCauHoi);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiDung=edNoiDung.getText().toString();
                if (noiDung.isEmpty()){
                    Toast.makeText(context, "Nội dung không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
                    int maBoCauHoi=boCauHoiArrayList.get(spBoCauHoi.getSelectedItemPosition()).getMaBoCauHoi();
                    CauHoi cauHoi=new CauHoi(noiDung,spPhanLoai.getSelectedItemPosition(),maBoCauHoi);
                    boolean check=cauHoiDAO.themCauHoi(cauHoi);
                    if (check){
                        Toast.makeText(context, "Thêm câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        if (viTri==spBoCauHoi.getSelectedItemPosition()){
                            adapter.setArrayList(cauHoiDAO.cauHoiTheoBo(maBoCauHoi));
                            rcCauHoi.setAdapter(adapter);
                        }
                    }
                }
            }
        });
    }

    public void locCauHoiTheoPhanLoai(int viTri,int phanLoai,ArrayList<CauHoi> arrayList,
                                      CauHoiAdapter adapter,RecyclerView recyclerView){
        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        int maBoCauHoi=boCauHoiArrayList.get(viTri).getMaBoCauHoi();
        ArrayList<CauHoi> cauHoiArrayList=cauHoiDAO.cauHoiTheoBo(maBoCauHoi);

        if (arrayList.size() == cauHoiArrayList.size()){
            ArrayList<CauHoi> arrayList1=new ArrayList<>();
            for (CauHoi cauHoi:cauHoiArrayList){
                if (phanLoai==cauHoi.getPhanLoai()){
                    arrayList1.add(cauHoi);
                }
            }
            adapter.setArrayList(arrayList1);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setArrayList(cauHoiArrayList);
            recyclerView.setAdapter(adapter);
        }
    }

    public void xoaCauHoi(CauHoi cauHoi,ArrayList<CauHoi> arrayList,CauHoiAdapter adapter){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn muốn xóa câu hỏi này?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check= cauHoiDAO.xoaCauHoi(cauHoi);
                if (check){
                    Toast.makeText(context, "Xóa câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    arrayList.remove(cauHoi);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.show();
    }

    public void suaCauHoi(CauHoi cauHoi, CauHoiAdapter adapter, TextView tvPhanLoai,
                          RelativeLayout layoutPhanLoai,ArrayList<CauHoi> cauHoiArrayList){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.cauhoi_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();

        TextView tvTieuDe=view.findViewById(R.id.cauHoi_tvTieuDe);
        EditText edNoiDung=view.findViewById(R.id.cauHoi_edNoiDung);
        Button btnLuu =view.findViewById(R.id.cauHoi_btnDongY);
        Button btnHuy=view.findViewById(R.id.cauHoi_btnTuChoi);
        Spinner spBoCauHoi=view.findViewById(R.id.cauHoiDialog_spBoCauHoi);
        Spinner spPhanLoai=view.findViewById(R.id.cauHoiDialog_spPhanLoai);
        caiDatSpinnerPhanLoai(spPhanLoai);
        caiDatSpinnerBoCauHoi(spBoCauHoi);
        btnLuu.setText("Lưu");
        tvTieuDe.setText("Sửa câu hỏi");
        edNoiDung.setText(cauHoi.getNoiDung());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int boCauHoiCu=cauHoi.getBoCauHoi();
                int phanLoaiCu=cauHoi.getPhanLoai();
                String noiDungMoi=edNoiDung.getText().toString();
                if (noiDungMoi.isEmpty()){
                    Toast.makeText(context, "Nội dung không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
                    int boCauHoiMoi=boCauHoiArrayList.get(spBoCauHoi.getSelectedItemPosition()).getMaBoCauHoi();
                    int phanLoaiMoi=spPhanLoai.getSelectedItemPosition();
                    cauHoi.setNoiDung(noiDungMoi);
                    cauHoi.setPhanLoai(phanLoaiMoi);
                    cauHoi.setBoCauHoi(boCauHoiMoi);
                    boolean check=cauHoiDAO.suaCauHoi(cauHoi);
                    if (check){
                        if (boCauHoiCu==boCauHoiMoi){
                            if (phanLoaiCu!=phanLoaiMoi){
                                new BasicFeatures(context).caiDatMauSac(phanLoaiMoi,tvPhanLoai,layoutPhanLoai);
                            }
                        }else {
                            cauHoiArrayList.remove(cauHoi);
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Sửa câu hỏi thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
    }

    public void kiemTraSoCauHoi(int viTri){
        ArrayList<BoCauHoi> boCauHoiArrayList=boCauHoiDAO.tatCaBoCauHoi();
        int maBoCauHoi=boCauHoiArrayList.get(viTri).getMaBoCauHoi();
        ArrayList<CauHoi> cauHoiArrayList=cauHoiDAO.cauHoiTheoBo(maBoCauHoi);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        if (cauHoiArrayList.isEmpty()){
            builder.setTitle("Bộ câu hỏi trống");
            builder.setMessage("Bạn cần thêm câu hỏi hoặc chọn bộ câu hỏi khác để bắt đầu");
            builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    new QuestionGroupsFeatures(context).xemChiTietDanhSachCauHoi(viTri);
                }
            });
            builder.setNegativeButton("Chọn bộ khác", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }else {
            new GamePlayFeatures(context).batDauChoi(viTri,null, SetupScreen_2.class);
        }
    }

}
