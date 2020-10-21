package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginDonorActivity extends AppCompatActivity {
    EditText emailedittextdnr;
    EditText passwordediitextdnr;
    Button signindnr;
    Button signupdnr;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_donor);
        this.setTitle("Login");
        signindnr=findViewById(R.id.signinbtndnr);

        emailedittextdnr=findViewById(R.id.emaildnrtxtview);
        passwordediitextdnr=findViewById(R.id.passworddnrtxtview);
        signupdnr=findViewById(R.id.signupdnrbtn);
        fAuth=FirebaseAuth.getInstance();
        signupdnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginDonorActivity.this, SignupDonoractivity.class));
                finish();



            }
        });
        signindnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailedittextdnr.getText().toString().trim();
                String Password =passwordediitextdnr.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    emailedittextdnr.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    passwordediitextdnr.setError("Password is Required");
                }

                loginAccount(email, Password);
            }
        });


    }

    private void loginAccount(String email, String Password) {

        final ProgressDialog progressDialog = new ProgressDialog(LoginDonorActivity.this, R.style.Theme_AppCompat_Light_Dialog);
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
                            if (type.equals("Donor"))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(LoginDonorActivity.this,"Logged in Successfully!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                                finish();
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginDonorActivity.this, "Authentication Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginDonorActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
