package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private ListView listView;
    private List<Info>infoList;
    //private List<User>userList;
    //new
    private CustomAdapter customAdapter;
    DatabaseReference databaseReference;
    //DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        databaseReference= FirebaseDatabase.getInstance().getReference("Information");
        //new
       // databaseReference=FirebaseDatabase.getInstance().getReference("User");
        listView=findViewById(R.id.listid);
        infoList=new ArrayList<>();
       // userList=new ArrayList<>();
        //new
        customAdapter=new CustomAdapter(DetailsActivity.this,infoList);
       // customAdapter=new CustomAdapter(DetailsActivity.this,userList);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoList.clear();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren())
                {
                    Info info=dataSnapshot1.getValue(Info.class);
                    infoList.add(info);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
