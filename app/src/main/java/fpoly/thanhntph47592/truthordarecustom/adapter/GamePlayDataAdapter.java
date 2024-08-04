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
import fpoly.thanhntph47592.truthordarecustom.model.GamePlayData;

public class GamePlayDataAdapter extends RecyclerView.Adapter<GamePlayDataAdapter.LichSuHolder>{

    private Context context;
    private ArrayList<GamePlayData> arrayList;
    private HistoryFeatures historyFeatures;

    public GamePlayDataAdapter(Context context, ArrayList<GamePlayData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        historyFeatures=new HistoryFeatures(context);
    }

    @NonNull
    @Override
    public LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.question_group_item,parent,false);
        return new LichSuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuHolder holder, int position) {
        GamePlayData gamePlayData =arrayList.get(position);

        holder.tvTime.setText(gamePlayData.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyFeatures.showGamePlayData(gamePlayData);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                historyFeatures.deleteGamePlayData(gamePlayData,arrayList, GamePlayDataAdapter.this);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class LichSuHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private ImageView btnExtend;

        public LichSuHolder(@NonNull View itemView) {
            super(itemView);
            tvTime =itemView.findViewById(R.id.questionGroup_tvName);
            btnExtend =itemView.findViewById(R.id.questionGroup_btnExtend);

            btnExtend.setVisibility(View.GONE);
        }
    }
}
