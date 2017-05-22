package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import hireapro.himanshu.hireapro.dataclass.Professional;

public class DetailedProfessionalInfo extends AppCompatActivity implements Serializable {

    private ImageView proImageIV;
    private RatingBar proRatingRB;
    private ToggleButton favoriteOnTB;
    private TextView ratingTV,addressShortTV,distanceTV,phoneNumberTV,fulladdressTV,baseRateTV,jobTV;
    private Button callButton,msgButton;
    private Toolbar proNameToolbar;
    Professional professional;
    Bitmap defaultProImage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_professional_info);
        initializeComponents();



        Intent i = getIntent();
        professional = (Professional) i.getSerializableExtra("ProObject");
        proNameToolbar.setTitle(professional.getName().toString());
        //proImageIV.setImageBitmap(b);
        setSupportActionBar(proNameToolbar);
        new DisplayData().execute();
        

    }



    private void initializeComponents() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait ....");
        proImageIV = (ImageView) findViewById(R.id.pro_image_detaileds_imageview);
        ratingTV = (TextView) findViewById(R.id.rating_textview);
        proRatingRB = (RatingBar) findViewById(R.id.detailed_pro_rating_bar);
        addressShortTV=(TextView)findViewById(R.id.pro_address);
        distanceTV = (TextView)findViewById(R.id.distance_between);
        phoneNumberTV = (TextView)findViewById(R.id.phoneno_detailed_textview);
        fulladdressTV = (TextView)findViewById(R.id.pro_fulladdress_textview);
        baseRateTV = (TextView)findViewById(R.id.baserate_detailed_textview);
        jobTV = (TextView)findViewById(R.id.job_detailed_textview);
        callButton = (Button) findViewById(R.id.call_pro_btn);
        msgButton = (Button) findViewById(R.id.msg_pro_btn);
        proNameToolbar =  (Toolbar) findViewById(R.id.pro_name_toolbar);
        defaultProImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_user);
    }

    public class DisplayData extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap b) {
            super.onPostExecute(b);
            proImageIV.setImageBitmap(b);
            addressShortTV.setText(professional.getAddress().toString());
            distanceTV.setText(professional.getDistanceFromUser()+" Km(s) Away");
            phoneNumberTV.setText(professional.getPhoneNumber()+"");
            fulladdressTV.setText(professional.getAddress().toString());
            baseRateTV.setText("₹ " +professional.getBaseRate()+" per hour");
            jobTV.setText(professional.getJob().toString());
            progressDialog.hide();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            URL url; HttpURLConnection httpURLConnection = null; Bitmap b=null;
            String urlh = professional.getProfilePictureURL();
            if(urlh.equals(""))
              return defaultProImage;
            else {
            try {
                url = new URL(urlh);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(100 * 30);
                httpURLConnection.setReadTimeout(100 * 30);

                b = BitmapFactory.decodeStream((InputStream) httpURLConnection.getContent(), null, null);

            }
            catch (Exception e)
            {

            }
            return b;
        }}
    }
}
