package org.electromob.faculty_management_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class faculty_home extends AppCompatActivity {

    EditText Name,Id,room_number,monday,tuesday,wednesday,thursday,friday,saturday,notes,resume1,areaofintrest,despapers;
    Button update;
    ImageView logo;
    FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private static final int PICK_IMAGE = 123;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    String name12,id,room_no,mon,tue,wed,thur,fri,sat,spl,resume,areaof,despap;
    ImageView image;
    private Uri imagepath;
    private StorageReference mstorageref;
    private ProgressDialog progressDialog;
    private StorageTask muploadtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);

        Name = findViewById(R.id.faculty_name);
        logo = findViewById(R.id.navlogo);
        Id = findViewById(R.id.faculty_id);
        areaofintrest = findViewById(R.id.areasofintrest);
        despapers = findViewById(R.id.faculty_papers);
        room_number = findViewById(R.id.faculty_room_number);
        firebaseStorage = FirebaseStorage.getInstance();
        mstorageref = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        update = findViewById(R.id.faculty_update);
        notes = findViewById(R.id.special_notes);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        firebaseAuth = FirebaseAuth.getInstance();
        image = findViewById(R.id.faculty_imageview_homme);
        resume1 = findViewById(R.id.special_resume);
        saturday = findViewById(R.id.saturday);
        firebaseDatabase = FirebaseDatabase.getInstance();


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (muploadtask != null && muploadtask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Upload in progress ...",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE);


                }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name12 = Name.getText().toString();
                id = Id.getText().toString().trim();
                room_no = room_number.getText().toString().trim();
                mon = monday.getText().toString().trim();
                tue = tuesday.getText().toString().trim();
                wed = wednesday.getText().toString().trim();
                thur = thursday.getText().toString().trim();
                fri = friday.getText().toString().trim();
                sat = saturday.getText().toString().trim();
                spl = notes.getText().toString().trim();
                resume = resume1.getText().toString().trim();
                areaof = areaofintrest.getText().toString().trim();
                despap = despapers.getText().toString().trim();

                if (imagepath != null ){
                    progressDialog.setMessage("Relax a bit we are uploading your pic ...");
                    progressDialog.show();
                    StorageReference fileReference = mstorageref.child(name12);

                    muploadtask = fileReference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Process success",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Process failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"IMAGE OR NAME SELECTED: ",Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getApplicationContext(),name12,Toast.LENGTH_SHORT).show();

                reference = firebaseDatabase.getReference("Data").child(name12);
                reference.keepSynced(true);
                faculty_info faculty_info;
                faculty_info = new faculty_info(name12,id,room_no,mon,tue,wed,thur,fri,sat,spl,resume,areaof,despap);
                reference.setValue(faculty_info);

                Toast.makeText(getApplicationContext(),"Upload Sucess",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imagepath = data.getData();

            Picasso.get().load(imagepath).into(image);

        }

    }

}
