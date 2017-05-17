package hireapro.himanshu.hireapro;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 17/5/17.
 */

public class ProfessionalListAdapter extends RecyclerView.Adapter<ProfessionalListAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }


}
