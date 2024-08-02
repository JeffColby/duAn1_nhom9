package fpoly.thanhntph47592.truthordarecustom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import fpoly.thanhntph47592.truthordarecustom.R;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrayList;

    public SpinnerAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.spinner_text_item,parent,false);
        TextView tvNoiDung=convertView.findViewById(R.id.spinner_tvNoiDung);
        tvNoiDung.setText(arrayList.get(position));
        return convertView;
    }
}
