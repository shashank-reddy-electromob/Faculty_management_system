package org.electromob.faculty_management_system;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

public class studentviewholer extends RecyclerView.ViewHolder {

    public TextView name123,room_number123,mon1,tue1,wed1,thur1,fri1,sat1,spl1,resume123,years,ares,papers;
    public ImageView faculty_profile_pic;

    public studentviewholer(@NonNull View itemView) {
        super(itemView);

        name123 = (TextView)itemView.findViewById(R.id.cardview_name);
        room_number123 = itemView.findViewById(R.id.cardview_room_number);
        spl1 = itemView.findViewById(R.id.cardview_spl);
        mon1 = itemView.findViewById(R.id.cardview_monday_time);
        tue1 = itemView.findViewById(R.id.cardview_tuesday_time);
        wed1 = itemView.findViewById(R.id.cardview_wednesday_time);
        thur1 = itemView.findViewById(R.id.cardview_thursday_time);
        fri1 = itemView.findViewById(R.id.cardview_friday_time);
        sat1 = itemView.findViewById(R.id.cardview_saturday_time);
        resume123 = itemView.findViewById(R.id.cardview_resume);
        faculty_profile_pic = itemView.findViewById(R.id.faculty_imageview);
        years = itemView.findViewById(R.id.yearsofexperience);
        ares = itemView.findViewById(R.id.area_of_interest);
        papers = itemView.findViewById(R.id.descriptionofpapers);
    }
}
