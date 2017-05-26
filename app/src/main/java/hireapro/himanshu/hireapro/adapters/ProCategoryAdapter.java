package hireapro.himanshu.hireapro.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hireapro.himanshu.hireapro.R;
import hireapro.himanshu.hireapro.dataclass.ProfessionalCategory;

/**
 * Created by root on 24/5/17.
 */

public class ProCategoryAdapter extends RecyclerView.Adapter<ProCategoryAdapter.MyViewHolder> {
    private List<ProfessionalCategory> ProfessionalCategoryList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView proCategoryImageview;
        public TextView proCategoryNameTextView;


        public MyViewHolder(View view) {
            super(view);
            proCategoryImageview = (ImageView) view.findViewById(R.id.pro_category_row_image);
            proCategoryNameTextView = (TextView) view.findViewById(R.id.pro_category_text);

        }
    }

    public ProCategoryAdapter(List<ProfessionalCategory> ProfessionalCategoryList) {
        this.ProfessionalCategoryList = ProfessionalCategoryList;
    }

    @Override
    public ProCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pro_category_row, parent, false);

        return new ProCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProCategoryAdapter.MyViewHolder holder, int position) {
        ProfessionalCategory proProfessionalCategory = ProfessionalCategoryList.get(position);
        //holder.profilePictureImageView.setImageResource(R.drawable.catering_green);
       // holder.proCategoryImageview.setImageResource(proProfessionalCategory.getImageID());

        holder.proCategoryImageview.setImageBitmap(proProfessionalCategory.getImage());
        holder.proCategoryNameTextView.setText(proProfessionalCategory.getProType());

    }

    @Override
    public int getItemCount() {
        return ProfessionalCategoryList.size();
    }


}
