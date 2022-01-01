package com.example.stageappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

// Coded by Resul Bozburun
public class RegisterActivity<DatabaseReferencereference> extends AppCompatActivity {

    MaterialEditText nameSurname, email, password, role;
    Button btnRegister;

    FirebaseAuth auth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        nameSurname = findViewById(R.id.name_surname);
        password = findViewById(R.id.password);
        role = findViewById(R.id.role);
        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNameSurname = nameSurname.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                String txtRole = role.getText().toString();


                // Check registration process.
                if (TextUtils.isEmpty(txtNameSurname) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtRole)){
                    Toast.makeText(RegisterActivity.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                }else if(txtPassword.length() < 8){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters!", Toast.LENGTH_SHORT).show();
                }else{
                    register(txtNameSurname,txtEmail, txtPassword, txtRole);
                }
            }
        });
    }

    // Register to firebase db
    private void register(String nameSurname, String email, String password, String role){

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userId = firebaseUser.getUid();

                            dbRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("nameSurname", nameSurname);
                            hashMap.put("role",role);
                            hashMap.put("imageURL","default");

                            // If registration process OK, redirect the user to login activity.
                            dbRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else{
                            Toast.makeText(RegisterActivity.this, "You can't register with this email or password. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}