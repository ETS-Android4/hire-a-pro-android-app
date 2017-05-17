package hireapro.himanshu.hireapro;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 17/5/17.
 */

public class ProfessionalListAdapter extends RecyclerView.Adapter<ProfessionalListAdapter.MyViewHolder> {

    private List<ProfessionalType>professionalTypeList;
    LayoutInflater layoutInflater;


    public ProfessionalListAdapter(List<ProfessionalType>professionalType)
    {
        this.professionalTypeList = professionalTypeList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pro_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       ProfessionalType professionalType = professionalTypeList.get(position);
        holder.proTypeTextView.setText(professionalType.getProType());
        holder.protypeImageView.setImageResource(professionalType.getImageID());

    }

    @Override
    public int getItemCount() {
        return professionalTypeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView proTypeTextView;
        ImageView protypeImageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            protypeImageView = (ImageView) itemView.findViewById(R.id.pro_type_imageview);
            proTypeTextView = (TextView) itemView.findViewById(R.id.pro_type_textview);
        }
    }


}
