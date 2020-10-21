package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class signupfundactivity extends AppCompatActivity implements View.OnClickListener  {
    EditText Emailfnd ;
    EditText Passwordfnd ;
    EditText Ornztionfnd;
    EditText bkashfnd ;
    EditText rocketfnd ;
    EditText typee;
   // Button Signup ;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupfundactivity);
        this.setTitle("Sign Up");

       Emailfnd=findViewById(R.id.emaileditText);
       Passwordfnd=findViewById(R.id.passwordEditText);
       Ornztionfnd=findViewById(R.id.orgzeditText);
       bkashfnd=findViewById(R.id.bkashEditText);
       rocketfnd=findViewById(R.id.RocketEditText);
       typee=findViewById(R.id.editTexttype);
       Button Signup=findViewById(R.id.signupbtn);
      // Signup=findViewById(R.id.signupbtn);
       fAuth=FirebaseAuth.getInstance();



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

  /*  @Override
    protected void onStart() {

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),loginfundactivity.class));
        }
        super.onStart();
    }*/


    private void registerUser()
{


    final String organztn= Ornztionfnd.getText().toString().trim();
    final String emaill=Emailfnd.getText().toString().trim();
    String password=Passwordfnd.getText().toString().trim();
    final String bkashh=bkashfnd.getText().toString().trim();
    final String type=typee.getText().toString().trim();

    if (organztn.isEmpty()) {
        Ornztionfnd.setError("Organization is required");
        Ornztionfnd.requestFocus();
        return;
    }

    if (emaill.isEmpty()) {
        Emailfnd.setError("Email is required");
        Emailfnd.requestFocus();
        return;
    }



    if (password.isEmpty()) {
        Passwordfnd.setError("Password required");
        Passwordfnd.requestFocus();
        return;
    }

    if (password.length() < 6) {
        Passwordfnd.setError("Password should not be less than 6");
        Passwordfnd.requestFocus();
        return;
    }

    if (bkashh.isEmpty()) {
        bkashfnd.setError("Bkash number should be included");
        bkashfnd.requestFocus();
        return;
    }

    fAuth.createUserWithEmailAndPassword(emaill, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        User user = new User(
                                organztn,
                                emaill,
                                bkashh,
                                type
                        );

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(signupfundactivity.this, "User Created", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),loginfundactivity.class));
                                } else {
                                    //display a failure message
                                }
                            }
                        });

                    } else {
                        Toast.makeText(signupfundactivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

}



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signupbtn:
                registerUser();
                break;
        }
    }


}
