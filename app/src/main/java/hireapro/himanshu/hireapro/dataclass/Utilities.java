package hireapro.himanshu.hireapro.dataclass;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import hireapro.himanshu.hireapro.SplashActivity;

/**
 * Created by root on 22/5/17.
 */

public class Utilities {


    public static String SERVER_URL = "http://hireapro.netii.net/api";
    public static String IMAGE_URL_PRO="http://hireapro.netii.net/images/pro/upload.php";
    public static String IMAGE_URL_USER="http://hireapro.netii.net/images/user/upload.php";

    public static String name;
    public static boolean isUserProfessional;
    public static String id;
    public static String address;
    public static float location_latitude;
    public static float location_longitude;
    public static Bitmap userProfile;
    public static Bitmap userProfile_mini;
    public static String city;
    public static boolean rememberLogin;
    public static long phone_number;

    public static final String REMEMBER_LOGIN  = "REMEMBER_LOGIN";
    public static final String ID  = "ID";
    public static final String IS_USER_PROFESSIONAL  = "IS_USER_PROFESSIONAL";
    public static final String NAME  = "NAME";
    public static final String ADDRESS  = "ADDRESS";
    public static final String CITY  = "CITY";
    public static final String USER_LATITUDE  = "USER_LATITUDE";
    public static final String USER_LONGITUDE  = "USER_LONGITUDE";
    public static final String PHONE_NUMBER  = "PHONE_NUMBER";


    public static boolean checkPreviousRememberMe()
    {
        boolean defaultValue=false;
        rememberLogin = SplashActivity.sp.getBoolean("REMEMBER_LOGIN",defaultValue);
        if (rememberLogin)
        {
            id = SplashActivity.sp.getString("ID","");
            name = SplashActivity.sp.getString("NAME","");

        }
        return rememberLogin;
    }

    public static void setRememberMe(boolean r)
    {
        SharedPreferences.Editor editor = SplashActivity.sp.edit();
        if(r) {
            editor.putBoolean("REMEMBER_LOGIN", r);
            editor.putString("ID", id);
            editor.putBoolean("IS_USER_PROFESSIONAL",isUserProfessional)
            editor.putString("NAME", name);
            editor.putString("ADDRESS",address);
            editor.putString("CITY",city);
            editor.putFloat("USER_LATITUDE",location_latitude);
            editor.putFloat("USER_LONGITUDE",location_longitude);
            editor.putLong("PHONE_NUMBER",phone_number);
            editor.commit();
        }
        else
        {
            editor.putBoolean("REMEMBER_LOGIN",false);
            editor.putString("USER_NAME","");
            editor.putString("NAME","");
            editor.commit();
        }
        Log.d("SetRemmberMecalled","id : "+id);
        Log.d("Rember Login",SplashActivity.sp.getBoolean("REMEMBER_LOGIN",false)+"");
    }

    public static void persistLocation(float lat,float lon)
    {

    }




}


