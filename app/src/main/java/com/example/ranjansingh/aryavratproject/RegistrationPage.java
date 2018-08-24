package com.example.ranjansingh.aryavratproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {

    EditText editName, editAge, editAddress, editEmail, editPassword, editConfirm_Password;
    RadioGroup radioGroup;
    RadioButton radioBtnMale, radioBtnFemale;
    Button btnRegistration;
    String name,email, password, confirm_password;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        myDb=new DatabaseHelper(this);

        editName= (EditText) findViewById(R.id.editName);
        editAge= (EditText) findViewById(R.id.editAge);
        editAddress= (EditText) findViewById(R.id.editAddress);
        editEmail= (EditText) findViewById(R.id.editEmail);
        editPassword= (EditText) findViewById(R.id.editPassword);
        editConfirm_Password= (EditText) findViewById(R.id.editConfirm_Password);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        radioBtnMale= (RadioButton) findViewById(R.id.radioBtnMale);
        radioBtnFemale= (RadioButton) findViewById(R.id.radioBtnFemale);
        btnRegistration= (Button) findViewById(R.id.btnRegistration);

        btnRegistration.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        int vid=view.getId();
        if(vid==R.id.btnRegistration)
        {
            // Call When the buttonis clicked to validate in input.
            register();

            boolean isInserted = myDb.insertData(editName.getText().toString(),
                    editAge.getText().toString(),
                    editAddress.getText().toString(),
                    editEmail.getText().toString(),
                    editPassword.getText().toString());
            if (isInserted)
                Toast.makeText(RegistrationPage.this, "Data inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(RegistrationPage.this, "Data not inserted", Toast.LENGTH_SHORT).show();



        }

    }

    public void register() {
        // 2. Initialise the input to string variable.
        initalize();
        if(!validation()) {
            Toast.makeText(this, "Registration has faild", Toast.LENGTH_SHORT).show();
        }
        else {

        registrationSucessfully();

        }

    }
    private void registrationSucessfully() {
        Toast.makeText(this, "Registration Page is Sucessfully", Toast.LENGTH_SHORT).show();
        // what will go after the validate input
        Intent intent=new Intent(RegistrationPage.this,LoginPage.class);
        startActivity(intent);
    }



    private boolean validation() {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 8) {
            editName.setError("name cannot be blank and grater then 8 charater");
            valid = false;
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("please enter valid Email Address");
            valid = false;
        }
        else if (editPassword.getText().toString().trim().length()==0){
            editPassword.setError("password can not be blank");
            valid=false;
        }

        else if (!password.matches(".*[0-8]+.*")) {
            editPassword.setError("password should atlest one Numeric latter");
            valid = false;
        }
      /*  else if (!password.matches(".*[@#$%]+.*")) {
            editPassword.setError("password should atlest one Special latter");
            valid = false;
        }
        else if (!password.matches(".*[A-Z]+.*")){
            editPassword.setError("password should atlest one capital latter");
            valid=false;
        }
        else if (!password.matches(".*[a-z]+.*")){
            editPassword.setError("password should atlest one small latter");
            valid=false;
        }*/
        else if(!password.equals(confirm_password)) {
            editConfirm_Password.setError("password should be same");
            valid=false;
        }
            return valid;
    }


    public void initalize() {
        name=editName.getText().toString().trim();
        email=editEmail.getText().toString();
        password=editPassword.getText().toString().trim();
        confirm_password=editConfirm_Password.getText().toString().trim();
    }
}
