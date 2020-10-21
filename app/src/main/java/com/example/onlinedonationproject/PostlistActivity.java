package com.example.onlinedonationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostlistActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    EditText title,descript,organiz,bkash,emmail;
    Button save,load;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);
        fAuth=FirebaseAuth.getInstance();
        title=findViewById(R.id.entertitle);
        descript=findViewById(R.id.descript);
        organiz=findViewById(R.id.enterorgz);
        emmail=findViewById(R.id.enteremail);
        bkash=findViewById(R.id.enterbkash);
        save=findViewById(R.id.savebutton);
        load=findViewById(R.id.loadbutton);
        databaseReference= FirebaseDatabase.getInstance().getReference("Information");
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PostlistActivity.this,DetailsActivity.class);
                startActivity(intent);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        if(fAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(getApplicationContext(),loginfundactivity.class));
        }
    }

    private void saveData() {
        String tt=title.getText().toString().trim();
        String key=databaseReference.push().getKey();
        String dd=descript.getText().toString().trim();
        String or=organiz.getText().toString().trim();
        String bk=bkash.getText().toString().trim();
        String em=emmail.getText().toString().trim();

        Info info=new Info(tt,dd,or,bk,em);
        databaseReference.child(key).setValue(info);
        Toast.makeText(getApplicationContext(),"Info Added",Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.SignOutMenuId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId()==R.id.ProfileId)
        {
            startActivity(new Intent(PostlistActivity.this,ProfileActivity.class));

        }
        return super.onOptionsItemSelected(item);

    }
}
