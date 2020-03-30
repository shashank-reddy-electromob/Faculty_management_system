package org.electromob.faculty_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_signup_activity extends AppCompatActivity {

    Button signups;
    EditText email,name,id,password,confirmpassword;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reff;
    private FirebaseUser firebaseUser;
    String sfname1,slname1,email1,np,ncp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup_activity);

        signups = findViewById(R.id.signups);
        email = findViewById(R.id.signup_student_email);
        name = findViewById(R.id.signup_student_name);
        id = findViewById(R.id.signup_student_number);
        password = findViewById(R.id.signup_student_password);
        confirmpassword = findViewById(R.id.signup_student_confirm_password);
        firebaseAuth = FirebaseAuth.getInstance();

        signups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sfname1 = name.getText().toString();
                slname1 = id.getText().toString();
                np = password.getText().toString();
                ncp = confirmpassword.getText().toString();
                email1 = email.getText().toString().trim();

                if (np.equals(ncp)) {


                    if (sfname1.isEmpty() || slname1.isEmpty() || email1.isEmpty() || np.isEmpty() || ncp.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter all the feilds....", Toast.LENGTH_SHORT).show();
                    } else {

                        firebaseAuth.createUserWithEmailAndPassword(email1, np).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendemail();
                                } else {
                                    Toast.makeText(getApplicationContext(), "registration failed....", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        Toast.makeText(getApplicationContext(), "Data taken ....", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Entered password doesnt match:..",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendemail(){
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        reff = firebaseDatabase.getReference("users").child("Students").child(firebaseAuth.getUid());
                        userinfo userinfo;
                        userinfo = new userinfo(sfname1, slname1, email1, np, ncp);
                        reff.setValue(userinfo);
                        Toast.makeText(getApplicationContext(),"Successfully registered, verification mail has sent",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), login_activity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Verification mail failed to send try after sometime", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
