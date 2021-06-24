package com.noaimnolab.somnum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    private EditText emailEt, passwordEt, usernameEt;
    private Button SignUpButton;
    private TextView SignInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        usernameEt = findViewById(R.id.username);
        SignUpButton = findViewById(R.id.registerbutton);
        progressDialog = new ProgressDialog(this);
        SignInTv = findViewById(R.id.haveanaccount);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        SignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Register(){
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String username = usernameEt.getText().toString();
        if (TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            passwordEt.setError("Enter your password");
            return;
        }
        else if (TextUtils.isEmpty(username)) {
            usernameEt.setError("Enter your username");
            return;
        }
        else if (password.length()<7) {
            passwordEt.setError("Password length should be 8 or more characters");
            return;
        }
        else if (!isValidEmail(email)) {
            emailEt.setError("Invalid Email");
            return;
        }
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterScreen.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterScreen.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(RegisterScreen.this, "Sign up fail", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}