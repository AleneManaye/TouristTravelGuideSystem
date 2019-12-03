package com.biwash.touristguidesystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword_Fragment extends AppCompatActivity {
    TextView backToLoginBtn,forgotSubmitBtn;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password__fragment);
        backToLoginBtn=findViewById(R.id.backToLoginBtn);
        forgotSubmitBtn=findViewById(R.id.forgotSubmitBtn);
        email=findViewById(R.id.emailId);
        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword_Fragment.this,Login_Fragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        forgotSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valdiateEmail();
            }
        });
    }

    private void valdiateEmail(){
    String getEmailId=email.getText().toString();

        Pattern p=Pattern.compile(Utils.regEx);
        Matcher m=p.matcher(getEmailId);

        if (getEmailId.equals("") || getEmailId.length() == 0){
            email.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (m.find()){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Invalid E-mail", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}
