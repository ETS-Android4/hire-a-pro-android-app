package hireapro.himanshu.hireapro.fragments;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hireapro.himanshu.hireapro.R;
import hireapro.himanshu.hireapro.adapters.ProCategoryAdapter;
import hireapro.himanshu.hireapro.adapters.ViewPagerAdapter;
import hireapro.himanshu.hireapro.dataclass.ProfessionalCategory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalListFragment extends Fragment {

    RecyclerView.LayoutManager mLayoutManager;
    ProCategoryAdapter proCategoryAdapter;
    ProfessionalCategory professionalCategory;
    ViewPager imageSliderViewPager;
    String searchUrl = "http://hireapro.netii.net/api/user/favorite.php?user_id=";
    private RecyclerView proCategoryRecyclerView;
    private double userLatitude = 28.350595, userLongitude = 77.3543528;
    private String userID = "UID101";

    private List<ProfessionalCategory> professionalCategoryList = new ArrayList<ProfessionalCategory>();

    public ProfessionalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_professional_list, container, false);
        imageSliderViewPager =(ViewPager) view.findViewById(R.id.imageSliderViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        imageSliderViewPager.setAdapter(viewPagerAdapter);

       // initializeComponents();
        return view;
        //return inflater.inflate(R.layout.fragment_professional_list, container, false);
    }

    private void initializeComponents() {



    }

    private void prepareProfessionalListData() {
        professionalCategoryList = new ArrayList<ProfessionalCategory>();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.electrician_green);

        ProfessionalCategory professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        professionalCategory = new ProfessionalCategory(b, "Yo");
        professionalCategoryList.add(professionalCategory);

        proCategoryAdapter.notifyDataSetChanged();
    }

}
