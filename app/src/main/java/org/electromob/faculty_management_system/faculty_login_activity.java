package org.electromob.faculty_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;

public class faculty_login_activity extends AppCompatActivity {

    Button loginfaculty;
    EditText email,password;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login_activity);

        loginfaculty = findViewById(R.id.loginfaculty);
        email = findViewById(R.id.login_faculty_email);
        password = findViewById(R.id.login_faculty_password);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(getApplicationContext(), faculty_home.class));
        }

        loginfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  email.getText().toString().trim();
                String password1 =  password.getText().toString().trim();


                if (name.isEmpty() && password1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Entered username or password are blank or incorrect", LENGTH_SHORT).show();
                    loginfaculty.setEnabled(true);
                }
                else {
                    progressDialog.setMessage("Relax a bit we are loging in ...");
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(name,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Login successfull...", LENGTH_SHORT).show();
                                checkemailverification();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Login failed... Try verifying your email", LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    });
                }
            }
        });

    }

    private void checkemailverification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag){
            finish();
            startActivity(new Intent(getApplicationContext(), faculty_home.class));
        }else{
            Toast.makeText(getApplicationContext(),"Verify your email..", LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }

}
