package hireapro.himanshu.hireapro.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hireapro.himanshu.hireapro.ProfessionalListAdapter;
import hireapro.himanshu.hireapro.ProfessionalCategory;
import hireapro.himanshu.hireapro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalListFragment extends Fragment {

    private RecyclerView pro_list;

   ProfessionalListAdapter professionalListAdapter;
    List<ProfessionalCategory> professionalTypeList;

    public ProfessionalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_professional_list,null);
        pro_list = (RecyclerView) rootview.findViewById(R.id.pro_type_list_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        pro_list.setLayoutManager(llm);
        pro_list.setItemAnimator(new DefaultItemAnimator());
        professionalListAdapter = new ProfessionalListAdapter(professionalTypeList);
        pro_list.setAdapter(professionalListAdapter);
        prepareProfessionalListData();

        return inflater.inflate(R.layout.fragment_professional_list, container, false);
    }

    private void prepareProfessionalListData() {
        professionalTypeList= new ArrayList<ProfessionalCategory>();

        ProfessionalCategory professionalType = new ProfessionalCategory(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);

        professionalType = new ProfessionalCategory(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);

        professionalType = new ProfessionalCategory(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);



        professionalListAdapter.notifyDataSetChanged();
    }

}
