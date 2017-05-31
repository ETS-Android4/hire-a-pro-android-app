package hireapro.himanshu.hireapro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    public static SharedPreferences sp;
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

        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }
}
