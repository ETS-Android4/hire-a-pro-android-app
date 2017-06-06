package hireapro.himanshu.hireapro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import hireapro.himanshu.hireapro.dataclass.GPSTracker;

public class SplashActivity extends AppCompatActivity {
    public static SharedPreferences sp;
    public static GPSTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getPreferences(Context.MODE_PRIVATE);
       /* Location targetLocation = new Location("");//provider name is unnecessary
        targetLocation.setLatitude(28.6088105d);//your coords of course
        targetLocation.setLongitude(77.0941665d);


        Location myLocation = new Location("");//provider name is unnecessary
        myLocation.setLatitude(28.7888105d);//your coords of course
        myLocation.setLongitude(77.0231665d);

        float distanceInMeters =  targetLocation.distanceTo(myLocation);

        Log.d("Diatnace", distanceInMeters+"");*/


        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET},11);
                return;
            }
        }
        else {

            gpsTracker = new GPSTracker(SplashActivity.this);
        }

        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 11:
                if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                    gpsTracker = new GPSTracker(SplashActivity.this);
                return;
        }
    }
}
