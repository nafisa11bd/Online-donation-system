package com.example.onlinedonationproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Info> {
    private Activity context;
    private List<Info> infolist;

    public CustomAdapter( Activity context, List<Info> infolist) {
        super(context, R.layout.sample_layout, infolist);
        this.context = context;
        this.infolist = infolist;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
       View view= layoutInflater.inflate(R.layout.sample_layout, null,true);
       Info info=infolist.get(position);
       final PackageManager pm=getContext().getPackageManager();
        TextView t1=view.findViewById(R.id.titletext);
        TextView t2=view.findViewById(R.id.ddd);
        TextView t3=view.findViewById(R.id.organiz);
        TextView t4=view.findViewById(R.id.Bkashno);
        TextView t5=view.findViewById(R.id.emailt);
        Button b1=view.findViewById(R.id.bkbutton);
        Button b2=view.findViewById(R.id.rkbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent=pm.getLaunchIntentForPackage("com.bKash.customerapp");
                 getContext().startActivity(intent);



            }
        });
        t1.setText("Name :" +info.getTitlee());

       t2.setText("Description :"+info.getDidlee());
       t3.setText("Organization :"+info.getOr() );
       t4.setText("Bkash No :"+info.getBk());
       t5.setText("Email Address :" +info.getEm());

        return view;
    }
}
