package com.example.appsecondfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivityAdd extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 280;
    private static final int PICK_CAMERA_REQUEST = 200;
    public List<Etudiant> lstEt = new ArrayList<>();

    /*pour les fichier et les images */
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    /* pour texts*/
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("TabEtudiants");

    ImageView img;
    Uri filePath;
    TextView nom, prenom, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        img = findViewById(R.id.imageView);
        nom = findViewById(R.id.txt_nom);
        prenom = findViewById(R.id.txt_prenom);
        tel = findViewById(R.id.txt_telephone);
        myRef.addValueEventListener(evetList);
    }

    public void CamClick(View view) {
        Intent takep = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takep, PICK_CAMERA_REQUEST);
    }

    public void GallerieClick(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void AddClick(View view) {
        String uid = UUID.randomUUID().toString();
        StorageReference photo = storageReference.child("photos/" + uid);
//        addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(MainActivityAdd.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        })
        photo.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String photoStringLink = uri.toString();
                        Log.i("urlimage", photoStringLink);
                        Etudiant e = new Etudiant(nom.getText().toString(), prenom.getText().toString(), tel.getText().toString(), photoStringLink);
                        myRef.push().setValue(e);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivityAdd.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

               ///////message
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityAdd.this);
                builder.setTitle("My Data");
                builder.setMessage("Data add !");
                // add a button
                builder.setPositiveButton("OK", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                ////////////// fin message


            }
        });
    }

    public void AnuulerClick(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null) {
            filePath = data.getData();
            img.setImageURI(filePath);

        }
        if (requestCode == PICK_CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap =(Bitmap) extras.get("data");
            filePath = data.getData();
            img.setImageURI(filePath);
        }
    }

    //reqiperation de tableau
    ValueEventListener evetList = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            Etudiant et;
//            lstEt.clear();
//            for (DataSnapshot ds : snapshot.getChildren()) {
//                et = ds.getValue(Etudiant.class);
//                lstEt.add(et);
//            }
//            for (Etudiant e : lstEt) {
//                String s = "Nom :" + e.getNom() + " Prenom :" + e.getPrenom();
//                Toast.makeText(MainActivityAdd.this, s, Toast.LENGTH_SHORT).show();
//            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}