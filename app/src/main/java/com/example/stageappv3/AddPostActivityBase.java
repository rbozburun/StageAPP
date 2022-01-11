package com.example.stageappv3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import kotlin.HashCodeKt;

// Coded by Resul Bozburun
public class AddPostActivityBase extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_post);



        // Create fragments -------------------------------------------
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = new ChoosePostTypeFragment();
        fragmentTransaction.add(R.id.frameLayout_onAddPostActivity, fragment);
        fragmentTransaction.commit();
        //---------------------------------------------------------------

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to the previous activity
        return super.onSupportNavigateUp();
    }


}