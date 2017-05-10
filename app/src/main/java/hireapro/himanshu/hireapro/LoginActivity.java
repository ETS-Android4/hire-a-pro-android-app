package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String loginUrl ;
    private int CONNECTIONOUT_TIME = 15000;
    ProgressDialog progressDialog;

private User user;
    //views
    private EditText emailPhoneEditText,passwordEditText;
    private Button loginButton,fbLoginButton,googleLoginButton;
    private Switch remembermeSwitch;
    private TextView forgotPasswordTextView,createAccountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intializeComponents();
        loginButton.setOnClickListener(this);
        createAccountTextView.setOnClickListener(this);
        fbLoginButton.setOnClickListener(this);
        googleLoginButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.login_button:
                getUserData();
                validateData();
                prepareUrl();
                new ConnectServer().execute(user);
                break;
            case R.id.create_account_textview:
                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                break;
            case R.id.forgot_password_textview:
                // your code for button2 here
                break;
            case R.id.facebook_login_button:
                Toast.makeText(LoginActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.google_login_button:
                Toast.makeText(LoginActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void prepareUrl() {
      
        loginUrl  = "http://hireapro.netii.net/api/user/loginuser_with_phoneno.php?phone_no=";
        loginUrl = loginUrl + user.getPhone();
        loginUrl = loginUrl + "&password=";
        loginUrl = loginUrl + user.getPassword();
        System.out.print(loginUrl);
    }



    private void getUserData() {


        user.setPhone(Long.valueOf(emailPhoneEditText.getText().toString()));
        user.setPassword(passwordEditText.getText().toString());
    }

    private void validateData() {
    }


    private void intializeComponents() {
        emailPhoneEditText = (EditText) findViewById(R.id.phone_email_edittext);
        passwordEditText= (EditText) findViewById(R.id.password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);
        fbLoginButton= (Button) findViewById(R.id.facebook_login_button);
        googleLoginButton = (Button) findViewById(R.id.google_login_button);
     //   remembermeSwitch = (Switch) findViewById(R.id.remember_me_toggle);
        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_textview);
        createAccountTextView = (TextView) findViewById(R.id.create_account_textview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
      //  progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait ....");
        user = new User();

    }



    public class ConnectServer extends AsyncTask < User,String,String>
    {
boolean loginFailure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(User... users) {
            HttpURLConnection httpURLConnection = null;
         //   httpURLConnection.setConnectTimeout(CONNECTIONOUT_TIME);
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(loginUrl);
                httpURLConnection  =(HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer =new StringBuffer();

                String line = "";

                while((line = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(line);
                }
                String response = stringBuffer.toString();
                Log.d("response",response);
                JSONObject finalResponse  = new JSONObject(response);
                String name = finalResponse.optString("user_name");
                int stat = finalResponse.getInt("status");
                if(stat!=201)
                {
                    Toast.makeText(LoginActivity.this, "Unable to Login", Toast.LENGTH_SHORT).show();
                    return name;
                }
                else {
                    startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
                    return null;
                }


            }


            catch (Exception e)
            {
                e.printStackTrace();
                loginFailure = true;

            }
            finally {


                if(httpURLConnection!=null)
                httpURLConnection.disconnect();
            }

return  null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();

            if(loginFailure==true)
            {
                Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
