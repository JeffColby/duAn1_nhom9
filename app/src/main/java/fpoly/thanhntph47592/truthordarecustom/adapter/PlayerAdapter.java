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

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.NguoiChoiHolder>{

    private Context context;
    private ArrayList<String> arrayList;
    private GamePlayFeatures gamePlayFeatures;

    public PlayerAdapter(Context context, ArrayList<String> arrayList) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.player_item,parent,false);
        return new NguoiChoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiChoiHolder holder, int position) {
        String playerName =arrayList.get(position);

        holder.tvPlayerName.setText(playerName);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.deletePlayer(holder.getAdapterPosition(),arrayList, PlayerAdapter.this);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayFeatures.changePlayer(holder.getAdapterPosition(), playerName,arrayList,
                        PlayerAdapter.this);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NguoiChoiHolder extends RecyclerView.ViewHolder {

        private TextView tvPlayerName;
        private ImageView btnEdit, btnDelete;

        public NguoiChoiHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayerName =itemView.findViewById(R.id.player_tvPlayerName);
            btnEdit =itemView.findViewById(R.id.player_btnEdit);
            btnDelete =itemView.findViewById(R.id.player_btnDelete);

            tvPlayerName.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
