package hireapro.himanshu.hireapro.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hireapro.himanshu.hireapro.R;
import hireapro.himanshu.hireapro.adapters.ProCategoryAdapter;
import hireapro.himanshu.hireapro.adapters.ViewPagerAdapter;
import hireapro.himanshu.hireapro.dataclass.ProfessionalCategory;
import hireapro.himanshu.hireapro.dataclass.Utilities;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalListFragment extends Fragment implements View.OnClickListener {

    RecyclerView.LayoutManager mLayoutManager;
    ProCategoryAdapter proCategoryAdapter;
    ProfessionalCategory professionalCategory;
    ViewPager imageSliderViewPager;
    LinearLayout plumberLinearLayout, electricianLinearLayout, painterLinearLayout, cateringLinearLayout, architectLinearLayout, locksmithLinearLayout,
            computerRepairLinearLayout, packersAndMoversLinearLayout, pestControlLinearLayout, phoneRepairLinearLayout;
    TextView cityHeaderTextView;
    View rootView;
    String searchUrl,favoriteUrl;
    //String searchUrl = "http://hireapro.netii.net/api/user/favorite.php?user_id=";
    private RecyclerView proCategoryRecyclerView;
    private List<ProfessionalCategory> professionalCategoryList = new ArrayList<ProfessionalCategory>();

    public ProfessionalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Implementing View Pager
        rootView = inflater.inflate(R.layout.fragment_professional_list, container, false);
        imageSliderViewPager = (ViewPager) rootView.findViewById(R.id.imageSliderViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        imageSliderViewPager.setAdapter(viewPagerAdapter);


        initializeComponents();
        //prepareUrl();
        return rootView;
        //return inflater.inflate(R.layout.fragment_professional_list, container, false);
    }

    private void prepareSearchUrl(String userType) {
        searchUrl = Utilities.SERVER_URL;
        searchUrl = searchUrl + "/pro/list_professional.php?type=";
        searchUrl = searchUrl + userType;
        searchUrl = searchUrl + "&user_latitude=";
        searchUrl = searchUrl + Utilities.location_latitude;
        searchUrl = searchUrl + "&user_longitude=";
        searchUrl = searchUrl + Utilities.location_longitude;
        searchUrl = searchUrl + "&distance=30";
        Log.d("Home Screen Url ", searchUrl);
    }





    private void initializeComponents() {
        plumberLinearLayout = (LinearLayout) rootView.findViewById(R.id.plumber_ll);
        electricianLinearLayout = (LinearLayout) rootView.findViewById(R.id.electrician_ll);
        painterLinearLayout = (LinearLayout) rootView.findViewById(R.id.painter_ll);
        cateringLinearLayout = (LinearLayout) rootView.findViewById(R.id.catering_ll);
        architectLinearLayout = (LinearLayout) rootView.findViewById(R.id.architect_ll);
        locksmithLinearLayout = (LinearLayout) rootView.findViewById(R.id.locksmith_ll);
        computerRepairLinearLayout = (LinearLayout) rootView.findViewById(R.id.computer_repair_ll);
        packersAndMoversLinearLayout = (LinearLayout) rootView.findViewById(R.id.packers_ll);
        pestControlLinearLayout = (LinearLayout) rootView.findViewById(R.id.pest_control_ll);
        phoneRepairLinearLayout = (LinearLayout) rootView.findViewById(R.id.mobile_repair_ll);
        cityHeaderTextView = (TextView) rootView.findViewById(R.id.locality_textview);

        plumberLinearLayout.setOnClickListener(this);
        electricianLinearLayout.setOnClickListener(this);
        painterLinearLayout.setOnClickListener(this);
        cateringLinearLayout.setOnClickListener(this);
        architectLinearLayout.setOnClickListener(this);
        locksmithLinearLayout.setOnClickListener(this);
        computerRepairLinearLayout.setOnClickListener(this);
        packersAndMoversLinearLayout.setOnClickListener(this);
        pestControlLinearLayout.setOnClickListener(this);
        phoneRepairLinearLayout.setOnClickListener(this);
        cityHeaderTextView.setText(Utilities.locality);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.electrician_ll:


        }
    }
}
