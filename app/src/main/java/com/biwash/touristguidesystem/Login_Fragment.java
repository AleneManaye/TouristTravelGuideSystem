package com.biwash.touristguidesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends AppCompatActivity {

    private EditText login_email,login_password;
    private Button btnLogin;
    private TextView forgotPassword,singUp;
    private CheckBox show_hide_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        btnLogin=findViewById(R.id.btnLogin);
        forgotPassword=findViewById(R.id.forgot_password);
        singUp=findViewById(R.id.singUp);
        btnLogin=findViewById(R.id.btnLogin);
        show_hide_password=findViewById(R.id.show_hide_password);

        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    show_hide_password.setText(R.string.hide_pwd);// change
                    login_password.setInputType(InputType.TYPE_CLASS_TEXT);
                    login_password.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());// show passwords
                }
                else {
                    show_hide_password.setText(R.string.show_pwd);// change
                    login_password.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    login_password.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());// hide password
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Fragment.this,SignUp_Fragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Fragment.this,ForgotPassword_Fragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void validateLogin(){
        //get email id and password
           String getEmailId=login_email.getText().toString();
           String getPassword=login_password.getText().toString();

           //check pattern for email id
        Pattern p= Pattern.compile(Utils.regEx);
        Matcher m=p.matcher(getEmailId);

        // check email and password field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0){
            login_email.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getPassword.equals("") || getPassword.length() == 0){
            login_password.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!m.find()){
            Toast.makeText(this, "Invalid E-mail", Toast.LENGTH_SHORT).show();
            return;
        }

        if (getEmailId.equals("admin@gmail.com") && getPassword.equals("admin")){
         //   Intent intent = new Intent (this, DashboardActivity.class);
           // startActivity(intent);

            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
