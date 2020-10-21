package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView protext,Orgzpro,emmailpro,bkkashpro;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String mUid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        protext=findViewById(R.id.profileText);
        Orgzpro=findViewById(R.id.orgpro);
        emmailpro=findViewById(R.id.emailpro);
        bkkashpro=findViewById(R.id.bkashpro);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //databaseReference=firebaseDatabase.getReference(mUid);
        databaseReference=firebaseDatabase.getInstance().getReference("Users").child(mUid);

        //FirebaseUser userr=firebaseAuth.getCurrentUser();
        //String uid=userr.getUid();
        //emmailpro.setText(userr.getEmail());
        //Orgzpro.setText(userr.getDisplayName());



       // FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(getApplicationContext(),loginfundactivity.class));
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tmp = "Are UID =" + mUid;
                //Toast.makeText(ProfileActivity.this,tmp,Toast.LENGTH_SHORT).show();


               /*for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren())
                {
                    Toast.makeText(ProfileActivity.this,tmp,Toast.LENGTH_SHORT).show();
                   // User user =new User(Orgzpro,emmailpro,bkkashpro);
                    User user =(User)dataSnapshot1.getValue();
                    Orgzpro.setText("Name :"+user.getOrganztn());
                    emmailpro.setText(user.getEmaill());
                    bkkashpro.setText(user.getBkashh());

                   // emmailpro.setText(user.getEmaill());

                }*/

                // Test Work Starts

                Query query = FirebaseDatabase.getInstance().getReference("Users").child(mUid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String type1=dataSnapshot.child("type").getValue().toString();
                        if (type1.equals("Donor"))
                        {
                            String email = dataSnapshot.child("emaill").getValue().toString();
                            String bkash = dataSnapshot.child("bkashh").getValue().toString();
                            String type0= dataSnapshot.child("type").getValue().toString();


                            User user = new User( email, bkash,type0);

                            Orgzpro.setText("Name :"+user.getOrganztn());
                            emmailpro.setText("Email :" +user.getEmaill());
                            bkkashpro.setText("Bkash :" +user.getBkashh());


                        }

                        else if(type1.equals("FundRaiser"))
                        {
                            String kaj = dataSnapshot.child("bkashh").getValue().toString();

                            String org = dataSnapshot.child("organztn").getValue().toString();
                            String email = dataSnapshot.child("emaill").getValue().toString();
                            String bkash = dataSnapshot.child("bkashh").getValue().toString();
                            String type= dataSnapshot.child("type").getValue().toString();


                            User user = new User(org, email, bkash,type);

                            Orgzpro.setText("Name :"+user.getOrganztn());
                            emmailpro.setText("Email :" +user.getEmaill());
                            bkkashpro.setText("Bkash :" +user.getBkashh());

                            FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();
                            if (userr != null) {
                                //String name = user.getDisplayName();
                                String emaill = userr.getEmail();
                                Toast.makeText(ProfileActivity.this,kaj,Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





                // Test Work Ends


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(ProfileActivity.this,loginfundactivity.class));
        }
    }
}
