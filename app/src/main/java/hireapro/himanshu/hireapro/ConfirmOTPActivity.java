package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConfirmOTPActivity extends AppCompatActivity implements View.OnClickListener {
    private int otp;
    private EditText otpEditText;
    private Button confirmOTPButton,resendOTPButton,goBackButton;
    ProgressDialog progressDialog;
    User userData;
    private String otpMessage;
    private String otpUrl = "http://103.16.142.107:55/api/mt/SendSMS?user=vinners&password=vinners&senderid=HIRPRO&channel=Trans&DCS=0&flashsms=0&number=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_otp);
        Intent i =getIntent();
        userData = (User) i.getSerializableExtra("UserObj");
        intializeComponents();
        generateOTP();
        generateOTPMessage();
        prepareUrl();
        new ConfirmOTPActivity.ConnectServer().execute();
    }

    private void intializeComponents() {
        otpEditText = (EditText) findViewById(R.id.otp_edittext);
        confirmOTPButton = (Button) findViewById(R.id.confirm_otp_button);
        resendOTPButton = (Button) findViewById(R.id.resend_otp_button);
        goBackButton = (Button) findViewById(R.id.go_back_button);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        //progressDialog.setTitle("Processing");
        progressDialog.setMessage("Detecting OTP...");
        confirmOTPButton.setOnClickListener(this);
        resendOTPButton.setOnClickListener(this);
        goBackButton.setOnClickListener(this);
    }

    private void generateOTPMessage() {
        otpMessage = "Your%20OTP%20is%20" + otp + ".%20Thanks%20for%20registering%20with%20Hire%20A%20Pro.%20Have%20a%20good%20day";
    }

    private void generateOTP() {
        otp = (int)(Math.random()*9000)+1000;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.confirm_otp_button:
if(Integer.valueOf(otpEditText.getText().toString())==otp)
{
    Toast.makeText(this, "Welcome Aboard", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(ConfirmOTPActivity.this,HomeScreenActivity.class));
}
else
{
    Toast.makeText(this, "OTP Mismatch, try again", Toast.LENGTH_SHORT).show();
}
                break;
            case R.id.go_back_button:
                Toast.makeText(ConfirmOTPActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.resend_otp_button:
                Toast.makeText(ConfirmOTPActivity.this, "Will be Implemented Soon", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void prepareUrl() {
        otpUrl = otpUrl + userData.getPhone();
        otpUrl = otpUrl + "&text=";
        otpUrl = otpUrl + otpMessage;
        otpUrl = otpUrl + "&route=01";
        Log.d("OTPURL",otpUrl);
    }

    public class ConnectServer extends AsyncTask< User,String,String>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressDialog.show();
        }

        @Override
        protected String doInBackground(User... users) {
            HttpURLConnection httpURLConnection = null;
            //   httpURLConnection.setConnectTimeout(CONNECTIONOUT_TIME);
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(otpUrl);
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
                String err = finalResponse.optString("ErrorCode");
                if (err.equals("000"))
                {
                    Toast.makeText(ConfirmOTPActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(ConfirmOTPActivity.this, "Unable to send OTP", Toast.LENGTH_SHORT).show();
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
           // progressDialog.hide();

        }
    }
}
