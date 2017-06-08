package hireapro.himanshu.hireapro.dataclass;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import hireapro.himanshu.hireapro.SplashActivity;

/**
 * Created by Himanshu on 22/5/17.
 */

public class Utilities {

    //Server Base Urls
    public static final String SERVER_URL = "http://hireapro.netii.net/api";
    public static final String IMAGE_URL_PRO = "http://hireapro.netii.net/images/pro/upload.php";
    public static final String IMAGE_URL_USER = "http://hireapro.netii.net/images/user/upload.php";


    //Key name to be used when storing data usign SHaredPreference
    public static final String REMEMBER_LOGIN = "REMEMBER_LOGIN";
    public static final String ID = "ID";
    public static final String IS_USER_PROFESSIONAL = "IS_USER_PROFESSIONAL";
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String LOCALITY = "LOCALITY";
    public static final String USER_LATITUDE = "USER_LATITUDE";
    public static final String USER_LONGITUDE = "USER_LONGITUDE";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final int PERMISSION_REQUEST_CODE = 1;
    public static String OTP_URL;
    public static String name ;                   //Name of the user
    public static boolean isUserProfessional = false; //true - professional ,false - normal user
    public static String id ;                //id can be user_id or pro_id
    public static String address ;           //Complete addresss of the user
    public static float location_latitude ;  //user's last known latitude
    public static float location_longitude ;    //user's last known longitude
    public static Bitmap userProfile;           //user's profile picture
    public static Bitmap userProfile_mini;      //User's profile picture thumbnail
    public static String locality ;                  //User's locality name
    public static boolean rememberLogin = false;        //true - remember login ,false - do not remember login
    public static long phone_number ;            // users phone number
    public static SharedPreferences.Editor editor;        //Shared pref editor
    public static ProgressDialog progressDialog;

    static {
        editor = SplashActivity.sp.edit();

    }

    //For Showing Progress Dailog
    public static void showProgressBar(Context mContext,String message,boolean cancelable)
    {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void hideProgressBar()
    {
        progressDialog.hide();
    }

    //Check if user opted for remember login during last login
    public static boolean checkPreviousRememberMe() {
        boolean defaultValue = false;
        rememberLogin = SplashActivity.sp.getBoolean("REMEMBER_LOGIN", defaultValue);
        //if yes all important values are retrived
        if (rememberLogin) {
            id = SplashActivity.sp.getString("ID", "");
            name = SplashActivity.sp.getString("NAME", "");
            isUserProfessional = SplashActivity.sp.getBoolean("IS_USER_PROFESSIONAL", false);
            address = SplashActivity.sp.getString("ADDRESS", "");
            locality = SplashActivity.sp.getString("LOCALITY", "");
            location_latitude = SplashActivity.sp.getFloat("USER_LATITUDE", 0.0f);
            location_longitude = SplashActivity.sp.getFloat("USER_LONGITUDE", 0.0f);
            phone_number = SplashActivity.sp.getLong("PHONE_NUMBER", 0);
        }
        return rememberLogin;
    }

    //Setting the remember me data in shredpref
    public static void setRememberMe(boolean option) {

        if (option) {
            editor.putBoolean("REMEMBER_LOGIN", option);
            editor.putString("ID", id);
            editor.putBoolean("IS_USER_PROFESSIONAL", isUserProfessional);
            editor.putString("NAME", name);
            editor.commit();
        } else {
            editor.putBoolean("REMEMBER_LOGIN", option);
            editor.putString("ID", "");
            editor.putBoolean("IS_USER_PROFESSIONAL", false);
            editor.putString("NAME", "");
            editor.putString("ADDRESS", "");
            editor.putString("LOCALITY", "");
            editor.putFloat("USER_LATITUDE", 0.0f);
            editor.putFloat("USER_LONGITUDE", 0.0f);
            editor.putLong("PHONE_NUMBER", 0);
            editor.commit();
        }
        Log.d("SetRemmberMecalled", "id : " + id);
        Log.d("Rember Login", SplashActivity.sp.getBoolean("REMEMBER_LOGIN", false) + "");
    }

    public static void persistLocation(float lat, float lon) {
        editor.putFloat("USER_LATITUDE", location_latitude);
        editor.putFloat("USER_LONGITUDE", location_longitude);
        editor.commit();
    }

    public static float[] getPersistedLocation() {

        return new float[]{SplashActivity.sp.getFloat("USER_LATITUDE", 0.0f), SplashActivity.sp.getFloat("USER_LONGITUDE", 0.0f)};
    }


    public static void persistPhoneNumber(long phoneNumber) {
        editor.putLong("PHONE_NUMBER", phone_number);
        editor.commit();
    }

    //retrives persistently stored Phone number
    public static long getPersistedPhoneNumber() {
        return SplashActivity.sp.getLong("PHONE_NUMBER", 0);
    }

    //Stores locality name persistently
    public static void persistLocality() {
        editor.putString("", locality);
        editor.commit();
    }

    //retrives persistently stored locality name
    public static String getPersistedLocality() {
        return SplashActivity.sp.getString("LOCALITY", "");
    }

    //Stores full address persistently
    public static void persistFullAddress() {
        editor.putString("ADDRESS", address);
        editor.commit();
    }

    //retrives persistently stored address name
    public static String getPersistedAddress() {
        return SplashActivity.sp.getString("ADDRESS", "");
    }


    //Gets locality name from coordinates
    public static String getClosestCityName(Context context, double latitude, double longitude) {
        String cityName = "";
        try {

            Geocoder geocoder = new Geocoder(context);

            //getting possible address list from given lat ,long and limiting no of results to 1
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);

            //Checking results
            if (addressList.size() > 0) // > 0 means City name found
            {
                cityName = addressList.get(0).getLocality();

                Utilities.locality  = addressList.get(0).getSubLocality() + ", " + addressList.get(0).getLocality();
                Log.d("Locality",addressList.get(0).getLocality());
                Log.d("Admin Area",addressList.get(0).getAdminArea());
                Log.d("Country name",addressList.get(0).getCountryName());
                Log.d("Feature name",addressList.get(0).getFeatureName());
               // Log.d("Phone",addressList.get(0).getPhone());
        //        Log.d("POstal code",addressList.get(0).getPostalCode());
//                Log.d("POstal code",addressList.get(0).getPremises());
      //         Log.d("POstal code",addressList.get(0).getSubAdminArea());
       //     Log.d("Sublocality",addressList.get(0).getSubLocality());
                Log.d("Address line",addressList.get(0).getAddressLine(0));


            }

            else
                cityName = null; // No result found


        } catch (IOException e) {
            Log.e("Geocoder Exception",e.toString());

        }
        return cityName;

    }
}


