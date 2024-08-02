package fpoly.thanhntph47592.truthordarecustom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.HistoryFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.LichSu;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuHolder>{

    private Context context;
    private ArrayList<LichSu> arrayList;
    private HistoryFeatures historyFeatures;

    public LichSuAdapter(Context context, ArrayList<LichSu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        historyFeatures=new HistoryFeatures(context);
    }

    @NonNull
    @Override
    public LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bocauhoi_item,parent,false);
        return new LichSuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuHolder holder, int position) {
        LichSu lichSu =arrayList.get(position);

        holder.btnAnHien.setVisibility(View.GONE);
        holder.tvNgayGio.setText(lichSu.getNgayGio());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyFeatures.xemLichSu(lichSu);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                historyFeatures.xoaLichSu(lichSu,arrayList,LichSuAdapter.this);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class LichSuHolder extends RecyclerView.ViewHolder {

        private TextView tvNgayGio;
        private ImageView btnAnHien;

        public LichSuHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayGio=itemView.findViewById(R.id.boCauHoi_tvTenBoCauHoi);
            btnAnHien=itemView.findViewById(R.id.boCauHoi_btnAnHien);
        }
    }
}
