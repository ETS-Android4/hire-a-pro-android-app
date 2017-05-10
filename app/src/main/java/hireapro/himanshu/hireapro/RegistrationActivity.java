package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerRegisterButton,facebookRegisterloginButton,gLoginRegisterButton;
    private EditText nameRegisterEditText,phoneRegisterEditText,passwordRegisterEditText;
    private String name ,password;
    private long phone;
    private User user;
    private String registerUrl = "http://hireapro.netii.net/api/user/registeruser_byphone_api.php?name=";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        intializeComponents();
        registerRegisterButton.setOnClickListener(this);
        facebookRegisterloginButton.setOnClickListener(this);
        gLoginRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.registerBTN:
                getUserData();
                validateData();
                prepareUrl();
                new RegistrationActivity.ConnectServer().execute(user);
                break;
            case R.id.facebook_login_button:
                Toast.makeText(RegistrationActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.google_login_button:
                Toast.makeText(RegistrationActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void getUserData() {
        user.setName(nameRegisterEditText.getText().toString());
        user.setPhone(Long.valueOf(phoneRegisterEditText.getText().toString()));
        user.setPassword(passwordRegisterEditText.getText().toString());
    }

    private void intializeComponents() {

        registerRegisterButton = (Button) findViewById(R.id.registerBTN);
        facebookRegisterloginButton= (Button) findViewById(R.id.facebook_login_button);
        gLoginRegisterButton = (Button) findViewById(R.id.google_login_button);
        //   remembermeSwitch = (Switch) findViewById(R.id.remember_me_toggle);
  nameRegisterEditText =(EditText) findViewById(R.id.name_edittext);
        phoneRegisterEditText = (EditText) findViewById(R.id.phone_edittext);
        passwordRegisterEditText = (EditText) findViewById(R.id.password_edit);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        //progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait ....");
        user = new User();
    }

    public class ConnectServer extends AsyncTask< User,String,String>
    {
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
                URL url = new URL(registerUrl);
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
                JSONObject finalResponse  = new JSONObject(response);
                int res = finalResponse.optInt("status");
                if (res==201)
                {
                    return "Account Created , Please Verify your Mobile Number";
                }
                else {

                    return null;
                }
            }

            catch (Exception e)
            {
                e.printStackTrace();
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
            if(result != null) {
Intent i = new Intent(RegistrationActivity.this, ConfirmOTPActivity.class);
                i.putExtra("UserObj",user);
                startActivity(i);
            }
            else
            {
                Toast.makeText(RegistrationActivity.this, "Registered Failed ,Check provided information", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void validateData()
    {

    }

    private void prepareUrl()
    {
        registerUrl = registerUrl + user.getName();
        registerUrl = registerUrl + "&phone_no=";
        registerUrl = registerUrl + user.getPhone();
        registerUrl = registerUrl + "&password=";
        registerUrl = registerUrl + user.getPassword();
        System.out.print(registerUrl);
    }


}
