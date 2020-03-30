package org.electromob.faculty_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login_activity extends AppCompatActivity {

    ImageView studentLogin,facultyLogin;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        studentLogin = findViewById(R.id.studentlogin);
        facultyLogin = findViewById(R.id.facultylogin);
        forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),forgot_password.class));
            }
        });

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),student_login_activity.class));
            }
        });

        facultyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),faculty_login_activity.class));
            }
        });

    }
}
