package com.example.stageappv3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stageappv3.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class PublishImgActivity extends AppCompatActivity {

    ActionBar actionBar;

    FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;

    EditText descrEtxt;
    ImageView imageView;
    Button publishBtn;

    // User info
    String name, email, uid, dp;

    // Permissions
    private static final int CAMERA_REQ_CODE = 100;
    private static final int STORAGE_REQ_CODE = 200;

    private static final int IMAGE_PICK_CAMERA_CODE = 300;
    private static final int IMAGE_PICK_GALLERY_CODE = 400;

    Uri image_uri = null;


    String[] cameraPermissions;
    String[] storagePermissions;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_img);

        //Show logo on toolbar and add back icon to toolbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.stageapp_vector_logo);

        // Init permissions
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // Progress bar
        pd = new ProgressDialog(this);

        //Show logo on toolbar and add back icon to toolbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.stageapp_vector_logo);

        firebaseAuth = FirebaseAuth.getInstance();


        checkUserStatus();


        // Get info about the current user
        dbRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = dbRef.orderByChild("email").equalTo(email);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()){
                    name = "" + ds.child("name").getValue();
                    email = "" + ds.child("email").getValue();
                    email = "" + ds.child("image").getValue();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        descrEtxt = findViewById(R.id.txtDescription_onPublishImgActivity);
        imageView = findViewById(R.id.imgView_onPublishImgActivity);
        publishBtn = findViewById(R.id.postPublishBtn);

        // Get IMG from camera or gallery of the user.
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOnPickDialog();
            }
        });

        // Listen publish btn
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get description supplied by user
                String descr = descrEtxt.getText().toString().trim();
                if (TextUtils.isEmpty(descr)){
                    Toast.makeText(PublishImgActivity.this, "Enter description...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (image_uri == null){
                    // Publish post without image
                    uploadData(descr, "noImage");

                }else{
                    // Publish post with image
                    uploadData(descr, String.valueOf(image_uri));
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to the previous activity
        return super.onSupportNavigateUp();
    }

    private void uploadData(String descr, String uri) {
        pd.setMessage("Publishing post...");
        pd.show();

        String timeStamp = String.valueOf(System.currentTimeMillis());

        // Path to store post
        String filePathAndName = "Posts/" + timeStamp;


        if (!uri.equals("noImage")){
            // Post with img
            StorageReference stRef = FirebaseStorage.getInstance().getReference().child(filePathAndName);
            stRef.putFile(Uri.parse(uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Img uploaded to firebase, get the url.
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());

                    String downloadUri = uriTask.getResult().toString();

                    if (uriTask.isSuccessful()) {
                        HashMap<Object, String> hashMap = new HashMap<>();
                        hashMap.put("uid",uid);
                        hashMap.put("uName",name);
                        hashMap.put("email",email);
                        hashMap.put("pId",timeStamp);
                        hashMap.put("uDp",dp);
                        hashMap.put("pDesc",descr);
                        hashMap.put("pImg",downloadUri);
                        hashMap.put("pTime",timeStamp);

                        // Path to store data
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts/");
                        ref.child(timeStamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                // Added in database successfuly
                                pd.dismiss();
                                Toast.makeText(PublishImgActivity.this, "Post published!", Toast.LENGTH_SHORT);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Post adding failed to db
                                pd.dismiss();
                                Toast.makeText(PublishImgActivity.this, "Post couldn't published. There is an error: " + e.getMessage(), Toast.LENGTH_SHORT);

                                // Reset views
                                descrEtxt.setText("");
                                imageView.setImageURI(null);
                                image_uri = null;
                            }
                        });

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Img upload failed
                    pd.dismiss();
                    Toast.makeText(PublishImgActivity.this, "Image couldn't upload. There is an error: " + e.getMessage(), Toast.LENGTH_SHORT);
                }
            });

        } else{
            // Post without img

            HashMap<Object, String> hashMap = new HashMap<>();
            hashMap.put("uid",uid);
            hashMap.put("uName",name);
            hashMap.put("email",email);
            hashMap.put("pId",timeStamp);
            hashMap.put("uDp",dp);
            hashMap.put("pDesc",descr);
            hashMap.put("pImg","noImage");
            hashMap.put("pTime",timeStamp);

            // Path to store data
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
            ref.child(timeStamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    // Added in database successfuly
                    pd.dismiss();
                    Toast.makeText(PublishImgActivity.this, "Post published!", Toast.LENGTH_SHORT);

                    // Reset views
                    descrEtxt.setText("");
                    imageView.setImageURI(null);
                    image_uri = null;

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Post adding failed to db
                    pd.dismiss();
                    Toast.makeText(PublishImgActivity.this, "Post couldn't published. There is an error: " + e.getMessage(), Toast.LENGTH_SHORT);
                }
            });

        }
    }

    private void showOnPickDialog() {
        String[] options = {"Camera", "Gallery"};

        // Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle clicked items, 0 -> camera | 1 -> gallery
                if (which == 0){
                    // Camera
                    if (!checkCameraPermission()){
                        requestCameraPermissions();
                    } else{
                        pickFromCamera();
                    }
                }
                if (which == 1){
                    // Gallery
                    if (!checkStoragePermission()){
                        requestStoragePermissions();
                    }else{
                        pickFromGallery();
                    }
                }

            }
        });

        // Show dialog
        builder.create().show();
    }

    private void pickFromCamera() {
        // Pick from camera intent
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Descripiton");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private boolean checkStoragePermission(){
        // Check for storage permissions
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermissions(){
        // Request for storage permission
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQ_CODE);
    }

    private boolean checkCameraPermission(){
        // Check for camera permissions
        boolean resultCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean resultStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return resultCamera && resultStorage;
    }

    private void requestCameraPermissions(){
        // Request for camera permission
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQ_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserStatus();
    }

    private void checkUserStatus(){
        // Get the current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            // User signed
            email = user.getEmail();
            uid = user.getUid();


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQ_CODE:{
                if (grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this, "Camera and Storage permissions are neccessary.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                }
            }
            break;

            case STORAGE_REQ_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        pickFromGallery();
                    } else{
                        Toast.makeText(this, "Storage permission is neccessary.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                image_uri = data.getData();
                imageView.setImageURI(image_uri);

            }else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                imageView.setImageURI(image_uri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}