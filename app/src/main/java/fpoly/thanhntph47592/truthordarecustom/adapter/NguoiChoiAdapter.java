package fpoly.thanhntph47592.truthordarecustom.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;
import fpoly.thanhntph47592.truthordarecustom.features.GamePlayFeatures;

public class NguoiChoiAdapter extends RecyclerView.Adapter<NguoiChoiAdapter.NguoiChoiHolder>{

    private Context context;
    private ArrayList<String> arrayList;
    private GamePlayFeatures gamePlayFeatures;

    public NguoiChoiAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        gamePlayFeatures=new GamePlayFeatures(context);
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NguoiChoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.nguoichoi_item,parent,false);
        return new NguoiChoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiChoiHolder holder, int position) {
        String tenNguoiChoi=arrayList.get(position);

        holder.tvTenNguoiChoi.setText(tenNguoiChoi);
        holder.tvTenNguoiChoi.setMovementMethod(new ScrollingMovementMethod());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.xoaNguoiChoi(holder.getAdapterPosition(),arrayList,NguoiChoiAdapter.this);
            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.suaNguoiChoi(holder.getAdapterPosition(),tenNguoiChoi,arrayList,
                        NguoiChoiAdapter.this);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NguoiChoiHolder extends RecyclerView.ViewHolder {

        private TextView tvTenNguoiChoi;
        private ImageView btnSua,btnXoa;

        public NguoiChoiHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNguoiChoi=itemView.findViewById(R.id.nguoiChoi_tvTenNguoiChoi);
            btnSua=itemView.findViewById(R.id.nguoiChoi_btnSua);
            btnXoa=itemView.findViewById(R.id.nguoiChoi_btnXoa);
        }
    }
}
