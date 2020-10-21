package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginfundactivity extends AppCompatActivity {
    EditText emailedittextfnd;
    EditText passwordediitextfnd;
    Button signinfnd;
    Button signupfnd;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfundactivity);
        this.setTitle("Login");
        signinfnd=findViewById(R.id.signinbtnfnd);

        emailedittextfnd=findViewById(R.id.emailfndtxtview);
        passwordediitextfnd=findViewById(R.id.passwordfndtxtview);
        signupfnd=findViewById(R.id.signupfndbtn);
        fAuth=FirebaseAuth.getInstance();
        signupfnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginfundactivity.this, signupfundactivity.class));
                finish();
            }
        });
        signinfnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailedittextfnd.getText().toString().trim();
                String Password =passwordediitextfnd.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    emailedittextfnd.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    passwordediitextfnd.setError("Password is Required");
                }

                loginAccount(email, Password);
            }
        });

    }

    private void loginAccount(String email, String Password) {

        final ProgressDialog progressDialog = new ProgressDialog(loginfundactivity.this, R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        fAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    String mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Query query = FirebaseDatabase.getInstance().getReference("Users").child(mUid);

                    query.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String type=dataSnapshot.child("type").getValue().toString();
                            if (type.equals("FundRaiser"))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(loginfundactivity.this,"Logged in Successfully!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), PostlistActivity.class));
                                finish();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(loginfundactivity.this, "Authentication Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(loginfundactivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
