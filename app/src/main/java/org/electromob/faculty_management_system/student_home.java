package org.electromob.faculty_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class student_home extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference;
    ImageView logo;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    FirebaseRecyclerOptions<faculty_info> options ;
    FirebaseRecyclerAdapter<faculty_info,studentviewholer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        logo = findViewById(R.id.navlogo1);
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Data");
        reference.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<faculty_info>()
                .setQuery(reference,faculty_info.class).build();

        adapter = new FirebaseRecyclerAdapter<faculty_info, studentviewholer>(options) {
            @Override
            protected void onBindViewHolder(final studentviewholer studentviewholer, int i, faculty_info faculty_info) {

                studentviewholer.name123.setText(faculty_info.getName1());
                studentviewholer.room_number123.setText(faculty_info.getRoom_no1());
                studentviewholer.spl1.setText(faculty_info.getSpl1());
                studentviewholer.mon1.setText(faculty_info.getMon1());
                studentviewholer.tue1.setText(faculty_info.getTue1());
                studentviewholer.wed1.setText(faculty_info.getWed1());
                studentviewholer.thur1.setText(faculty_info.getThur1());
                studentviewholer.fri1.setText(faculty_info.getFri1());
                studentviewholer.sat1.setText(faculty_info.getSat1());
                studentviewholer.resume123.setText(faculty_info.getResume2());

                final StorageReference storageReference = firebaseStorage.getReference();
                storageReference.child(firebaseAuth.getUid()).child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(studentviewholer.faculty_profile_pic);
                    }
                });

            }

            @NonNull
            @Override
            public studentviewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_cardview,parent,false);

                return new studentviewholer(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
