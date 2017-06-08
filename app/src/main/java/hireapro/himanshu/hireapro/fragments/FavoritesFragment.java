package hireapro.himanshu.hireapro.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import hireapro.himanshu.hireapro.DetailedProfessionalInfo;
import hireapro.himanshu.hireapro.R;
import hireapro.himanshu.hireapro.RecyclerItemClickListener;
import hireapro.himanshu.hireapro.adapters.SearchProfessionalAdapter;
import hireapro.himanshu.hireapro.dataclass.Professional;
import hireapro.himanshu.hireapro.dataclass.User;
import hireapro.himanshu.hireapro.dataclass.Utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

private RecyclerView favoriteRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SearchProfessionalAdapter mAdapter;
    Professional professional;
    ProgressDialog progressDialog;
    String favoriteUrl;
    boolean firstTime=true;
    private String professionalType = "plumber";
    String searchUrl = "http://hireapro.netii.net/api/user/favorite.php?user_id=";

    Bitmap defaultProImage;
    private List<Professional> professionalList = new ArrayList<>();

    public FavoritesFragment() {
        // Required empty public constructor
    }

    private void prepareFavoriteUrl() {
        favoriteUrl = Utilities.SERVER_URL;
        favoriteUrl = favoriteUrl + "/user/favorite.php?user_id=";
        favoriteUrl = favoriteUrl + "UID101";
        favoriteUrl = favoriteUrl + "&user_latitude=";
        favoriteUrl = favoriteUrl + Utilities.location_latitude;
        favoriteUrl = favoriteUrl + "&user_longitude=";
        favoriteUrl = favoriteUrl + Utilities.location_longitude;
        Log.d("Favorite Url ", favoriteUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);
        prepareUrl(Utilities.id, Utilities.location_latitude, Utilities.location_longitude);
        favoriteRecyclerView = (RecyclerView) view.findViewById(R.id.favorite_fragment_recycler_view);
        initializeComponents();
        prepareFavoriteUrl();
        if(firstTime==true )
        new ConnectServer().execute();
        favoriteRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView t = (TextView) view.findViewById(R.id.name_search_result_row_textview);
                        // Toast.makeText(SearchProfessionalActivity.this, "" + t.getText().toString() + "Position" + position, Toast.LENGTH_SHORT).show();
                        Professional professional;
                        Intent proDetails = new Intent(getActivity(), DetailedProfessionalInfo.class);

                        professional = professionalList.get(position);

                        proDetails.putExtra("ProObject", professional);
                        startActivity(proDetails);
                    }
                })
        );


        firstTime = false;
        return view;

    }

    private void prepareUrl(String userID, double userLatitude, double userLongitude) {
        searchUrl = searchUrl + userID + "&user_latitude=";
        searchUrl = searchUrl + userLatitude + "&user_longitude=";
        searchUrl = searchUrl + userLongitude ;
      //  Log.d("URL",searchUrl);
    }

    private void initializeComponents() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait ....");
        mLayoutManager = new GridLayoutManager(this.getActivity(),2);
        favoriteRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SearchProfessionalAdapter(professionalList);
        favoriteRecyclerView.setAdapter(mAdapter);
        defaultProImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_user);

    }

    public class ConnectServer extends AsyncTask<User, String, String> {
        boolean loginFailure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
            Log.d("favorite url",favoriteUrl);
        }

        @Override
        protected String doInBackground(User... users) {
            HttpURLConnection httpURLConnection = null;
            Bitmap b;
            //   httpURLConnection.setConnectTimeout(CONNECTIONOUT_TIME);
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(favoriteUrl);
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

                    if(professional.getProfilePictureURL().equals(""))
                        professional.setUserImage(defaultProImage);
                    else {
                        url = new URL(professional.getProfilePictureURL());
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(100 * 30);
                        httpURLConnection.setReadTimeout(100 * 30);

                        b = BitmapFactory.decodeStream((InputStream) httpURLConnection.getContent(), null, null);
                        if(b==null)
                            b=defaultProImage;
                        professional.setUserImage(b);
                    }
                    //    Log.d("Sample Data",finaljsonobject.getString("OutletName"));
                    professionalList.add(professional);

                    //Log.d("outletData", outlet.getOutletName());
                }

            }
            catch (UnknownHostException ex)
            {
               // Toast.makeText(getActivity(), "No Internet connection", Toast.LENGTH_SHORT).show();
            }

            catch (Exception e) {
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


            mAdapter.notifyDataSetChanged();

            //Hiding the progress bar

            progressDialog.hide();

        }
    }

}
