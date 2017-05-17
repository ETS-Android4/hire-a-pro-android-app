package hireapro.himanshu.hireapro;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {


    private ProfessionalListFragment professionalListFragment;
    private NotificationFragment notificationFragment;
    private FavoritesFragment favoritesFragment;
    private SettingsFragment settingsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    RecyclerView proTypeRecyclerview;
    ProfessionalListAdapter professionalListAdapter;
     List<ProfessionalType> professionalType;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.content, professionalListFragment).commit();
                    return true;
                case R.id.navigation_favorites:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.content, favoritesFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.content, notificationFragment).commit();
                    return true;
                case R.id.navigation_settings:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.content, settingsFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initializeComponents();





        //Setting Default Fragment
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, professionalListFragment).commit();
    }

    private void initializeComponents() {
        fragmentManager = getFragmentManager();
        professionalListFragment = new ProfessionalListFragment();
        favoritesFragment = new FavoritesFragment();
        notificationFragment = new NotificationFragment();
        settingsFragment = new SettingsFragment();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        proTypeRecyclerview = (RecyclerView) findViewById(R.id.pro_list_recyclerview);
        professionalListAdapter = new ProfessionalListAdapter(professionalType);
        proTypeRecyclerview.setAdapter(professionalListAdapter);
        proTypeRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

}
