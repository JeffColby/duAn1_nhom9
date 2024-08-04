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
import fpoly.thanhntph47592.truthordarecustom.model.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.CauHoiHolder> {

    private Context context;
    private ArrayList<Question> arrayList;
    private static BasicFeatures basicFeatures;
    private QuestionsFeatures questionsFeatures;

    public QuestionAdapter(Context context, ArrayList<Question> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        basicFeatures=new BasicFeatures(context);
        questionsFeatures=new QuestionsFeatures(context);
    }

    public ArrayList<Question> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Question> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CauHoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.question_item,parent,false);
        return new CauHoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauHoiHolder holder, int position) {
        Question question =arrayList.get(position);

        basicFeatures.colorSetUp(question.getType(),holder.tvType,holder.layoutType);
        holder.tvContent.setText(question.getContent());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionsFeatures.deleteQuestion(question,arrayList, QuestionAdapter.this);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionsFeatures.editQuestion(question, QuestionAdapter.this,
                        holder.tvType,holder.layoutType,arrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CauHoiHolder extends RecyclerView.ViewHolder {

        private TextView tvType, tvContent;
        private ImageView btnExtend, btnDelete, btnEdit;
        private RelativeLayout layoutExtend, layoutType;

        public CauHoiHolder(@NonNull View itemView) {
            super(itemView);
            tvType =itemView.findViewById(R.id.question_tvType);
            tvContent =itemView.findViewById(R.id.question_tvContent);
            btnExtend =itemView.findViewById(R.id.question_btnExtend);
            btnDelete =itemView.findViewById(R.id.question_btnDelete);
            btnEdit =itemView.findViewById(R.id.question_btnEdit);
            layoutExtend =itemView.findViewById(R.id.question_layoutExtend);
            layoutType =itemView.findViewById(R.id.question_layoutType);

            tvContent.setMovementMethod(new ScrollingMovementMethod());

            btnExtend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basicFeatures.hideAndShow(btnExtend,layoutExtend);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basicFeatures.hideAndShow(btnExtend,layoutExtend);
                }
            });
        }
    }
}
