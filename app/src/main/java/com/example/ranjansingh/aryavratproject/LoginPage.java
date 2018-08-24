package com.example.ranjansingh.aryavratproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
@SuppressWarnings("ALL")
public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    EditText editLoginEmail, editLoginPassword;
    Button ButtonLogin;
    DatabaseHelper myDb;
    String email, password;

    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        myDb = new DatabaseHelper(this);

        editLoginEmail = (EditText) findViewById(R.id.editLoginEmail);
        editLoginPassword = (EditText) findViewById(R.id.editLoginPassword);
        ButtonLogin = (Button) findViewById(R.id.ButtonLogin);

        ButtonLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        int vid = view.getId();
        if (vid == R.id.ButtonLogin) {
            // Call When the buttonis clicked to validate in input.
            loginRegister();

        }
    }

        /**
         * This method is to empty all input edit text
         */
    private void emptyInputEditText() {
        editLoginEmail.setText(null);
        editLoginEmail.setText(null);
    }


    public void loginRegister() {
        // 2. Initialise the input to string variable.
        initalize();
        if(!validation()) {
            Toast.makeText(this, "Login validation has faild", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(this, "Login validation Sucessfully", Toast.LENGTH_SHORT).show();

            LoginSucessfully();
        }

    }

    private void LoginSucessfully() {
        if (myDb.checkUser(editLoginEmail.getText().toString().trim()
                , editLoginPassword.getText().toString().trim())) {

            // To Pass Data LoginActivity to UserListActivity...
            Intent accountsIntent = new Intent(LoginPage.this, DisplayActivity.class);
            accountsIntent.putExtra("EMAIL", editLoginEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            Toast.makeText(this, "Login validation Sucessfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "error_valid_email_password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validation() {
        boolean valid = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editLoginEmail.setError("please enter valid Email Address");
            valid = false;
        }
        else if (editLoginPassword.getText().toString().trim().length()==0 || editLoginPassword.length() < 8) {
            editLoginPassword.setError("password can not be blank and atlest 8 charater");
            valid=false;
        }
        return valid;
    }


    public void initalize() {
        email=editLoginEmail.getText().toString();

    }
}

