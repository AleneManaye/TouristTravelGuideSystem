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
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Fragment extends AppCompatActivity {
    EditText fullName,email,phone,password,cPassword;
    TextView already_user;
    Button btnSignUp;
    CheckBox termsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        fullName=findViewById(R.id.fullName);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        cPassword=findViewById(R.id.cPassword);
        already_user=findViewById(R.id.already_user);
        btnSignUp=findViewById(R.id.btnSignUp);
        termsCheck=findViewById(R.id.termsCheck);

        already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp_Fragment.this,Login_Fragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSignUp();
            }
        });
    }

    private void validateSignUp(){
        //get email id and password
        String getEmail=email.getText().toString();
        String getfullName=fullName.getText().toString();
        String getPassword=password.getText().toString();
        String getPhone=phone.getText().toString();
        String getcPassword=cPassword.getText().toString();

        //check pattern for email id
        Pattern p=Pattern.compile(Utils.regEx);
        Matcher m=p.matcher(getEmail);

        // Check values in the field are empty or not
        if (getfullName.equals("") || getfullName.length() == 0){
            fullName.requestFocus();
            Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getEmail.equals("") || getEmail.length() == 0){
            email.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.equals("") || phone.length()<5 ){
            phone.requestFocus();
            Toast.makeText(this, "Please enter valid phone", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getPassword.equals("") || getPassword.length() == 0){
            password.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getcPassword.equals("") || getcPassword.length() == 0){
            password.requestFocus();
            Toast.makeText(this, "Please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!m.find()){
            Toast.makeText(this, "Invalid E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if(getPassword.equals(getcPassword)){
            if(termsCheck.isChecked()){
                Toast.makeText(this, "Successfully registered.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "You must agree terms before proceeding", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Password did not match", Toast.LENGTH_SHORT).show();
        }
    }
}
