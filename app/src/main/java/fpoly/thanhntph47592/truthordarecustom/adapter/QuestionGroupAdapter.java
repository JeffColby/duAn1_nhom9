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
import fpoly.thanhntph47592.truthordarecustom.dao.QuestionDAO;
import fpoly.thanhntph47592.truthordarecustom.features.QuestionGroupsFeatures;
import fpoly.thanhntph47592.truthordarecustom.features.BasicFeatures;
import fpoly.thanhntph47592.truthordarecustom.model.QuestionGroup;
import fpoly.thanhntph47592.truthordarecustom.model.Question;

public class QuestionGroupAdapter extends RecyclerView.Adapter<QuestionGroupAdapter.BoCauHoiHolder>{

    private Context context;
    private ArrayList<QuestionGroup> arrayList;
    private ArrayList<Question> questionArrayList;
    private static BasicFeatures basicFeatures;
    private QuestionGroupsFeatures questionGroupsFeatures;
    private QuestionDAO questionDAO;

    public QuestionGroupAdapter(Context context, ArrayList<QuestionGroup> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        basicFeatures=new BasicFeatures(context);
        questionGroupsFeatures =new QuestionGroupsFeatures(context);
        questionDAO =new QuestionDAO(context);
    }

    public void setArrayList(ArrayList<QuestionGroup> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BoCauHoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.question_group_item,parent,false);
        return new BoCauHoiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoCauHoiHolder holder, int position) {
        QuestionGroup questionGroup =arrayList.get(position);
        questionArrayList = questionDAO.filterQuestionByGroup(questionGroup.getId());

        holder.tvName.setText(questionGroup.getName());
        holder.tvNumQuestions.setText("Số câu hỏi: "+(questionArrayList.size()+""));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.deleteQuestionGroup(questionArrayList.size(), questionGroup,arrayList, QuestionGroupAdapter.this);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.editQuestionGroup(questionGroup, QuestionGroupAdapter.this);
            }
        });
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGroupsFeatures.questionGroupDetail(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class BoCauHoiHolder extends RecyclerView.ViewHolder {

        private TextView tvName, btnDetail, tvNumQuestions;
        private ImageView btnExtend, btnDelete, btnEdit;
        private RelativeLayout layoutExtend;

        public BoCauHoiHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.questionGroup_tvName);
            btnExtend =itemView.findViewById(R.id.questionGroup_btnExtend);
            layoutExtend =itemView.findViewById(R.id.questionGroup_layoutExtend);
            btnDelete =itemView.findViewById(R.id.questionGroup_btnDelete);
            btnEdit =itemView.findViewById(R.id.questionGroup_btnEdit);
            btnDetail =itemView.findViewById(R.id.questionGroup_btnDetail);
            tvNumQuestions =itemView.findViewById(R.id.questionGroup_tvNumQuestions);

            btnExtend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basicFeatures.hideAndShow(btnExtend, layoutExtend);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basicFeatures.hideAndShow(btnExtend, layoutExtend);
                }
            });

            basicFeatures.textUnderlineSetUp(btnDetail,"Xem chi tiết");
            tvName.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
