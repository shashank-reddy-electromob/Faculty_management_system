package org.electromob.faculty_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class signup_activity extends AppCompatActivity {

    ImageView signupstudents,signupfaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);

        signupstudents = findViewById(R.id.signupstudents);
        signupfaculty = findViewById(R.id.signupfaculty);

        signupstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),student_signup_activity.class));
            }
        });

        signupfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),faculty_signup_activity.class));
            }
        });

    }
}
