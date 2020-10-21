package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

public class SignupDonoractivity extends AppCompatActivity implements View.OnClickListener {
    EditText Emaildnr;
    EditText Passworddnr;
    // EditText Ornztiondnr;
    EditText bkashdnr;
    EditText rocketdnr;
    EditText typedn;
    // Button Signup ;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_donoractivity);
        this.setTitle("Sign Up");

        Emaildnr = findViewById(R.id.emaileditTextdn);
        Passworddnr = findViewById(R.id.passwordEditTextdn);
        // Ornztionfnd=findViewById(R.id.orgzeditText);
        bkashdnr = findViewById(R.id.bkashEditTextdn);
        rocketdnr = findViewById(R.id.RocketEditTextdn);
        typedn = findViewById(R.id.editTexttypedn);
        Button Signup = findViewById(R.id.signupbtndn);
        // Signup=findViewById(R.id.signupbtn);
        fAuth = FirebaseAuth.getInstance();


        Signup.setOnClickListener(this);



       /*Signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email= Emailfnd.getText().toString().trim();
               String Password =Passwordfnd.getText().toString().trim();

               if(TextUtils.isEmpty(email))
               {
                   Emailfnd.setError("Email is required");
                   return;
               }
               if(TextUtils.isEmpty(Password))
               {
                   Passwordfnd.setError("Password is Required");
               }

               fAuth.createUserWithEmailAndPassword(email,Password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {


                               if(task.isSuccessful())
                               {
                                   User user=new User(

                                   );
                                   Toast.makeText(signupfundactivity.this,"User Created",Toast.LENGTH_SHORT)
                                           .show();
                                   startActivity(new Intent(getApplicationContext(),loginfundactivity.class));

                               }

                               else
                                   {
                                       Toast.makeText(signupfundactivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT);

                                   }
                           }
                       });
           }
       });*/

    }

   /* @Override
    protected void onStart() {

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),SignupDonoractivity.class));
        }
        super.onStart();
    }*/


    private void registerUser() {


        // final String organztn= Ornztionfnd.getText().toString().trim();
        final String emaill = Emaildnr.getText().toString().trim();
        String password = Passworddnr.getText().toString().trim();
        final String bkashh = bkashdnr.getText().toString().trim();
        final String type = typedn.getText().toString().trim();

       /* if (organztn.isEmpty()) {
            Ornztionfnd.setError("Organization is required");
            Ornztionfnd.requestFocus();
            return;
        }*/

        if (emaill.isEmpty()) {
            Emaildnr.setError("Email is required");
            Emaildnr.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            Passworddnr.setError("Password required");
            Passworddnr.requestFocus();
            return;
        }

        if (password.length() < 6) {
            Passworddnr.setError("Password should not be less than 6");
            Passworddnr.requestFocus();
            return;
        }

        if (bkashh.isEmpty()) {
            bkashdnr.setError("Bkash number should be included");
            bkashdnr.requestFocus();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(SignupDonoractivity.this, R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Creating User..");
        progressDialog.show();

        fAuth.createUserWithEmailAndPassword(emaill, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    User user = new User(
                            //organztn,
                            emaill,
                            bkashh,
                            type
                    );

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(SignupDonoractivity.this, "User Created", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), LoginDonorActivity.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(SignupDonoractivity.this, "Failed ! Try Again.", Toast.LENGTH_LONG).show();
                                //display a failure message
                            }
                        }
                    });

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignupDonoractivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupbtndn:
                registerUser();
                break;
        }
    }


}
