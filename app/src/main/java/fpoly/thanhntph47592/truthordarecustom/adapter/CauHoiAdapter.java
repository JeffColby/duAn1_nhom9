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
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionsFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.CauHoi;

public class CauHoiAdapter extends RecyclerView.Adapter<CauHoiAdapter.CauHoiHolder> {

    private Context context;
    private ArrayList<CauHoi> arrayList;
    private BasicFeatures basicFeatures;
    private QuestionsFeatures questionsFeatures;

    public CauHoiAdapter(Context context, ArrayList<CauHoi> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        basicFeatures=new BasicFeatures(context);
        questionsFeatures=new QuestionsFeatures(context);
    }

    public ArrayList<CauHoi> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<CauHoi> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CauHoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cauhoi_item,parent,false);
        return new CauHoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauHoiHolder holder, int position) {
        CauHoi cauHoi=arrayList.get(position);

        basicFeatures.caiDatMauSac(cauHoi.getPhanLoai(),holder.tvPhanLoai,holder.layoutPhanLoai);
        holder.tvNoiDung.setText(cauHoi.getNoiDung());
        holder.tvNoiDung.setMovementMethod(new ScrollingMovementMethod());

        holder.btnAnHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.anHien(holder.btnAnHien,holder.layoutMoRong);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicFeatures.anHien(holder.btnAnHien,holder.layoutMoRong);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionsFeatures.xoaCauHoi(cauHoi,arrayList,CauHoiAdapter.this);
            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionsFeatures.suaCauHoi(cauHoi,CauHoiAdapter.this,
                        holder.tvPhanLoai,holder.layoutPhanLoai,arrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CauHoiHolder extends RecyclerView.ViewHolder {

        private TextView tvPhanLoai,tvNoiDung;
        private ImageView btnAnHien,btnXoa,btnSua;
        private RelativeLayout layoutMoRong,layoutPhanLoai;

        public CauHoiHolder(@NonNull View itemView) {
            super(itemView);
            tvPhanLoai=itemView.findViewById(R.id.cauHoi_tvPhanLoai);
            tvNoiDung=itemView.findViewById(R.id.cauHoi_tvNoiDung);
            btnAnHien=itemView.findViewById(R.id.cauHoi_btnAnHien);
            btnXoa=itemView.findViewById(R.id.cauHoi_btnXoa);
            btnSua=itemView.findViewById(R.id.cauHoi_btnSua);
            layoutMoRong=itemView.findViewById(R.id.cauHoi_layoutMoRong);
            layoutPhanLoai=itemView.findViewById(R.id.cauHoi_layoutPhanLoai);
        }
    }
}
