package fpoly.thanhntph47592.truthordarecustom.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.dao.CauHoiDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.BoCauHoi;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;

public class BoCauHoiAdapter extends RecyclerView.Adapter<BoCauHoiAdapter.BoCauHoiHolder>{

    private Context context;
    private ArrayList<BoCauHoi> arrayList;
    private BasicFeatures basicFeatures;
    private QuestionGroupsFeatures questionGroupsFeatures;
    private CauHoiDAO cauHoiDAO;

    public BoCauHoiAdapter(Context context, ArrayList<BoCauHoi> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        basicFeatures=new BasicFeatures(context);
        questionGroupsFeatures =new QuestionGroupsFeatures(context);
        cauHoiDAO=new CauHoiDAO(context);
    }

    public void setArrayList(ArrayList<BoCauHoi> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BoCauHoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bocauhoi_item,parent,false);
        return new BoCauHoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoCauHoiHolder holder, int position) {
        BoCauHoi boCauHoi=arrayList.get(position);
        ArrayList<CauHoi> cauHoiArrayList=cauHoiDAO.cauHoiTheoBo(boCauHoi.getMaBoCauHoi());

        holder.tvTenBoCauHoi.setText(boCauHoi.getTenBoCauHoi());
        holder.tvTenBoCauHoi.setMovementMethod(new ScrollingMovementMethod());
        holder.tvSoCauHoi.setText("Số câu hỏi: "+(cauHoiArrayList.size()+""));
        basicFeatures.catDatChuGachChan(holder.btnChiTiet,"Xem chi tiết");

        holder.btnAnHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.anHien(holder.btnAnHien, holder.layoutMoRong);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.anHien(holder.btnAnHien, holder.layoutMoRong);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.xoaBoCauHoi(cauHoiArrayList.size(),boCauHoi,arrayList,BoCauHoiAdapter.this);
            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.suaBoCauHoi(boCauHoi,BoCauHoiAdapter.this);
            }
        });
        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.xemChiTietDanhSachCauHoi(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class BoCauHoiHolder extends RecyclerView.ViewHolder {

        private TextView tvTenBoCauHoi, btnChiTiet, tvSoCauHoi;
        private ImageView btnAnHien, btnXoa, btnSua;
        private RelativeLayout layoutMoRong;

        public BoCauHoiHolder(@NonNull View itemView) {
            super(itemView);
            tvTenBoCauHoi=itemView.findViewById(R.id.boCauHoi_tvTenBoCauHoi);
            btnAnHien=itemView.findViewById(R.id.boCauHoi_btnAnHien);
            layoutMoRong=itemView.findViewById(R.id.boCauHoi_layoutMoRong);
            btnXoa=itemView.findViewById(R.id.boCauHoi_btnXoa);
            btnSua=itemView.findViewById(R.id.boCauHoi_btnSua);
            btnChiTiet=itemView.findViewById(R.id.boCauHoi_btnChiTiet);
            tvSoCauHoi=itemView.findViewById(R.id.boCauHoi_tvSoCauHoi);
        }
    }
}
