package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hireapro.himanshu.hireapro.dataclass.Professional;
import hireapro.himanshu.hireapro.dataclass.User;

public class SearchProfessionalActivity extends AppCompatActivity {
    int DEFAULTDISTANCE = 5;
    ProgressDialog progressDialog;
    RecyclerView searchedProList;
    RecyclerView.LayoutManager mLayoutManager;
    Professional professional;
    String searchUrl = "http://hireapro.netii.net/api/pro/list_professional.php?type=";
    String imageUrl;
    Bitmap defaultProImage;
    private String professionalType = "plumber";
    private double userLatitude = 28.350595, userLongitude = 77.3543528;
    private int distance = DEFAULTDISTANCE;
    private RecyclerView recyclerView;
    private List<Professional> professionalList = new ArrayList<>();
    private SearchProfessionalAdapter searchProfessionalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search Results");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeComponents();
        extractParameters();
        prepareUrl(professionalType, userLatitude, userLongitude, distance);
        new ConnectServer().execute();


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(SearchProfessionalActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView t = (TextView) view.findViewById(R.id.name_search_result_row_textview);
                        // Toast.makeText(SearchProfessionalActivity.this, "" + t.getText().toString() + "Position" + position, Toast.LENGTH_SHORT).show();
                        Professional professional;
                        Intent proDetails = new Intent(SearchProfessionalActivity.this, DetailedProfessionalInfo.class);

                        professional = professionalList.get(position);

                        proDetails.putExtra("ProObject", professional);
                        startActivity(proDetails);
                    }
                })
        );
    }

    private void extractParameters() {
    }

    private void initializeComponents() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait ....");
        recyclerView = (RecyclerView) findViewById(R.id.pro_search_recycler);
        searchProfessionalAdapter = new SearchProfessionalAdapter(professionalList);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchProfessionalAdapter);
        defaultProImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_user);
    }

    private void prepareUrl(String professionalType, double userLatitude, double userLongitude, int distance) {
        searchUrl = searchUrl + professionalType + "&user_latitude=";
        searchUrl = searchUrl + userLatitude + "&user_longitude=";
        searchUrl = searchUrl + userLongitude + "&distance=";
        searchUrl = searchUrl + distance;
    }

    public class ConnectServer extends AsyncTask<User, String, String> {
        boolean loginFailure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(User... users) {
            HttpURLConnection httpURLConnection = null;
            Bitmap b;
            //   httpURLConnection.setConnectTimeout(CONNECTIONOUT_TIME);
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(searchUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                String response = stringBuffer.toString();

                Log.d("reponse", response);
                JSONObject finalResponse = new JSONObject(response);
                JSONArray jsonArray = finalResponse.getJSONArray("professional_list");


                for (int i = 0; i < jsonArray.length(); i++) {
                    professional = new Professional();
                    JSONObject finaljsonobject = jsonArray.getJSONObject(i);
                    professional.setProID(finaljsonobject.getString("pro_id"));
                    professional.setName(finaljsonobject.getString("name"));
                    professional.setBaseRate(Integer.valueOf(finaljsonobject.getString("base_rate")));
                    professional.setPhoneNumber(Long.valueOf(finaljsonobject.getString("phone_no")));
                    professional.setJob(finaljsonobject.getString("job_name"));
                    professional.setProfilePictureURL(finaljsonobject.getString("profile_picture_url"));

                    // professional.setSecondryPhoneNumber(Long.valueOf(finaljsonobject.getString("phone_no_secondary")));
                    professional.setAddress(finaljsonobject.getString("address"));
                    professional.setLocationLatitude(Float.valueOf(finaljsonobject.getString("base_location_latitude")));
                    professional.setLocationLongitude(Float.valueOf(finaljsonobject.getString("base_location_longitude")));
                    professional.setDistanceFromUser(Float.valueOf(finaljsonobject.getString("distance")));

                    if (professional.getProfilePictureURL().equals(""))
                        professional.setUserImage(defaultProImage);
                    else {
                        url = new URL(professional.getProfilePictureURL());
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(100 * 30);
                        httpURLConnection.setReadTimeout(100 * 30);

                        b = BitmapFactory.decodeStream((InputStream) httpURLConnection.getContent(), null, null);
                        if (b == null)
                            b = defaultProImage;
                        professional.setUserImage(b);
                    }
                    //    Log.d("Sample Data",finaljsonobject.getString("OutletName"));
                    professionalList.add(professional);

                    //Log.d("outletData", outlet.getOutletName());
                }

            } catch (Exception e) {
                e.printStackTrace();
                loginFailure = true;

            } finally {


                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            searchProfessionalAdapter.notifyDataSetChanged();

            //Hiding the progress bar

            progressDialog.hide();

        }
    }


}
