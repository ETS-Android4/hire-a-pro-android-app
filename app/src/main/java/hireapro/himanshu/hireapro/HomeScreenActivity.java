package hireapro.himanshu.hireapro;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {


    RecyclerView proTypeRecyclerview;
    ProfessionalListAdapter professionalListAdapter;
    List<ProfessionalType> professionalTypeList;

    //Fragment declaration
    private ProfessionalListFragment professionalListFragment;
    private NotificationFragment notificationFragment;
    private FavoritesFragment favoritesFragment;
    private SettingsFragment settingsFragment;

    //Fragment Manager and transaction declaration
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initializeComponents();




    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragmentTransaction.replace(R.id.content, professionalListFragment).commit();
                    return true;
                case R.id.navigation_favorites:

                    fragmentTransaction.replace(R.id.content, favoritesFragment).commit();
                    return true;
                case R.id.navigation_notifications:

                    fragmentTransaction.replace(R.id.content, notificationFragment).commit();
                    return true;
                case R.id.navigation_settings:

                    fragmentTransaction.replace(R.id.content, settingsFragment).commit();
                    return true;
            }
            return false;
        }

    };



    private void initializeComponents() {
        fragmentManager = getFragmentManager();

        //Fragment Inialization
        professionalListFragment = new ProfessionalListFragment();
        favoritesFragment = new FavoritesFragment();
        notificationFragment = new NotificationFragment();
        settingsFragment = new SettingsFragment();

        //
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(0);

//        proTypeRecyclerview = (RecyclerView) findViewById(R.id.pro_type_list_recyclerview);
//        professionalListAdapter = new ProfessionalListAdapter(professionalTypeList);
        //proTypeRecyclerview.setAdapter(professionalListAdapter);
        //proTypeRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        proTypeRecyclerview.setLayoutManager(mLayoutManager);
//        proTypeRecyclerview.setItemAnimator(new DefaultItemAnimator());
//        proTypeRecyclerview.setAdapter(professionalListAdapter);

       // prepareProfessionalListData();





    }

    private void prepareProfessionalListData() {

        ProfessionalType professionalType = new ProfessionalType(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);

        professionalType = new ProfessionalType(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);

        professionalType = new ProfessionalType(R.drawable.catering_blue,"Yo");
        professionalTypeList.add(professionalType);



        professionalListAdapter.notifyDataSetChanged();
    }

}
