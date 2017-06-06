package hireapro.himanshu.hireapro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hireapro.himanshu.hireapro.dataclass.User;
import hireapro.himanshu.hireapro.dataclass.Utilities;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String loginUrl ;
    private int CONNECTIONOUT_TIME = 15000;



private User user;
    //views
    private EditText emailPhoneEditText,passwordEditText;
    private Button loginButton;
    private Switch remembermeSwitch;
    private TextView forgotPasswordTextView,createAccountTextView;
    private TextInputLayout inputLayoutEmail,inputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intializeComponents();
        loginButton.setOnClickListener(this);
        createAccountTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.login_button:

                if(validateEmail() && validatePassword()) {
                    getUserData();
                    prepareUrl();
                    checkRememberMe();
                    new ConnectServer().execute(user);
                }
                    break;
            case R.id.create_account_textview:

                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                break;
            case R.id.forgot_password_textview:
                // your code for button2 here
                break;


        }
    }

    private boolean checkRememberMe() {
        return false;
    }


    private void prepareUrl() {

        loginUrl = Utilities.SERVER_URL;
        loginUrl = loginUrl + "/user/loginuser_with_phoneno.php?phone_no=";
        loginUrl = loginUrl + user.getPhone();
        loginUrl = loginUrl + "&password=";
        loginUrl = loginUrl + user.getPassword();
        Log.d("Login URL Prepared",loginUrl);
    }



    private void getUserData() {


        user.setPhone(Long.valueOf(emailPhoneEditText.getText().toString()));
        user.setPassword(passwordEditText.getText().toString());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateEmail() {
        if (emailPhoneEditText.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setError("Please Enter Email or Phone no");
            requestFocus(emailPhoneEditText);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (passwordEditText.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Please enter password");
            requestFocus(passwordEditText);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


    private void intializeComponents() {
        emailPhoneEditText = (EditText) findViewById(R.id.phone_email_edittext);
        passwordEditText= (EditText) findViewById(R.id.password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_emailphone);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

     //   remembermeSwitch = (Switch) findViewById(R.id.remember_me_toggle);
        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_textview);
        createAccountTextView = (TextView) findViewById(R.id.create_account_textview);
        user = new User();

    }



    public class ConnectServer extends AsyncTask < User,String,String>
    {
boolean loginFailure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                if(stat!=200)
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


            if(loginFailure==true)
            {
                Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
